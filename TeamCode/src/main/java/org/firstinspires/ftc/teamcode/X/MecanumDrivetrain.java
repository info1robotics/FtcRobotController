package org.firstinspires.ftc.teamcode.X;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MecanumDrivetrain {

    private static OpMode opMode;
    private static DcMotor flMotor, frMotor, blMotor, brMotor;
    private static String flMotorName, frMotorName, blMotorName, brMotorName;
    private static float maxPower;

    public static void init(OpMode opMode, String frontLeftMotor, String frontRightMotor, String backtLeftMotor, String backRightMotor, float maxPower)
    {
        MecanumDrivetrain.opMode = opMode;
        MecanumDrivetrain.maxPower = maxPower;

        flMotorName = frontLeftMotor;
        frMotorName = frontRightMotor;
        blMotorName = backtLeftMotor ;
        brMotorName = backRightMotor;

        flMotor = MecanumDrivetrain.opMode.hardwareMap.get(DcMotor.class, flMotorName);
        frMotor = MecanumDrivetrain.opMode.hardwareMap.get(DcMotor.class, frMotorName);
        blMotor = MecanumDrivetrain.opMode.hardwareMap.get(DcMotor.class, blMotorName);
        brMotor = MecanumDrivetrain.opMode.hardwareMap.get(DcMotor.class, brMotorName);
    }

    public static void updateOpMode(OpMode opMode)
    {
        MecanumDrivetrain.opMode = opMode;
        flMotor = MecanumDrivetrain.opMode.hardwareMap.get(DcMotor.class, flMotorName);
        frMotor = MecanumDrivetrain.opMode.hardwareMap.get(DcMotor.class, frMotorName);
        blMotor = MecanumDrivetrain.opMode.hardwareMap.get(DcMotor.class, blMotorName);
        brMotor = MecanumDrivetrain.opMode.hardwareMap.get(DcMotor.class, brMotorName);
    }

    public static void setMaxPower(float maxPower)
    {
        MecanumDrivetrain.maxPower = maxPower;
    }
    public static float getMaxPower()
    {
        return maxPower;
    }

    // de implementat custom input curve
    public static void moveTowards(float x, float y, float t)
    {
        float[] targetPower = normalize( new float[]{
                (x + y + t),
                (y - x - t),
                (y - x + t),
                (x + y - t)
        }, maxPower);

        flMotor.setPower(targetPower[0] * maxPower);
        frMotor.setPower(targetPower[1] * maxPower);
        blMotor.setPower(targetPower[2] * maxPower);
        brMotor.setPower(targetPower[3] * maxPower);

    }

    private static float[] normalize(float[] values, float maxPower)
    {
        if (Math.abs(values[0]) > 1 || Math.abs(values[2]) > 1 ||
                Math.abs(values[1]) > 1 || Math.abs(values[3]) > 1) {
            double max = 0;
            max = Math.max(Math.abs(values[0]), Math.abs(values[2]));
            max = Math.max(Math.abs(values[1]), max);
            max = Math.max(Math.abs(values[3]), max);

            values[0] /= max;
            values[1] /= max;
            values[2] /= max;
            values[3] /= max;
        }
        return values;
    }
}
