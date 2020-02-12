package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Stone Across Line Wall", group="Red")
public class RedAutoStoneAcrossLineWall extends RedAutoFoundationToLine {
    @Override
    public void runOpMode() {
        initOpMode();
        pickupStoneFromStart();
        moveStoneAcrossLineWall();

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
}