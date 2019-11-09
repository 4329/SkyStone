package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Autonomous Mode", group="Pushbot")
public class BlueAutonomousMode extends AutonomousMode {
    @Override
    int colorDirection() {
        return 1;
    }
}
