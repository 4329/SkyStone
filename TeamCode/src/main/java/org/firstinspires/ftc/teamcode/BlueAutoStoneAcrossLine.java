package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Stone Across Line", group="Blue")
public class BlueAutoStoneAcrossLine extends BlueAutoFoundationToLine {
    @Override
    public void runOpMode() {
        initOpMode();
        moveStoneAcrossLine();
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
