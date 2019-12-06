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

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name = "Tele Op Mode", group = "Iterative Opmode")
public class Iterative_Drive_Mode extends OpMode {
    public static final int CORE_HEX_90_DEGREES = 65;
    public static final double ELEVATOR_POWER = 0.425;
    public static final double ELEVATOR_UP_POWER = 1;

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private boolean isPov = true;
    static final double INCREMENT = 0.01;     // amount to slew stoneGrabberServo each CYCLE_MS cycle
    static final int CYCLE_MS = 50;     // period of each cycle
    static final double MAX_POS = 1.0;     // Maximum rotational position
    static final double MIN_POS = 0.0;     // Minimum rotational position
    static final double     FOUNDATION_GRABBER_SPEED             = 0.6;
    private SkystoneHardwareMap robot = new SkystoneHardwareMap();
    private RobotController robotController = new RobotController(robot);

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized cadet");

        robot.init(hardwareMap);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        if (gamepad1.start) {
            isPov = !isPov;
            telemetry.addData("pov mode ", isPov);
        }
        telemetry.addData("imu angle_other", robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry
        double leftPower;
        double rightPower;

        if (gamepad1.start) {
            isPov = !isPov;
        }

        telemetry.addData("pov mode ", isPov);

        // Choose to drive using either Tank Mode, or POV Mode
        // Comment out the method that's not used.  The default below is POV.

        if (isPov) {
            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            leftPower = Range.clip(drive + turn, -1.0, 1.0);
            rightPower = Range.clip(drive - turn, -1.0, 1.0);
        } else {
            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            leftPower = -gamepad1.left_stick_y;
            rightPower = -gamepad1.right_stick_y;
        }

        if (gamepad1.right_bumper) {
            int leftMultiplier = 1;
            if (leftPower < 0){
                leftMultiplier = -1;
            }
            int rightMultiplier = 1;
            if (rightPower < 0){
                rightMultiplier = -1;
            }
            leftPower = Range.clip(leftPower, -0.99, 0.99);
            rightPower = Range.clip(rightPower, -0.99, 0.99);
            leftPower = Math.pow(leftPower, 2) * leftMultiplier;
            rightPower = Math.pow(rightPower, 2) * rightMultiplier;
        }

        // Send calculated power to wheels
        robot.frontLeftDrive.setPower(leftPower);
        robot.frontRightDrive.setPower(rightPower);
        robot.backLeftDrive.setPower(leftPower);
        robot.backRightDrive.setPower(rightPower);

       if (gamepad2.a) {
            robotController.stoneGrabberDown();
        }
        if (gamepad2.b) {
            robotController.stoneGrabberUp();
        }

        if (gamepad2.dpad_left) {
           robotController.stoneGrabberSupportUp();
        }
        if (gamepad2.dpad_right) {
            robotController.stoneGrabberSupportDown();


        }

    // STICK DIRECTIONS ARE NEGATIVE!!!!!!!!
        if (gamepad2.left_stick_y < 0 && robot.leftElevatorMotor.getCurrentPosition() < CORE_HEX_90_DEGREES * 17) {
            robot.leftElevatorMotor.setPower(ELEVATOR_UP_POWER);
        }
        else if (gamepad2.left_stick_y > 0 && robot.leftElevatorMotor.getCurrentPosition() > 8) {//prevent going under zero
            robot.leftElevatorMotor.setPower(-ELEVATOR_POWER);
        }
        else {
            robot.leftElevatorMotor.setPower(0);
        }


        if (gamepad2.left_stick_y < 0 && robot.rightElevatorMotor.getCurrentPosition() < CORE_HEX_90_DEGREES * 17) {
            robot.rightElevatorMotor.setPower(ELEVATOR_UP_POWER);
        }
        else if (gamepad2.left_stick_y > 0 && robot.rightElevatorMotor.getCurrentPosition() > 8) {
            robot.rightElevatorMotor.setPower(-ELEVATOR_POWER);
        }
        else {
            robot.rightElevatorMotor.setPower(0);
        }

        if (gamepad2.x) {
            robotController.foundationGrabberDown();
        }
        if (gamepad2.y) {
            robotController.foundationGrabberUp();
        }
        if (gamepad2.right_bumper) {
            robotController.foundationGrabberAlmostDown();
        }
        if (gamepad2.dpad_up) {
            robotController.sgDeployUp();
        }
        if (gamepad2.dpad_down) {
            robotController.sgDeployDown();
        }

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        telemetry.addData("Elevator position","left (%d), right (%d)", robot.leftElevatorMotor.getCurrentPosition(),robot.rightElevatorMotor.getCurrentPosition());
        telemetry.addData("Left stick y", "(%.2f)", gamepad2.left_stick_y);

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }


    public void encoderGrabber(double speed,
                             int encoderTicks,
                             double timeoutS) {
        int newLeftTarget;


        // Determine new target position, and pass to motor controller
        newLeftTarget = robot.foundationGrabber.getCurrentPosition() + (encoderTicks);
        robot.foundationGrabber.setTargetPosition(newLeftTarget);

        // Turn On RUN_TO_POSITION
        robot.foundationGrabber.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // reset the timeout time and start motion.
        runtime.reset();
        robot.foundationGrabber.setPower(Math.abs(speed));

        // keep looping while we are still active, and there is time left, and both motors are running.
        // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
        // its target position, the motion will stop.  This is "safer" in the event that the robot will
        // always end the motion as soon as possible.
        // However, if you require that BOTH motors have finished their moves before the robot continues
        // onto the next step, use (isBusy() || isBusy()) in the loop test.
        while (
                (runtime.seconds() < timeoutS) &&
                (robot.foundationGrabber.isBusy())) {

            // Display it for the driver.
            telemetry.addData("Path1", "Running to %7d", newLeftTarget);
            telemetry.addData("Path2", "Running at %7d",
                    robot.foundationGrabber.getCurrentPosition());
            telemetry.update();

        }

        // Stop all motion;
        robot.foundationGrabber.setPower(0);

        // Turn off RUN_TO_POSITION
        robot.foundationGrabber.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //  sleep(250);   // optional pause after each move

    }

    public final void idle() {
        // Otherwise, yield back our thread scheduling quantum and give other threads at
        // our priority level a chance to run
        Thread.yield();
    }
}
