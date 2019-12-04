package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Autonomous Mode", group="Pushbot")
public class RedAutonomousMode extends AutonomousMode {
    @Override
    int colorDirection() {
        return -1;
    }

    @Override
    int colorDesiredAngle() {
        return -85;
    }
    boolean isNotDesiredAngle(double firstAngle, double v, int direction) {
        if (direction == 1) {
            return firstAngle < v;
        }
        else {
            return firstAngle > v;
        }
    }
}
