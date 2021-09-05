package org.firstinspires.ftc.teamcode.X;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class HolonomicOdometry {

    private static OpMode opMode;
    private static DcMotor leftEncoder, rightEncoder, backEncoder;
    private static int leftTicks, rightTicks, backTicks;
    private static float lateralDistance, backwardOffset, ticksTocm;
    private static String leftEncoderName, rightEncoderName, backEncoderName;

    public static Position currentPosition;

    public static void init(OpMode opMode, String leftEncoder, String rightEncoder, String backEncoder, float lateralDistance, float backwardOffset, float ticksTocm)
    {
        HolonomicOdometry.opMode = opMode;
        HolonomicOdometry.lateralDistance = lateralDistance;
        HolonomicOdometry.backwardOffset = backwardOffset;
        HolonomicOdometry.ticksTocm = ticksTocm;
        currentPosition = new Position(0, 0, 0);

        leftEncoderName = leftEncoder;
        rightEncoderName = rightEncoder;
        backEncoderName = backEncoder;

        HolonomicOdometry.leftEncoder = HolonomicOdometry.opMode.hardwareMap.get(DcMotor.class, leftEncoderName);
        HolonomicOdometry.rightEncoder = HolonomicOdometry.opMode.hardwareMap.get(DcMotor.class, rightEncoderName);
        HolonomicOdometry.backEncoder = HolonomicOdometry.opMode.hardwareMap.get(DcMotor.class, backEncoderName);

        leftTicks = HolonomicOdometry.leftEncoder.getCurrentPosition();
        rightTicks = HolonomicOdometry.rightEncoder.getCurrentPosition();
        backTicks = HolonomicOdometry.backEncoder.getCurrentPosition();


    }

    public static void updateOpMode(OpMode opMode)
    {
        HolonomicOdometry.opMode = opMode;
        leftEncoder = HolonomicOdometry.opMode.hardwareMap.get(DcMotor.class, leftEncoderName);
        rightEncoder = HolonomicOdometry.opMode.hardwareMap.get(DcMotor.class, rightEncoderName);
        backEncoder = HolonomicOdometry.opMode.hardwareMap.get(DcMotor.class, backEncoderName);
    }

    public static void setPosition(Position position)
    {
        currentPosition = position;
        currentPosition.normaliseAngle();
    }

    public static Position updatePosition()
    {
        int dLeftTicks = leftEncoder.getCurrentPosition();
        int drightTicks = rightEncoder.getCurrentPosition();
        int dbackTicks = backEncoder.getCurrentPosition();

        float dL = (dLeftTicks - leftTicks) / ticksTocm;
        float dR = (drightTicks - rightTicks) / ticksTocm;
        float dB = (dbackTicks - backTicks) / ticksTocm;

        leftTicks = dLeftTicks;
        rightTicks = drightTicks;
        backTicks = dbackTicks;

        float dh = (dR - dL) / lateralDistance;
        currentPosition.h += dh;

        if(Math.abs(dh) < 0.002)
        {
            currentPosition.x += dB;
            currentPosition.y += (dL + dR) / 2;
        }
        else {
            float rt = lateralDistance / 2 * (dL + dR) / (dR - dL);
            float rs = dB / dh - backwardOffset;
            currentPosition.x += rt * (Math.cos(dh) - 1) + rs * Math.sin(dh);
            currentPosition.y += rt * Math.sin(dh) + rs * (1 - Math.cos(dh));
        }
        currentPosition.normaliseAngle();
        return currentPosition;
    }



}
