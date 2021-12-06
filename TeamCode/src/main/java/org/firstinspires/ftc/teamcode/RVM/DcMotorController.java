package org.firstinspires.ftc.teamcode.RVM;

import android.util.Log;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DcMotorController {

	HashMap<String, DcMotor> motors;

	public DcMotorController(@NonNull HardwareMap hMap) {
		motors = new HashMap<String, DcMotor>();

		Iterator<Map.Entry<String, DcMotor>> iterator = hMap.dcMotor.entrySet().iterator();
		while(iterator.hasNext())
		{
			Map.Entry<String, DcMotor> entry = iterator.next();
			motors.put(entry.getKey(), entry.getValue());
		}
	}

	public void setPower(String motor, double power)
	{
		motors.get(motor).setPower(power);
	}
}
