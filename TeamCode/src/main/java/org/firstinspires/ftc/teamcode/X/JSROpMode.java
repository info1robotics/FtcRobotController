package org.firstinspires.ftc.teamcode.X;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.JSR.JSRuntimeWebSocketServer;

import java.io.IOException;

@TeleOp()
public class JSROpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        JSRuntimeWebSocketServer server = null;
        try {
            server = new JSRuntimeWebSocketServer( this);
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
            Thread.yield();
        }
    }
}
