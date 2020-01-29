package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Foundation To Line Wall", group="Red")
public class RedAutoFoundationToLine extends AutonomousMode {
    @Override
    public void runOpMode() {
        initOpMode();

        moveFoundationInBuildZone();
        returnToWall();
        driveToLineWall(6);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }

    @Override
    int colorDirection() {
        return -1;
    }

    @Override
    int colorDesiredAngle() {
        return -83;
    }

    @Override
    int zeroAngle() {
        return -7;
    }

    boolean isNotDesiredAngle(double firstAngle, double v, int direction) {
        if (direction == 1) {
            return firstAngle < v;
        }
        else {
            return firstAngle > v;
        }
    }

    @Override
    int wallSmasher() {
        return -75;
    }
}
