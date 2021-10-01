package com.info1robotics.rvm;

import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import com.eclipsesource.v8.V8Value;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoWSD;

import java.io.IOException;

public class RVRuntimeWebSocket extends NanoWSD.WebSocket {



    RVRuntimeWebSocket(NanoHTTPD.IHTTPSession handshakeRequest) {
        super(handshakeRequest);
        RVRuntime.getInstance().registerPrintCallback(new JavaVoidCallback() {
            @Override
            public void invoke(V8Object receiver, V8Array parameters) {
                StringBuilder messageBuilder = new StringBuilder();
                for(int i = 0; i < parameters.length(); i++) {
                    switch(parameters.getType(i)) {
                        case V8Value.STRING:
                             messageBuilder.append(parameters.getString(i));
                            break;
                        case V8Value.INTEGER:
                            messageBuilder.append(parameters.getInteger(i));
                            break;
                        case V8Value.BOOLEAN:
                            messageBuilder.append(parameters.getBoolean(i));
                            break;
                        case V8Value.BYTE:
                            messageBuilder.append(parameters.getByte(i));
                            break;
                        case V8Value.DOUBLE:
                            messageBuilder.append(parameters.getDouble(i));
                            break;
                        case V8Value.NULL:
                            messageBuilder.append("null");
                            break;
                        case V8Value.UNDEFINED:
                            messageBuilder.append("undefined");
                            break;
                        default:
                            messageBuilder.append("!!unimp!!");
                            break;
                    }
                    messageBuilder.append(" ");
                }
                RVRuntimeMessage message = new RVRuntimeMessage(RVRuntimeMessage.OpType.PRINT);
                message.setPrintMessage(messageBuilder.toString());
                try {
                    send(message.toJson());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
    @Override
    protected void onOpen() {

    }

    @Override
    protected void onClose(NanoWSD.WebSocketFrame.CloseCode code, String reason, boolean initiatedByRemote) {

    }

    @Override
    protected void onMessage(NanoWSD.WebSocketFrame message) {
        System.out.println(message.getTextPayload());
        try {
            RVRuntimeMessage wMessage = new RVRuntimeMessage(message.getTextPayload());

            switch (wMessage.getOpType()) {
                case PRECOMPILE: {
                    RVRuntime.getInstance().loadScript(wMessage.getScript());
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
