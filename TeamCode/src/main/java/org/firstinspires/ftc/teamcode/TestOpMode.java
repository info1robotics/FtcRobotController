package org.firstinspires.ftc.teamcode;

import android.icu.text.Transliterator;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.Position;
@Disabled
@TeleOp
public class TestOpMode extends LinearOpMode {

    boolean right_bumper_state = false;

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor fl = hardwareMap.get(DcMotor.class, "motorFL");
        DcMotor fr = hardwareMap.get(DcMotor.class, "motorFR");
        DcMotor bl = hardwareMap.get(DcMotor.class, "motorBL");
        DcMotor br = hardwareMap.get(DcMotor.class, "motorBR");

        Mecanum mecanum = new Mecanum(this);
        waitForStart();
        while(opModeIsActive())
        {

            mecanum.vectorMove(-gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.left_trigger - gamepad1.right_trigger, gamepad1.right_bumper ? 0.5 : 1.0);
        }
    }
}
