package org.firstinspires.ftc.teamcode.X;

import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Function;
import com.info1robotics.rvm.RVRuntime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Mecanum;

import io.alicorn.v8.V8JavaAdapter;

abstract public class RVOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        javaPrelude();

        RVRuntime.getInstance().runInitScript();

        waitForStart();

        if(!opModeIsActive()) return;

        new Thread(this::runRoutine).start();

        GamepadEx gamepadEx = new GamepadEx(gamepad1);

        while (opModeIsActive())
        {
            idle();
        }

        RVRuntime.getInstance().v8.terminateExecution();



    }

    abstract void runRoutine();

    void javaPrelude() {
        RVRuntime.getInstance().v8.getLocker().acquire();

        V8JavaAdapter.injectObject("hardwareMap", hardwareMap, RVRuntime.getInstance().v8);
        V8JavaAdapter.injectClass(DcMotor.class, RVRuntime.getInstance().v8);

        RVRuntime.getInstance().v8.registerJavaMethod((v8Object, v8Array) -> {
            Thread t = new Thread() {
                @Override
                public void run() {
                    super.run();
                    ((V8Function)v8Array.get(0)).call(v8Object,
                            new V8Array(RVRuntime.getInstance().v8));
                }
            };
            t.start();
        }, "doAsync");



        RVRuntime.getInstance().v8.getLocker().release();
    }
}
