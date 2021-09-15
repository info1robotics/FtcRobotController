package org.firstinspires.ftc.teamcode.X;

import com.info1robotics.rvm.RVRuntime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

abstract public class RVOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        javaPrelude();

        RVRuntime.getInstance().runInitScript();

        waitForStart();

        new Thread(this::runRoutine).start();

        GamepadEx gamepadEx = new GamepadEx(gamepad1);

        while (opModeIsActive())
        {
            gamepadEx.update();
            idle();
        }

        RVRuntime.getInstance().v8.terminateExecution();



    }

    abstract void runRoutine();

    abstract void javaPrelude();
}
