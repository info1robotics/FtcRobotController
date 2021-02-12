package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.movement.Chasis;
import org.firstinspires.ftc.teamcode.movement.Power;

public class TeleopMovement {
    Chasis chasis;

    public TeleopMovement(LinearOpMode opMode) {
        chasis = new Chasis(opMode);
    }

    public void moveXYT(double x, double y, double t) {
        Power movementPower = new Power(
                (x + y + t),
                (y - x - t),
                (y - x + t),
                (x + y - t)
        );

        movementPower = normalizePower(movementPower);
        chasis.setPower(movementPower);
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
