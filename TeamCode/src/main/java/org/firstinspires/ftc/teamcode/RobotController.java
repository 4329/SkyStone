package org.firstinspires.ftc.teamcode;

public class RobotController {


    private SkystoneHardwareMap robot;

    public RobotController(SkystoneHardwareMap robot) {
        this.robot = robot;
    }

    public void foundationGrabberDown(){
        robot.rightOutFoundationGrabber.setPosition(0.7);
        robot.rightInFoundationGrabber.setPosition(0.3);
        robot.leftInFoundationGrabber.setPosition(0.65);
        robot.leftOutFoundationGrabber.setPosition(0.35);


    }
    public void foundationGrabberUp(){
        robot.rightOutFoundationGrabber.setPosition(0);
        robot.rightInFoundationGrabber.setPosition(1.0);
        robot.leftInFoundationGrabber.setPosition(0);
        robot.leftOutFoundationGrabber.setPosition(1.0);

    }


}
