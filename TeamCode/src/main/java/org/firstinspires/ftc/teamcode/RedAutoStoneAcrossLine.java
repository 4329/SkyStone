package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Stone Across Line", group="Red")
public class RedAutoStoneAcrossLine extends RedAutoFoundationToLine {
    @Override
    public void runOpMode() {
        initOpMode();
        moveStoneAcrossLine();

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
}
