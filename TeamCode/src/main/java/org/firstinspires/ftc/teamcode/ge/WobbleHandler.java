package org.firstinspires.ftc.teamcode.ge;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class WobbleHandler {
    private final LinearOpMode opMode;
    Servo servoSmall;
    Servo servoBig;
    DcMotor dcMotor;


    public WobbleHandler(LinearOpMode opMode) {
        this.opMode = opMode;
        servoSmall = opMode.hardwareMap.get(Servo.class, "wobble2");
        servoBig = opMode.hardwareMap.get(Servo.class, "wobble1");
        dcMotor = opMode.hardwareMap.get(DcMotor.class, "wobbleMotor");

        dcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        servoBig.setPosition(0.7f);
    }


    public void setPower(int direction)
    {
        dcMotor.setPower(direction * 0.5);
    }


    public void get()
    {
        servoSmall.setPosition(0f);
        servoBig.setPosition(0f);
    }

    public void setRelaxed() {
        servoSmall.setPosition(1f);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        servoBig.setPosition(1f);
    }

    public void setPicked() {
        servoBig.setPosition(0.4f);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        servoSmall.setPosition(0f);
    }

    public void setUpped() {
        servoBig.setPosition(0.7f);
    }
}
