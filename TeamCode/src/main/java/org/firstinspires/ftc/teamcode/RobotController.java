package org.firstinspires.ftc.teamcode;

public class RobotController {
    public static final int CORE_HEX_90_DEGREES = 65;
    public static final double ELEVATOR_POWER = 0.425;
    public static final double ELEVATOR_UP_POWER = 1;


    private SkystoneHardwareMap robot;

    public RobotController(SkystoneHardwareMap robot) {
        this.robot = robot;
    }

    public void foundationGrabberDown() {
        robot.rightOutFoundationGrabber.setPosition(0.7);
        robot.rightInFoundationGrabber.setPosition(0.3);
        robot.leftInFoundationGrabber.setPosition(0.65);
        robot.leftOutFoundationGrabber.setPosition(0.35);
    }

    public void foundationGrabberUp() {
        robot.rightOutFoundationGrabber.setPosition(0);
        robot.rightInFoundationGrabber.setPosition(1.0);
        robot.leftInFoundationGrabber.setPosition(0);
        robot.leftOutFoundationGrabber.setPosition(1.0);
    }

    public void foundationGrabberAlmostDown() {
        robot.rightOutFoundationGrabber.setPosition(0.55);
        robot.rightInFoundationGrabber.setPosition(0.45);
        robot.leftInFoundationGrabber.setPosition(0.5);
        robot.leftOutFoundationGrabber.setPosition(0.5);
    }
    public void sgDeployUp() {
        robot.rightSGDeploy.setPosition(0);
        robot.leftSGDeploy.setPosition(1);
    }

    public void sgDeployDown() {
        stoneGrabberSupportUp();
        robot.rightSGDeploy.setPosition(0.325);
        robot.leftSGDeploy.setPosition(0.675);
    }

    public void stoneGrabberUp() {
        robot.stoneGrabberServo.setPosition(0.5);
    }
    public void stoneGrabberDown() {

        robot.stoneGrabberServo.setPosition(0);
    }


    public void stoneGrabberSupportUp() {
        robot.stoneGrabberSupport.setPosition(0.71);
    }

    public void stoneGrabberSupportDown() {
        robot.stoneGrabberSupport.setPosition(1);
    }
    public void stoneGrabberSupportInit() {
        robot.stoneGrabberSupport.setPosition(0.6);
    }
    public void liftBlock()  {
        robot.leftElevatorMotor.setPower(ELEVATOR_UP_POWER);
        robot.rightElevatorMotor.setPower(ELEVATOR_UP_POWER);

        while (robot.leftElevatorMotor.getCurrentPosition() < CORE_HEX_90_DEGREES && robot.rightElevatorMotor.getCurrentPosition() < CORE_HEX_90_DEGREES){
            idle();
        }
        robot.leftElevatorMotor.setPower(0);
        robot.rightElevatorMotor.setPower(0);

    }

    public final void idle() {
        // Otherwise, yield back our thread scheduling quantum and give other threads at
        // our priority level a chance to run
        Thread.yield();
    }
    public void liftAndSupport() {
        liftBlock();
        stoneGrabberSupportDown();
    }

}
