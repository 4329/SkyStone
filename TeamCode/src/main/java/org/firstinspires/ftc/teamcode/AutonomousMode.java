/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import java.sql.Driver;

/**
 * This file illustrates the concept of driving a path based on time.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByEncoder;
 *
 *   The desired path in this example is:
 *   - Drive forward for 3 seconds
 *   - Spin right for 1.3 seconds
 *   - Drive Backwards for 1 Second
 *   - Stop and close the claw.
 *
 *  The code is written in a simple form with no optimizations.
 *  However, there are several ways that this type of sequence could be streamlined,
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */


public abstract class AutonomousMode extends LinearOpMode {

    /* Declare OpMode members. */
    protected SkystoneHardwareMap robot = new SkystoneHardwareMap();
    protected RobotController robotController = new RobotController(robot);
    private ElapsedTime     runtime = new ElapsedTime();


    static final double     FORWARD_SPEED = 0.6;
    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // Neverest 40 motor
    static final double     DRIVE_GEAR_REDUCTION    = 1.286 ;     // Gear ratio for tilerunner
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     UNKNOWN_REDUCTION       = 3.0/5.0 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION * UNKNOWN_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.7;
    static final double     TURN_SPEED              = 1.0;
    static final double     LEFT                    = 1.0;
    static final double     RIGHT                   =-1.0;
    static final double     SLOW_SPEED              = 0.3;

    @Override
    public void runOpMode() {
        initOpMode();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way


        moveFoundationInBuildZoneZigZag();
        //correct for being off either direction
        correctAngleAfterFoundation();
//        backFoundationToLine();
        foundationMoveStone();


        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }

    protected void pickupStoneFromStart() {
        robotController.sgDeployDown();
        robotController.stoneGrabberUp();
        encoderDrive(DRIVE_SPEED, 20,20,5);
        encoderDrive(SLOW_SPEED, 9.5,9.5,5);
        sleep(500);
        robotController.stoneGrabberDown();
        robotController.liftAndSupport();
        robotController.elevatorDown();
    }

    protected void moveStoneAcrossLineSkybridge() {
        encoderDrive(DRIVE_SPEED, -5, -5, 5);
        turnToAngle(colorDesiredAngle(), .5, colorDirection());
        encoderDrive(DRIVE_SPEED, 40, 40, 5);
        robotController.liftAndUnsupport();
        robotController.elevatorDown();
        robotController.stoneGrabberUp();
    }

    protected void moveSecoundStoneAcrossLine(double distanceToStone){
        encoderDrive(DRIVE_SPEED,-48,-48,5);
        turnToAngle(zeroAngle(),.5,-colorDirection());
        encoderDrive(DRIVE_SPEED, distanceToStone, distanceToStone,3);
        robotController.stoneGrabberDown();
        robotController.liftAndSupport();
        robotController.elevatorDown();
        encoderDrive(DRIVE_SPEED, -distanceToStone, -distanceToStone,3);
        turnToAngle(colorDesiredAngle(),.5, colorDirection());
        encoderDrive(DRIVE_SPEED, 48, 48, 5);
        robotController.liftAndUnsupport();
        robotController.elevatorDown();
        robotController.stoneGrabberUp();
        driveBackToLine();
    }

    protected void driveBackToLine() {
        encoderDrive(DRIVE_SPEED, -20, -20, 5);
    }

    protected void moveStoneAcrossLineWall() {
        encoderDrive(DRIVE_SPEED, -26, -26, 5);
        turnToAngle(colorDesiredAngle(), .5, colorDirection());
        encoderDrive(DRIVE_SPEED, 40, 40, 5);
        robotController.liftAndUnsupport();
        robotController.elevatorDown();
        robotController.stoneGrabberUp();
    }

    protected void foundationMoveStone() {
        encoderDrive(DRIVE_SPEED, -61, -61, 5);
        turnToAngle(5, .4, -colorDirection());
        encoderDrive(DRIVE_SPEED, 10, 10, 5);
        robotController.foundationGrabberDown();
        sleep(500);
        encoderDrive(DRIVE_SPEED, -10, -10, 5);
        turnToAngle(colorDesiredAngle(), .4, colorDirection());
        encoderDrive(DRIVE_SPEED, 33, 33, 5);
        robotController.foundationGrabberUp();
        encoderDrive(DRIVE_SPEED, -14, -14, 5);
    }

    protected void backFoundationToLine() {
        encoderDrive(DRIVE_SPEED, -42, -42, 5);
    }

    protected void correctAngleAfterFoundation() {
        if (robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle > 90*colorDirection()){
            turnToAngle(90*colorDirection(), .45, -colorDirection());
        }
        else if (robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle < 90*colorDirection()) {
            turnToAngle(90 * colorDirection(), .45, colorDirection());
        }
    }
    protected void moveFoundationInBuildZone() {
        encoderDrive(DRIVE_SPEED, 24, 24, 5);
        encoderDrive(SLOW_SPEED, 6.5, 6.5, 5);
        robotController.foundationGrabberDown();
        sleep(750);
        encoderDrive(DRIVE_SPEED, -16, -16, 5);
        turnToAngle(colorDesiredAngle(), 1, colorDirection());
        robotController.foundationGrabberUp();

        encoderDrive(DRIVE_SPEED, -2, -2, 2);
        //robotController.foundationGrabberDown();
        sleep(750);
        encoderDrive(1, 7, 7, 2);
        //this is the final back up
        encoderDrive(DRIVE_SPEED, -6, -6, 5);
    }
    protected void moveFoundationInBuildZoneZigZag() {
        encoderDrive(DRIVE_SPEED, 15, 15, 5);
        turnToAngle(colorDesiredAngle(),.6,colorDirection());
        encoderDrive(DRIVE_SPEED, 5,5, 5);
        turnToAngle(zeroAngle(),.6,-colorDirection());
        encoderDrive(DRIVE_SPEED, 13.5,13.7,5);
        robotController.foundationGrabberDown();
        sleep(750);
        encoderDrive(DRIVE_SPEED, -16, -16, 3);
        turnToAngle(colorDesiredAngle(), 1, colorDirection());
        robotController.foundationGrabberUp();

        encoderDrive(DRIVE_SPEED, -2, -2, 2);
        //robotController.foundationGrabberDown();
        sleep(750);
        encoderDrive(1, 7, 7, 2);
        //this is the final back up
        encoderDrive(DRIVE_SPEED, -6, -6, 5);
    }
    protected void returnToWall() {
        turnToAngle(zeroAngle(),.6,-colorDirection());
        encoderDrive(DRIVE_SPEED, -24,-24,2);
    }
    protected void driveToLineWall(int distance) {
        encoderDrive(DRIVE_SPEED, distance, distance, 2);
        turnToAngle(wallSmasher(),.5,colorDirection());
        backFoundationToLine();
    }
    protected void driveToLineSkybridge(int distance) {
        encoderDrive(DRIVE_SPEED, distance, distance, 2);
        turnToAngle(colorDesiredAngle(),.5,colorDirection());
        backFoundationToLine();
    }

    protected void initOpMode() {
        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
    }

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {


        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            int newLeftBackTarget = robot.backLeftDrive.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            int newRightBackTarget = robot.backRightDrive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            int newLeftFrontTarget = robot.frontLeftDrive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            int newRightFrontTarget = robot.frontRightDrive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            robot.frontLeftDrive.setTargetPosition(newLeftFrontTarget);
            robot.frontRightDrive.setTargetPosition(newRightFrontTarget);
            robot.backLeftDrive.setTargetPosition(newLeftBackTarget);
            robot.backRightDrive.setTargetPosition(newRightBackTarget);

            // Turn On RUN_TO_POSITION
            robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.frontLeftDrive.setPower(Math.abs(speed));
            robot.frontRightDrive.setPower(Math.abs(speed));
            robot.backLeftDrive.setPower(Math.abs(speed));
            robot.backRightDrive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.frontLeftDrive.isBusy() || robot.frontRightDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftFrontTarget,  newRightFrontTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.frontLeftDrive.getCurrentPosition(),
                        robot.frontRightDrive.getCurrentPosition());
                telemetry.addData("timeoutS", timeoutS);
                telemetry.addData("timeout", runtime.seconds());
                telemetry.addData("imu angle", robot.imu.getPosition());
                telemetry.addData("imu angle_other", robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);
                telemetry.update();
            }

            // Stop all motion;
            robot.frontLeftDrive.setPower(0);
            robot.frontRightDrive.setPower(0);
            robot.backLeftDrive.setPower(0);
            robot.backRightDrive.setPower(0);


            // Turn off RUN_TO_POSITION
            robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            //  sleep(250);   // optional pause after each move
        }
    }
    private void turnToAngle(double desiredAngle, double speed, int direction) {
        int numloops = 0;
        double powermultiplier = direction;
        double TURNTOANGLE_SPEED = speed;

        robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.backRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        robot.frontLeftDrive.setPower(-TURNTOANGLE_SPEED * powermultiplier);
        robot.backLeftDrive.setPower(-TURNTOANGLE_SPEED * powermultiplier);
        robot.frontRightDrive.setPower(TURNTOANGLE_SPEED * powermultiplier);
        robot.backRightDrive.setPower(TURNTOANGLE_SPEED * powermultiplier);


        while (opModeIsActive() &&
                isNotDesiredAngle(robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle, desiredAngle, direction)) {
        // need to update while condition based on red or blue, method will switch less than with greater than for red. -Luca
//            telemetry.addData("Robot Turning", "Left");
//            telemetry.addData("imu angle", robot.imu.getPosition());
//            telemetry.addData("imu angle_other", robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);
//            telemetry.update();
            idle();
        }
        robot.frontLeftDrive.setPower(0);
        robot.backLeftDrive.setPower(0);
        robot.frontRightDrive.setPower(0);
        robot.backRightDrive.setPower(0);

        numloops = 0;
    }




    abstract int colorDirection();
        abstract int colorDesiredAngle();
        abstract int zeroAngle();
        abstract boolean isNotDesiredAngle(double firstAngle, double v,int direction);
        abstract int wallSmasher();
}

