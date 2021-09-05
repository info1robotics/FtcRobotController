package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp
public class Vladbot extends LinearOpMode {
	@Override
	public void runOpMode() throws InterruptedException {
		DcMotor motor = hardwareMap.get(DcMotor.class, "motorGlisiera");
		waitForStart();
		while(opModeIsActive())
		{
			motor.setPower((gamepad1.dpad_up ? 0.4 : 0) - (gamepad1.dpad_down ? 0.4 : 0));
		}
	}
}
