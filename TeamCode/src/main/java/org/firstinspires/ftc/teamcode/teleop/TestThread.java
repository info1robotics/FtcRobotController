package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.hardware.Servo;

public class TestThread extends InputThread {
    Servo servo1, servo2;

    public TestThread(TeleopBase opMode) {
        super(opMode);
        servo1 = opMode.hardwareMap.get(Servo.class, "wobble1");
        servo2 = opMode.hardwareMap.get(Servo.class, "wobble2");
    }


    @Override
    protected void cleanup() {

    }

    @Override
    protected void run() {
        if(opMode.gamepad1.a) {
            servo1.setPosition(0f);
        }
        if(opMode.gamepad1.y) {
            servo1.setPosition(1f);
        }
        if(opMode.gamepad1.x) {
            servo2.setPosition(0f);
        }
        if(opMode.gamepad1.b) {
            servo2.setPosition(1f);
        }
    }
}
