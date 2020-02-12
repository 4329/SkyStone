package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Stone Across Line Skybridge", group="Blue")
public class BlueAutoStoneAcrossLineSkybridge extends BlueAutoFoundationToLine {
    @Override
    public void runOpMode() {
        initOpMode();
        pickupStoneFromStart();
        moveStoneAcrossLineSkybridge();

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
