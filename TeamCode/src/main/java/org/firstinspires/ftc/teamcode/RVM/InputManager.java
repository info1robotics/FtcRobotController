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

	public float getAnalog(int gamepad, String analog) {return gamepad == 1 ? g1.getAnalog(analog) : g2.getAnalog(analog);}

	public boolean getButton(int gamepad, String button)
	{
		return gamepad == 1 ? g1.getButton(button) : g2.getButton(button);
	}

	public boolean getButtonDown(int gamepad, String button)
	{
		return gamepad == 1 ? g1.getButtonDown(button) : g2.getButtonDown(button);

	}

	public boolean getButtonUp(int gamepad, String button)
	{
		return gamepad == 1 ? g1.getButtonUp(button) : g2.getButtonUp(button);
	}
}
