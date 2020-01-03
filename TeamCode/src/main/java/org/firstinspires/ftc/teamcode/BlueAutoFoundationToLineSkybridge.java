package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


@Autonomous(name="Blue Foundation To Line Skybridge", group="Blue")
public class BlueAutoFoundationToLineSkybridge extends BlueAutoFoundationToLine {
    @Override
    public void runOpMode() {
        initOpMode();

        moveFoundationInBuildZone();
        returnToWall();
        driveToLine(29);


        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }

}
