package org.firstinspires.ftc.teamcode.JSR;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8ScriptCompilationException;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.X.GamepadEx;

import fi.iki.elonen.NanoWSD;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class JSRuntime {

    public V8 v8;
    ArrayList<NanoWSD.WebSocket> openSockets = new ArrayList<>();
    public LinearOpMode opMode;

    public void print(String arg) {
        System.out.println(arg);
        for(NanoWSD.WebSocket socket : openSockets) {
            try {
                socket.send(new WebSocketMessage(WebSocketMessage.OpType.SCRIPT_PRINT, arg).toJson());
            } catch (IOException err) {
                err.printStackTrace();
            }

        }
        if(opMode != null && opMode.opModeIsActive())
        {
            opMode.telemetry.log().add(arg);
        }

    }

    public void sleep(int milis) throws InterruptedException {
        Thread.sleep(milis);
    }

    public JSRuntime(LinearOpMode opMode, GamepadEx gamepad) throws IOException {

        this.opMode = opMode;

        v8 = V8.createV8Runtime();

        // Utils
        v8.registerJavaMethod(this, "print", "print", new Class<?>[] { String.class });
        v8.registerJavaMethod(this, "sleep", "sleep", new Class<?>[] { int.class });

        // Gamepad
        v8.registerJavaMethod(gamepad, "getButton", "getButton", new Class<?>[] { String.class });
        v8.registerJavaMethod(gamepad, "getButtonDown", "getButtonDown", new Class<?>[] { String.class });
        v8.registerJavaMethod(gamepad, "getButtonUp", "getButtonUp", new Class<?>[] { String.class });
        v8.registerJavaMethod(gamepad, "getAnalog", "getAnalog", new Class<?>[] { String.class });

        v8.getLocker().release();
    }


    public void loadScript(String data) {

        v8.getLocker().acquire();

        try {
            v8.executeVoidScript(data);
        } catch (V8ScriptCompilationException e) {
            e.printStackTrace();
        }

        v8.getLocker().release();

    }

    public void runInitScript() {
        v8.getLocker().acquire();
        v8.executeJSFunction("init");
        v8.getLocker().release();
    }

    public void runTeleOpScript() {
        v8.getLocker().acquire();
        v8.executeJSFunction("teleop");
        v8.getLocker().release();
    }

    public void runAutonomousScript() {
        v8.getLocker().acquire();
        v8.executeJSFunction("autonomous");
        v8.getLocker().release();
    }

    public void addSocket(NanoWSD.WebSocket socket) {
        openSockets.add(socket);
    }

    public void removeSocket(NanoWSD.WebSocket socket) {
        openSockets.remove(socket);
    }
}
