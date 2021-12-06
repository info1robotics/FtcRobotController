package com.info1robotics.rvm;


import fi.iki.elonen.NanoWSD;


public class RVRuntimeWebSocketServer extends NanoWSD {
    private static final int PORT = 3005;


    public RVRuntimeWebSocketServer() {
        super(PORT);

    }

    @Override
    protected WebSocket openWebSocket(IHTTPSession handshake) {
        return new RVRuntimeWebSocket(handshake);
    }


}
