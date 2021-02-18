package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

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

        motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setPower(Power power) {
        motorFL.setPower(power.fl);
        motorFR.setPower(power.fr);
        motorBL.setPower(power.bl);
        motorBR.setPower(power.br);
    }
    public void setPower(double speed) {
        motorFL.setPower(speed);
        motorFR.setPower(speed);
        motorBL.setPower(speed);
        motorBR.setPower(speed);
    }

    public void setMode(DcMotor.RunMode runMode) {
        motorFL.setMode(runMode);
        motorFR.setMode(runMode);
        motorBL.setMode(runMode);
        motorBR.setMode(runMode);
    }

    public void setTargetPosition(int position) {
        motorFL.setTargetPosition(position);
        motorFR.setTargetPosition(position);
        motorBL.setTargetPosition(position);
        motorBR.setTargetPosition(position);
    }

    public void setTargetPosition(TickGroup t) {
        motorFL.setTargetPosition(t.fl);
        motorFR.setTargetPosition(t.fr);
        motorBL.setTargetPosition(t.bl);
        motorBR.setTargetPosition(t.br);
    }

    public boolean isBusy() {
        return motorFL.isBusy() || motorFR.isBusy() || motorBL.isBusy()||
                motorBR.isBusy();
    }

}
