package org.firstinspires.ftc.teamcode.JSR;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoWSD;

import java.io.IOException;

public class JSRuntimeWebSocket extends NanoWSD.WebSocket {

    private JSRuntime runtime;

    JSRuntimeWebSocket(NanoHTTPD.IHTTPSession handshakeRequest, JSRuntime runtime) {
        super(handshakeRequest);
        this.runtime = runtime;
    }
    @Override
    protected void onOpen() {
        runtime.addSocket(this);
    }

    @Override
    protected void onClose(NanoWSD.WebSocketFrame.CloseCode code, String reason, boolean initiatedByRemote) {
        runtime.removeSocket(this);
    }

    @Override
    protected void onMessage(NanoWSD.WebSocketFrame message) {
        System.out.println(message.getTextPayload());
        try {
            WebSocketMessage wMessage = WebSocketMessage.fromJson(message.getTextPayload());

            switch (wMessage.getOpType()) {
                case LOAD_SCRIPT : {
                    runtime.loadScript(wMessage.getData());
                    break;
                }
                case RUN_SCRIPT : {
                    runtime.runInitScript();
                    runtime.runAutonomousScript();
                    break;
                }
            }
        } catch (Exception e) {
            try {
                send(e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    protected void onPong(NanoWSD.WebSocketFrame pong) {

    }

    @Override
    protected void onException(IOException exception) {

    }
}
