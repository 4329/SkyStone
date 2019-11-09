package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Autonomous Mode", group="Pushbot")
public class RedAutonomousMode extends AutonomousMode {
    @Override
    int colorDirection() {
        return -1;
    }
}
