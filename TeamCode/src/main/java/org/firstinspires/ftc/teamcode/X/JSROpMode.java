package org.firstinspires.ftc.teamcode.X;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.JSR.JSRuntimeWebSocketServer;

import java.io.IOException;
@Disabled
@TeleOp()
public class JSROpMode extends LinearOpMode {

    public GamepadEx gamepad;
    @Override
    public void runOpMode() throws InterruptedException {

        gamepad = new GamepadEx(gamepad1);

        JSRuntimeWebSocketServer server = null;
        try {
            server = new JSRuntimeWebSocketServer( this, gamepad);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(server.runtime.v8.getLocker().hasLock() && isStarted()) {
            Thread.yield();
        }


        waitForStart();


        try {
            server.start(0, true);
        } catch (IOException e) {
            e.printStackTrace();
        }


        while(opModeIsActive()) {
            gamepad.update();
            Thread.yield();
        }

        server.runtime.v8.terminateExecution();
        server.stop();
    }


}
