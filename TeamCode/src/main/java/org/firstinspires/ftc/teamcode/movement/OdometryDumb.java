package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.teleop.TeleopMovement;

public class OdometryDumb {
    Chasis chasis;
    LinearOpMode opMode;

    double currentX, currentY;

    public OdometryDumb(LinearOpMode opMode) {
        chasis = new Chasis(opMode);
        this.opMode = opMode;
    }

    public void moveRel(double x, double y) {
        chasis.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        TickGroup targetTick = new TickGroup(
                (int)(x + y),
                (int)(y - x),
                (int)(y - x),
                (int)(x + y)
        );
        chasis.setTargetPosition(targetTick);
        chasis.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        moveXYT(x, y, 0);
        while(chasis.isBusy() && opMode.opModeIsActive());

    }

    public void moveXYT(double x, double y, double t) {
        Power targetPower = new Power(
                (x + y + t),
                (y - x - t),
                (y - x + t),
                (x + y - t)
        );
        targetPower = normalizePower(targetPower);
        chasis.setPower(targetPower);
    }

    private Power normalizePower(Power power) {
        double fl = power.fl, fr = power.fr, bl = power.bl, br = power.br;
        double max = Math.max(
                Math.max(Math.abs(fl), Math.abs(fr)),
                Math.max(Math.abs(bl), Math.abs(br))
        );
        max = Math.max(max, 1.0);
        fr = fr / max;
        bl = bl / max;
        br = br / max;
        fl = fl / max;
        return new Power(fl, fr, bl, br);
    }
}
