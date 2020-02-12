package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Stone Across Line Wall", group="Blue")
public class BlueAutoStoneAcrossLineWall extends BlueAutoFoundationToLine {
    @Override
    public void runOpMode() {
        initOpMode();
        pickupStoneFromStart();
        moveStoneAcrossLineWall();

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}