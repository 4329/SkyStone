package org.firstinspires.ftc.teamcode;

public class RobotController {


    private SkystoneHardwareMap robot;

    public RobotController(SkystoneHardwareMap robot) {
        this.robot = robot;
    }

    public void foundationGrabberDown(){
        robot.rightFoundationGrabber.setPosition(0.8);
        robot.leftFoundationGrabber.setPosition(0);

    }
    public void foundationGrabberUp(){
        robot.rightFoundationGrabber.setPosition(0);
        robot.leftFoundationGrabber.setPosition(0.81);

    }


}
