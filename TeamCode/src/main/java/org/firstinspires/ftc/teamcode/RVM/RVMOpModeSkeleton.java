package org.firstinspires.ftc.teamcode.RVM;

import com.eclipsesource.v8.V8;
import com.info1robotics.rvm.RVLocalStorage;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcore.internal.opmode.OpModeManagerImpl;

public class RVMOpModeSkeleton extends LinearOpMode {

	V8 v8;
	public String scriptPath;

	OpModeManagerImpl opModeManager;


	public void addTelemetry(String t)
	{
		telemetry.addLine(t);
		telemetry.update();
	}

	public boolean isRVMDebug()
	{
		return FtcRobotControllerActivity.RVM_IS_KEY_ACTIVE;
	}

	@Override
	public void runOpMode() throws InterruptedException {

		// ---- java controllers and utilities ----
		DcMotorController motorController = new DcMotorController(hardwareMap);
		InputManager inputManager = new InputManager(gamepad1, gamepad2);
		// ----------------------------------------

		// opMode java event loop
		new Thread(new Runnable() {
			public void run() {
				while(!isStopRequested())
				{

					// ---- java event loop ----
					inputManager.update();
					// -------------------------
					Thread.yield();
				}
				// ---- opMode end ----
				v8.terminateExecution();
				// --------------------
			}
		}
		).start();

		opModeManager = (OpModeManagerImpl) internalOpModeServices;

		v8 = V8.createV8Runtime();
		v8.executeVoidScript(RVLocalStorage.getInstance().readFile(scriptPath));

		// ---- Inject Java methods ----
		v8.registerJavaMethod(this, "isRVMDebug", "isRVMDebugActive", new Class[]{});
		v8.registerJavaMethod(this, "addTelemetry", "log", new Class[]{String.class});

		v8.registerJavaMethod(motorController, "setPower", "setPower", new Class[]{String.class, double.class});

		v8.registerJavaMethod(inputManager, "getAnalog", "getAnalog", new Class[]{int.class, String.class});
		v8.registerJavaMethod(inputManager, "getButton", "getButton", new Class[]{int.class, String.class});
		v8.registerJavaMethod(inputManager, "getButtonDown", "getButtonDown", new Class[]{int.class, String.class});
		v8.registerJavaMethod(inputManager, "getButtonUp", "getButtonUp", new Class[]{int.class, String.class});
		v8.registerJavaMethod(inputManager, "update", "pollInput", new Class[]{});
		// ----------------------------

		// execute
		v8.executeJSFunction("init");
		waitForStart();
		v8.executeJSFunction("run");

		v8.getLocker().release();
	}}
