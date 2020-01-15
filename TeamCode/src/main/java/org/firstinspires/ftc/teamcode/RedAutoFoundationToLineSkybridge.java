package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name="Red Foundation To Line Skybridge", group="Red")
public class RedAutoFoundationToLineSkybridge extends RedAutoFoundationToLine {
    @Override
    public void runOpMode() {
        initOpMode();

        moveFoundationInBuildZone();
        returnToWall();
        driveToLine(21);


        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }

}
