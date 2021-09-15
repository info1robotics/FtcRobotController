package com.info1robotics.rvm;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;

public class RVRuntimeMessage {

    enum OpType {
        INVALID,
        PRECOMPILE,
        PRINT
    }

    OpType opType;
    JsonObject messageData;

    public RVRuntimeMessage(String json) {
        JsonParser jsonParser = new JsonParser();

        messageData = jsonParser.parse(json).getAsJsonObject();
        opType = OpType.valueOf(messageData.get("opType").getAsString());
    }

    public RVRuntimeMessage(OpType opType) {
        this.opType = opType;
        messageData = new JsonObject();
        messageData.addProperty("opType", opType.name());
    }

    public String toJson() {
        return messageData.toString();
    }

    public OpType getOpType() {
        return opType;
    }

    public String getScript() {
        return messageData.get("script").getAsString();
    }

    public void setPrintMessage(String message) {
        messageData.addProperty("message", message);
    }
}
