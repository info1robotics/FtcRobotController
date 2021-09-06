package org.firstinspires.ftc.teamcode.JSR;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WebSocketMessage {

    enum OpType {
        LOAD_SCRIPT,
        RUN_SCRIPT,
        SCRIPT_PRINT,
    }

    private OpType opType;
    private String data;

    public WebSocketMessage(OpType opType, String data) {
        this.opType = opType;
        this.data = data;

    }

    public OpType getOpType() {
        return opType;
    }

    public String getData() {
        return data;
    }

    public String toJson() {
        JsonObject root = new JsonObject();
        root.addProperty("opType", opType.name());
        root.addProperty("data", data);

        return root.toString();
    }

    static WebSocketMessage fromJson(String jsonString) {
        JsonParser j = new JsonParser();

        JsonObject root = j.parse(jsonString).getAsJsonObject();

        OpType opType = OpType.valueOf(root.get("opType").getAsString());
        String data = root.get("data").getAsString();

        return new WebSocketMessage(opType, data);
    }
}
