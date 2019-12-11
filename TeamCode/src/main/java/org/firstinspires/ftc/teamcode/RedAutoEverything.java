package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Everything", group="Red")
public class RedAutoEverything extends RedAutoFoundationToLine {
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
