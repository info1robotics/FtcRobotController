package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeManagerImpl;

@Disabled
public class MultiOpMode extends LinearOpMode {

	private OpModeManagerImpl opModeManager;

	@Override
	public void runOpMode() throws InterruptedException {
		this.opModeManager = (OpModeManagerImpl) internalOpModeServices; //Store OpModeManagerImpl
		String opName = opModeManager.getActiveOpModeName();
		telemetry.addLine(opName);
		telemetry.update();
		sleep(5000);
	}
}
