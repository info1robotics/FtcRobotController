package org.firstinspires.ftc.teamcode.ge;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Launcher {
    Servo servo;
    DcMotor motorL, motorR;

    public Launcher(LinearOpMode opMode) {
        servo = opMode.hardwareMap.get(Servo.class, "launcherServo");
        motorL = opMode.hardwareMap.get(DcMotor.class, "launcherLeft");
        motorR = opMode.hardwareMap.get(DcMotor.class, "launcherRight");
    }

    public void start() {
        motorL.setPower(-1.0);
        motorR.setPower(1.0);

    }

    public void stop() {
        motorL.setPower(0);
        motorR.setPower(0);
    }

    public void push() {
        servo.setPosition(1.0);
    }

    public void unpush() {
        servo.setPosition(0.0);
    }



}

