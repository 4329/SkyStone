package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Wall Crawl Auto", group = "Pushbot")
public class WallCrawlAuto extends AutonomousMode {
    @Override
    int colorDirection() {
        return 1;
    }

    @Override
    int colorDesiredAngle() {
        return 85;
    }

    @Override
    int zeroAngle() {
        return 0;
    }

    boolean isNotDesiredAngle(double firstAngle, double v, int direction) {
        return firstAngle < v;
    }

    @Override
    public void runOpMode() {

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

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way


        encoderDrive(DRIVE_SPEED, 24, 24, 5);
    }
}
