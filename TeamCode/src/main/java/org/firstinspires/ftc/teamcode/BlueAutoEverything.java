package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Everything", group="Blue")
public class BlueAutoEverything extends BlueAutoFoundationToLine {
    @Override
    public void runOpMode() {
        initOpMode();

        moveFoundationInBuildZone();
        correctAngleAfterFoundation();
        foundationMoveStone();


        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }

}
