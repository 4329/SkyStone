package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class SkystoneHardwareMap {
    public DcMotor frontLeftDrive = null;
    public DcMotor frontRightDrive = null;
    public DcMotor backLeftDrive = null;
    public DcMotor backRightDrive = null;
    public DcMotor foundationGrabber = null;
    public DcMotor leftElevatorMotor = null;
    public DcMotor rightElevatorMotor = null;

    public Servo stoneGrabberServo;
    public Servo leftOutFoundationGrabber;
    public Servo rightOutFoundationGrabber;
    public Servo leftInFoundationGrabber;
    public Servo rightInFoundationGrabber;

    private RobotController robotController = new RobotController(this);

    public void init(HardwareMap hardwareMap) {

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        frontLeftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        backRightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        foundationGrabber = hardwareMap.get(DcMotor.class, "foundation_grabber");
        //stoneGrabberServo = hardwareMap.get(Servo.class, "stone_grabber");
        leftElevatorMotor = hardwareMap.get(DcMotor.class, "left_elevator_motor");
        rightElevatorMotor = hardwareMap.get(DcMotor.class, "right_elevator_motor");
        leftOutFoundationGrabber = hardwareMap.get(Servo.class, "left_out_foundation_grabber");
        leftInFoundationGrabber = hardwareMap.get(Servo.class, "left_in_foundation_grabber");
        rightOutFoundationGrabber = hardwareMap.get(Servo.class, "right_out_foundation_grabber");
        rightInFoundationGrabber = hardwareMap.get(Servo.class, "right_in_foundation_grabber");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);
        foundationGrabber.setDirection(DcMotor.Direction.FORWARD);
        leftElevatorMotor.setDirection(DcMotor.Direction.REVERSE);
        rightElevatorMotor.setDirection(DcMotor.Direction.REVERSE);
        leftElevatorMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightElevatorMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftElevatorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightElevatorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftElevatorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightElevatorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        robotController.foundationGrabberUp();
    }
}
