package org.firstinspires.ftc.teamcode.RVM;

import com.qualcomm.robotcore.hardware.Gamepad;

public class InputManager {

	GamepadEx g1, g2;

	public InputManager(Gamepad gamepad1, Gamepad gamepad2)
	{
		g1 = new GamepadEx(gamepad1);
		g2 = new GamepadEx(gamepad2);
	}

	public void update()
	{
		g1.update();
		g2.update();
	}

}
