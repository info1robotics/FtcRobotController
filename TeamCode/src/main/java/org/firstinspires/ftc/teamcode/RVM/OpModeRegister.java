//package org.firstinspires.ftc.teamcode.RVM;
//
//import android.util.Log;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//
//import com.info1robotics.rvm.RVLocalStorage;
//import com.info1robotics.rvm.RVMOpModeData;
//import com.info1robotics.rvm.RVMOpModeRegisterUtility;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
//import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;
//
//import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;
//import org.firstinspires.ftc.teamcode.MultiOpMode;
//
//import java.lang.reflect.Constructor;
//import java.util.ArrayList;
//import java.util.Iterator;
//
//public class OpModeRegister {
//
//	@OpModeRegistrar
//	public static void registerOpModes(OpModeManager manager)
//	{
//
//		ArrayList<RVMOpModeData> opModes = RVMOpModeRegisterUtility.getOpModeData();
//		try {
//			Class clazz = Class.forName("org.firstinspires.ftc.teamcode.RVM.RVMOpModeSkeleton");
//			Constructor clazzConstructor = clazz.getConstructor();
//			for (RVMOpModeData opModeData : opModes)
//			{
//				LinearOpMode opMode = (LinearOpMode) clazzConstructor.newInstance();
//				clazz.getField("scriptPath").set(opMode, opModeData.file);
//
//				manager.register(new OpModeMeta.Builder()
//						.setFlavor((opModeData.flavour == 1 ? OpModeMeta.Flavor.TELEOP : OpModeMeta.Flavor.AUTONOMOUS))
//						.setName(opModeData.name)
//						.build(), opMode);
//			}
//
//		}
//		catch (Throwable e) {
//			System.err.println(e.getMessage());
//		}
//
//	}
//}
