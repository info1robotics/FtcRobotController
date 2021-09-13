package org.firstinspires.ftc.teamcode.JSR;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.X.GamepadEx;
import org.firstinspires.ftc.teamcode.X.JSROpMode;

import fi.iki.elonen.NanoWSD;

import java.io.IOException;

public class JSRuntimeWebSocketServer extends NanoWSD {
    private static final int PORT = 3005;

    public JSRuntime runtime;
    public LinearOpMode opMode;

    public JSRuntimeWebSocketServer(LinearOpMode opMode, GamepadEx gamepad) throws IOException {
        super(PORT);
        this.opMode = opMode;
        runtime = new JSRuntime(opMode, gamepad);

    }

    @Override
    protected WebSocket openWebSocket(IHTTPSession handshake) {
        return new JSRuntimeWebSocket(handshake, runtime);
    }
}
