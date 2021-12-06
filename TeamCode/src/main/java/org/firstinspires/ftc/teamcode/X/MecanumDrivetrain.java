package org.firstinspires.ftc.teamcode.X;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MecanumDrivetrain{

    private DcMotor flMotor, frMotor, blMotor, brMotor;
    private double maxPower = 1.0f;

    public MecanumDrivetrain(OpMode opMode, String frontLeftMotor, String frontRightMotor, String backLeftMotor, String backRightMotor)
    {
        flMotor = opMode.hardwareMap.get(DcMotor.class, frontLeftMotor);
        frMotor = opMode.hardwareMap.get(DcMotor.class, frontRightMotor);
        blMotor = opMode.hardwareMap.get(DcMotor.class, backLeftMotor);
        brMotor = opMode.hardwareMap.get(DcMotor.class, backRightMotor);
    }


    public void Mecanum_setMaxPower(double maxPower)
    {
        this.maxPower = maxPower;
    }

    public double Mecanum_getMaxPower()
    {
        return maxPower;
    }

    // de implementat custom input curve
    public void Mecanum_moveTowards(float x, float y, float t)
    {
        float[] targetPower = normalize( new float[]{
                (x + y + t),
                (y - x - t),
                (y - x + t),
                (x + y - t)
        }, maxPower);

        flMotor.setPower(targetPower[0]);
        frMotor.setPower(targetPower[1]);
        blMotor.setPower(targetPower[2]);
        brMotor.setPower(targetPower[3]);

    }

    private float[] normalize(float[] values, double maxPower)
    {
        if (Math.abs(values[0]) > 1 || Math.abs(values[2]) > 1 ||
                Math.abs(values[1]) > 1 || Math.abs(values[3]) > 1) {
            double max = 0;
            max = Math.max(Math.abs(values[0]), Math.abs(values[2]));
            max = Math.max(Math.abs(values[1]), max);
            max = Math.max(Math.abs(values[3]), max);
            max = Math.min(max, maxPower);

            values[0] /= max;
            values[1] /= max;
            values[2] /= max;
            values[3] /= max;
        }
        return values;
    }
}
