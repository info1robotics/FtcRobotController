package org.firstinspires.ftc.teamcode.RVM;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.internal.ui.GamepadUser;

import java.util.ArrayList;
import java.util.HashMap;

public class GamepadEx extends Gamepad {

	private final com.qualcomm.robotcore.hardware.Gamepad gamepad;

	public GamepadEx(Gamepad gamepad) {
		this.gamepad = gamepad;
	}
	public void update() {
		if(a == gamepad.a) a = false;
		else if(gamepad.a) a = true;

		if(x == gamepad.x) x = false;
		else if(gamepad.x) x = true;

		if(b == gamepad.b) b = false;
		else if(gamepad.b) b = true;

		if(y == gamepad.y) y = false;
		else if(gamepad.y) y = true;

		if(dpad_up == gamepad.dpad_up) dpad_up = false;
		else if(gamepad.dpad_up) dpad_up = true;

		if(dpad_left == gamepad.dpad_left) dpad_left = false;
		else if(gamepad.dpad_left) dpad_left = true;

		if(dpad_right == gamepad.dpad_right) dpad_right = false;
		else if(gamepad.dpad_right) dpad_right = true;

		if(dpad_down == gamepad.dpad_down) dpad_down = false;
		else if(gamepad.dpad_down) dpad_down = true;
	}


}
