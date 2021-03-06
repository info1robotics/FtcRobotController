package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.movement.Chasis;
import org.firstinspires.ftc.teamcode.movement.Power;

public class TeleopMovement {
    Chasis chasis;

    private Power currentPower;

    public TeleopMovement(LinearOpMode opMode) {
        chasis = new Chasis(opMode);
        currentPower = new Power(0);
    }

    public void moveXYT(double x, double y, double t) {
        Power targetPower = new Power(
                (x + y + t),
                (y - x - t),
                (y - x + t),
                (x + y - t)
        );

        targetPower = normalizePower(targetPower);
        currentPower.add(targetPower.minus(currentPower).divide(32));
        if(targetPower.isZero()) currentPower = new Power(0);
        chasis.setPower(currentPower);
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
