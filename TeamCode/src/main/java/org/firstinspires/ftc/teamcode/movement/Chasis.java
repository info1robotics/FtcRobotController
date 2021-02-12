package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Chasis {
    private final DcMotor motorFL, motorFR, motorBL, motorBR;
    private final LinearOpMode opMode;

    public Chasis(LinearOpMode opMode) {
        this.opMode = opMode;
        motorFL = opMode.hardwareMap.get(DcMotor.class, "motorFL");
        motorFR = opMode.hardwareMap.get(DcMotor.class,"motorFR");
        motorBL = opMode.hardwareMap.get(DcMotor.class,"motorBL");
        motorBR = opMode.hardwareMap.get(DcMotor.class,"motorBR");

        motorFR.setDirection(DcMotor.Direction.REVERSE);
        motorBR.setDirection(DcMotor.Direction.REVERSE);
    }

    public void setPower(Power power) {
        motorFL.setPower(power.fl);
        motorFR.setPower(power.fr);
        motorBL.setPower(power.bl);
        motorBR.setPower(power.br);
    }

}
