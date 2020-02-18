package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Stone Across Line Skybridge", group="Red")
public class RedAutoStoneAcrossLineSkybridge extends RedAutoFoundationToLine {
    @Override
    public void runOpMode() {
        initOpMode();
        pickupStoneFromStart();
        moveStoneAcrossLineSkybridge();
        moveSecoundStoneAcrossLine(5);

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
}
