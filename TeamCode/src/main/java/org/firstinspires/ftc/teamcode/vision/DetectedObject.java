package org.firstinspires.ftc.teamcode.vision;

public class DetectedObject {
    public ObjectCodes objectCode;

    public DetectedObject(ObjectCodes objectCode) {
        this.objectCode = objectCode;
    }

    public String toString() {return objectCode.toString(); }
}