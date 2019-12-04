package org.firstinspires.ftc.teamcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class BlueAutonomousModeTest {
    private BlueAutonomousMode testObject = new BlueAutonomousMode();


    @Test
    public void isNotDesiredAngle_turnLeft_15goingTo85() {
        assertTrue(testObject.isNotDesiredAngle(15, 85, 1));
    }
    @Test
    public void isNotDesiredAngle_turnLeft_85goingTo85() {
        assertFalse(testObject.isNotDesiredAngle(85, 85, 1));
    }
    @Test
    public void isNotDesiredAngle_turnLeft_100goingTo85() {
        assertFalse(testObject.isNotDesiredAngle(100, 85,1));
    }
    @Test
    public void isNotDesiredAngle_turnRight_85goingTo0() {
        assertTrue(testObject.isNotDesiredAngle(85, 0, -1));
    }
    @Test
    public void isNotDesiredAngle_turnRight_0goingTo0() {
        assertFalse(testObject.isNotDesiredAngle(0, 0, -1));
    }
    @Test
    public void isNotDesiredAngle_turnRight_negative15goingTo0() {
        assertFalse(testObject.isNotDesiredAngle(-15, 0, -1));
    }


}