package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.ConfigurationTypeManager;

import org.firstinspires.ftc.teamcode.X.GamepadEx;

@TeleOp
public class Vladbot extends LinearOpMode {

	boolean servoState = false;

	@Override
	public void runOpMode() throws InterruptedException {
		Mecanum mecanum = new Mecanum(this);
		DcMotor motor = hardwareMap.get(DcMotor.class, "motorGlisiera");
		DcMotor motor2 = hardwareMap.get(DcMotor.class, "motor2");
		Servo servo = hardwareMap.get(Servo.class, "servo");
		servo.setPosition(1.0);
		GamepadEx gamepad = new GamepadEx(gamepad1);
		waitForStart();
		while(opModeIsActive())
		{
			 gamepad.update();
			mecanum.vectorMove(-gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.left_trigger - gamepad1.right_trigger, gamepad1.right_bumper ? 0.5 : 1.0);
			motor.setPower((gamepad1.dpad_up ? 0.4 : 0) - (gamepad1.dpad_down ? 0.4 : 0));
			motor2.setPower((gamepad1.dpad_up ? 0.4 : 0) - (gamepad1.dpad_down ? 0.4 : 0));
			if(gamepad.getButtonDown("a"))
			{
				servo.setPosition(servoState ? 1.0 : 0.0);
				servoState = !servoState;
			}
		}
	}
}
