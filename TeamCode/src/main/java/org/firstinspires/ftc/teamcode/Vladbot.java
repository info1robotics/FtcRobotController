package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RVM.GamepadEx;

@TeleOp
public class Vladbot extends LinearOpMode {

	int servoState = 0;
	boolean outtakeServoState = false;
	boolean intakeState = false;
	double[] servoStates = {0.0, 0.25, 0.50, 0.75};

	@Override
	public void runOpMode() throws InterruptedException {
		Mecanum mecanum = new Mecanum(this);
		DcMotor motor = hardwareMap.get(DcMotor.class, "motorOuttake");
		DcMotor motor2 = hardwareMap.get(DcMotor.class, "motorIntake");
		Servo servo = hardwareMap.get(Servo.class, "servoOuttake");
		Servo outtakeServo = hardwareMap.get(Servo.class, "servoOuttake2");

		servo.setPosition(0.0);
		GamepadEx g1 = new GamepadEx(gamepad1);
		GamepadEx g2 = new GamepadEx(gamepad2);

		waitForStart();
		while(opModeIsActive())
		{
			 g1.update();
			 g2.update();
			mecanum.vectorMove(-gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.left_trigger - gamepad1.right_trigger, gamepad1.right_bumper ? 0.5 : 1.0);
			motor.setPower((gamepad2.dpad_up ? 0.4 : 0) - (gamepad2.dpad_down ? 0.4 : 0));

//			telemetry.addLine(Boolean.toString(g1.getButton("a")));
//			telemetry.addLine(Boolean.toString(g2.getButton("b")));
//			telemetry.addLine(Boolean.toString(g2.getButton("dpad_up")));
//			telemetry.addLine(Boolean.toString(g2.getButton("dpad_down")));


			if(g2.getButtonDown("a"))
			{
				motor2.setPower(intakeState ? 1.0 : 0.0);
				intakeState = !intakeState;
			}

			if(g2.getButtonDown("y"))
			{
				motor2.setPower(intakeState ? -1.0 : 0.0);
				intakeState = !intakeState;
			}

			if(g2.getButtonDown("b"))
			{
				servoState++;
				servo.setPosition(servoStates[servoState % (servoStates.length)]);
			}

			if(g2.getButtonDown("x"))
			{
				servoState = servoStates.length - 1;
				servo.setPosition(servoStates[servoState % (servoStates.length)]);
			}

			if(g2.getButtonDown("bumper_right"))
			{
				outtakeServo.setPosition(outtakeServoState ? 0.0 : 1.0);
				outtakeServoState = !outtakeServoState;
			}

			telemetry.update();

		}
	}
}
