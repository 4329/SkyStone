package org.firstinspires.ftc.teamcode;

public class RobotController {


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
        robot.stoneGrabberSupport.setPosition(0.60);
    }

    public void stoneGrabberSupportDown() {
        robot.stoneGrabberSupport.setPosition(1);
    }
}
