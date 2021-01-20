package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Chasis implements Runnable {
    private final DcMotor motorFL, motorFR, motorBL, motorBR;
    private final OpMode opMode;
    public double fl, fr, bl, br;

    public Chasis(OpMode opMode) {
        this.opMode = opMode;
        motorFL = opMode.hardwareMap.get(DcMotor.class, "motorFL");
        motorFR = opMode.hardwareMap.get(DcMotor.class,"motorFR");
        motorBL = opMode.hardwareMap.get(DcMotor.class,"motorBL");
        motorBR = opMode.hardwareMap.get(DcMotor.class,"motorBR");
    }


    @Override
    public void run() {
        motorFL.setPower(fl);
        motorFR.setPower(fr);
        motorBL.setPower(bl);
        motorBR.setPower(br);
        Thread.yield();
    }

}
