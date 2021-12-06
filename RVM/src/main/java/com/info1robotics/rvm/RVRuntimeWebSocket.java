package com.info1robotics.rvm;

import android.util.Log;

import com.eclipsesource.v8.JavaCallback;
import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import com.eclipsesource.v8.V8Value;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoWSD;

import java.io.IOException;

public class RVRuntimeWebSocket extends NanoWSD.WebSocket {

    enum OpType {
        LOAD_PROJECT,
        START_ROBOT,
        STOP_ROBOT,
        GET_PROJECT,
        GET_HASHES,
    }

    Gson gson;

    RVMOps ops;

    RVRuntimeWebSocket(NanoHTTPD.IHTTPSession handshakeRequest) {
        super(handshakeRequest);

        ops = new RVMOps();
        gson = new Gson();

    }
    @Override
    protected void onOpen() {

    }

    @Override
    protected void onClose(NanoWSD.WebSocketFrame.CloseCode code, String reason, boolean initiatedByRemote) {

    }

    @Override
    protected void onMessage(NanoWSD.WebSocketFrame message) {
        try {
            JsonObject obj = gson.fromJson(message.getTextPayload(), JsonObject.class).getAsJsonObject();
            OpType op = OpType.valueOf(obj.get("op").getAsString());
            int status = obj.get("status").getAsInt();
            JsonObject data = obj.get("data").getAsJsonObject();
            System.out.println(data.toString());
            switch (op)
            {

                case LOAD_PROJECT:
                    ops.loadProject(data);
                    break;
                case START_ROBOT:
                    break;
                case STOP_ROBOT:
                    break;
            }


        } catch (Exception e) {
            try {
                Log.d("RVM_DEBUG", e.getMessage());
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
