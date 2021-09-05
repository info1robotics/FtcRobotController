package org.firstinspires.ftc.teamcode.X;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.ArrayList;
import java.util.HashMap;

public class Gamepad {

	private com.qualcomm.robotcore.hardware.Gamepad gamepad;


	/*
	Digital input:
	0	START
	1	BACK
	2	MODE
	3	A
	4	B
	5	X
	6	Y
	7	DPAD_UP
	8	DPAD_RIGHT
	9	DPAD_DOWN
	10	DPAD_LEFT
	11	BUMPER_LEFT
	12	BUMPER_RIGHT
	13	JOYSTICK_LEFT
	14	JOYSTICK_RIGHT
	Analog input:
	0	JOYSTICK_LEFT_X
	1	JOYSTICK_LEFT_Y
	2	JOYSTICK_RIGHT_X
	3	JOYSTICK_RIGHT_Y
	4	TRIGGER_LEFT
	5	TRIGGER_RIGHT
	*/
	private boolean[] buttonState = new boolean[15];
	private boolean[] buttonDown = new boolean[15];
	private boolean[] buttonUp = new boolean[15];

	// not used
	private float[] analog = new float[6];

	private class Joystick {
		public float x = 0, y = 0;
	}
	public Joystick leftJoystick, rightJoystick;
	public float leftTrigger, rightTrigger;

	// TODO update to java 8 way when possible
	public static final HashMap<String, Integer> buttonName = new HashMap<String, Integer>() {{
		put("start", 1);
		put("back", 1);
		put("mode", 1);
		put("a", 1);
		put("b", 1);
		put("x", 1);
		put("y", 1);
		put("dpad_up", 1);
		put("dpad_right", 1);
		put("dpad_down", 1);
		put("dpad_left", 1);
		put("bumper_left", 1);
		put("bumper_right", 1);
		put("joystick_left", 1);
		put("joystick_right", 1);
	}};
	public void Gamepad(com.qualcomm.robotcore.hardware.Gamepad gamepad)
	{
		this.gamepad = gamepad;
		update();
	}

	public void update()
	{
		leftJoystick.x 		= gamepad.left_stick_x;
		leftJoystick.y 		= gamepad.left_stick_y;
		rightJoystick.x 	= gamepad.right_stick_x;
		rightJoystick.y 	= gamepad.right_stick_y;
		leftTrigger 		= gamepad.left_trigger;
		rightTrigger 		= gamepad.right_trigger;

		boolean[] newButtonState = new boolean[15];

		newButtonState[0] 	= gamepad.start;
		newButtonState[1] 	= gamepad.back;
		newButtonState[2] 	= false;
		// TODO find better way to ignore start
		newButtonState[3] 	= gamepad.a & !newButtonState[0]; // ignore start + a
		newButtonState[4] 	= gamepad.b & !newButtonState[0]; // ignore start + b
		newButtonState[5] 	= gamepad.x;
		newButtonState[6] 	= gamepad.y;
		newButtonState[7] 	= gamepad.dpad_up;
		newButtonState[8] 	= gamepad.dpad_right;
		newButtonState[9] 	= gamepad.dpad_down;
		newButtonState[10] 	= gamepad.dpad_left;
		newButtonState[11] 	= gamepad.left_bumper;
		newButtonState[12] 	= gamepad.right_bumper;
		newButtonState[13] 	= gamepad.left_stick_button;
		newButtonState[14] 	= gamepad.right_stick_button;

		for(int i = 0; i < 15; i++)
		{
			buttonDown[i] = newButtonState[i] && (newButtonState[i] != buttonState[i]);
			buttonUp[i] = (!newButtonState[i]) && (newButtonState[i] != buttonState[i]);
		}
		buttonState = newButtonState;
	}

	public boolean getButton(String button)
	{
		return buttonState[buttonName.get(button)];
	}

	public boolean getButtonDown(String button)
	{
		return buttonDown[buttonName.get(button)];

	}

	public boolean getButtonUp(String button)
	{
		return buttonUp[buttonName.get(button)];
	}
}
