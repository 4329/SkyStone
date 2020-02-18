package org.firstinspires.ftc.teamcode;

public class RobotController {
    public static final int CORE_HEX_90_DEGREES = 65;
    public static final double ELEVATOR_DOWN_POWER = -0.425;
    public static final double ELEVATOR_UP_POWER = 1;
    public static final int CONTROLLER_DELAY = 200;
    public boolean isStoneGrabberSupportRetracted = true;
    public boolean isStoneGrabberUp = false;
    public boolean isLeftFoundationGrabberUp = true;
    public boolean isRightFoundationGrabberUp = true;
    public boolean isLeftTriggerDown = false;
    public boolean isRightTriggerDown = false;


    private SkystoneHardwareMap robot;

    public RobotController(SkystoneHardwareMap robot) {
        this.robot = robot;
    }

    public void foundationGrabberDown() {
        robot.rightOutFoundationGrabber.setPosition(0.65);
        robot.rightInFoundationGrabber.setPosition(0.35);
        robot.leftInFoundationGrabber.setPosition(0.65);
        robot.leftOutFoundationGrabber.setPosition(0.35);
        isLeftFoundationGrabberUp = false;
        isRightFoundationGrabberUp = false;
    }

    public void foundationGrabberUp() {
        robot.rightOutFoundationGrabber.setPosition(0);
        robot.rightInFoundationGrabber.setPosition(1.0);
        robot.leftInFoundationGrabber.setPosition(0);
        robot.leftOutFoundationGrabber.setPosition(1.0);
        isLeftFoundationGrabberUp = true;
        isRightFoundationGrabberUp = true;
    }

    public void foundationGrabberAlmostDown() {
        robot.rightOutFoundationGrabber.setPosition(0.55);
        robot.rightInFoundationGrabber.setPosition(0.45);
        robot.leftInFoundationGrabber.setPosition(0.5);
        robot.leftOutFoundationGrabber.setPosition(0.5);
        isLeftFoundationGrabberUp = false;
        isRightFoundationGrabberUp = false;
    }

    public void leftFoundationGrabberUp(){
        robot.leftInFoundationGrabber.setPosition(0);
        robot.leftOutFoundationGrabber.setPosition(1.0);
        isLeftFoundationGrabberUp = true;
    }

    public void rightFoundationGrabberUp() {
        robot.rightOutFoundationGrabber.setPosition(0);
        robot.rightInFoundationGrabber.setPosition(1.0);
        isRightFoundationGrabberUp = true;
    }

    public void leftFoundationGrabberDown(){
        robot.leftInFoundationGrabber.setPosition(0.65);
        robot.leftOutFoundationGrabber.setPosition(0.35);
        isLeftFoundationGrabberUp = false;
    }

    public void rightFoundationGrabberDown(){
        robot.rightOutFoundationGrabber.setPosition(0.65);
        robot.rightInFoundationGrabber.setPosition(0.35);
        isRightFoundationGrabberUp = false;
    }

    public void sgDeployUp() {
        robot.rightSGDeploy.setPosition(0);
        robot.leftSGDeploy.setPosition(1);
    }

    public void sgDeployDown() {
        stoneGrabberSupportRetracted();
        robot.rightSGDeploy.setPosition(0.55);
        robot.leftSGDeploy.setPosition(0.45);
    }

    public void sgDeployInit() {
        sgDeployUp();
        stoneGrabberDown();
        stoneGrabberSupportDeployed();
    }

//    public void sgDeployWayBack() {
//        robot.rightSGDeploy.setPosition(0);
//        robot.leftSGDeploy.setPosition(1);
//    }

    public void stoneGrabberUp() {
        robot.stoneGrabberServo.setPosition(0.5);
        sleep(CONTROLLER_DELAY);
        isStoneGrabberUp = true;
    }

    public void stoneGrabberDown() {
        robot.stoneGrabberServo.setPosition(0);
        sleep(CONTROLLER_DELAY);
        isStoneGrabberUp = false;
    }


    public void stoneGrabberSupportRetracted() {
        robot.stoneGrabberSupport.setPosition(0.71);
        sleep(CONTROLLER_DELAY);
        isStoneGrabberSupportRetracted = true;
    }

    public void stoneGrabberSupportDeployed() {
        robot.stoneGrabberSupport.setPosition(1);
        sleep(CONTROLLER_DELAY);
        isStoneGrabberSupportRetracted = false;
    }
    public void stoneGrabberSupportInit() {
        robot.stoneGrabberSupport.setPosition(0.6);
        isStoneGrabberSupportRetracted = true;
    }
    public void liftBlock()  {
        robot.leftElevatorMotor.setPower(ELEVATOR_UP_POWER);
        robot.rightElevatorMotor.setPower(ELEVATOR_UP_POWER);

        while (robot.leftElevatorMotor.getCurrentPosition() < CORE_HEX_90_DEGREES*2 && robot.rightElevatorMotor.getCurrentPosition() < CORE_HEX_90_DEGREES*2){
            idle();
        }
        robot.leftElevatorMotor.setPower(0);
        robot.rightElevatorMotor.setPower(0);

    }
    public int getElevatorMinimum(){
        if (isStoneGrabberSupportRetracted){
            return 8;
        }
        return CORE_HEX_90_DEGREES;
    }

    public final void idle() {
        // Otherwise, yield back our thread scheduling quantum and give other threads at
        // our priority level a chance to run
        Thread.yield();
    }
    public void liftAndSupport() {
        liftBlock();
        stoneGrabberSupportDeployed();
    }

    public void elevatorDown() {
        robot.leftElevatorMotor.setPower(ELEVATOR_DOWN_POWER);
        robot.rightElevatorMotor.setPower(ELEVATOR_DOWN_POWER);

        while (robot.rightElevatorMotor.getCurrentPosition() > getElevatorMinimum()){
            idle();
        }
        robot.leftElevatorMotor.setPower(0);
        robot.rightElevatorMotor.setPower(0);
    }


    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public void liftAndUnsupport() {
        liftBlock();
        stoneGrabberSupportRetracted();

    }
}
