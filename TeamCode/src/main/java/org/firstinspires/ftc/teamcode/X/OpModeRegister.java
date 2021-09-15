package org.firstinspires.ftc.teamcode.X;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;

public class OpModeRegister {

	@OpModeRegistrar
	public static void registerOpModes(OpModeManager manager)
	{
		manager.register(
				new OpModeMeta.Builder().setName("RVAuto").setFlavor(OpModeMeta.Flavor.AUTONOMOUS).build(),
				RVAutoOpMode.class
		);

		manager.register(
				new OpModeMeta.Builder().setName("RVTeleop").setFlavor(OpModeMeta.Flavor.TELEOP).build(),
				RVTeleOpMode.class
		);

	}
}
