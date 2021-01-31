package org.firstinspires.ftc.teamcode.autonomous.modes;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.vision.DetectedObject;

public class AutoVision extends AutonomousBase {

    private DetectedObject detectedObject;
    @Override
    protected void before() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        detectedObject = webcamVision.getFrontDetection();
    }

    @Override
    protected void run() {
        switch (detectedObject.objectCode) {
            case SINGLE: {
                caseSingle();
                break;
            }
            case QUAD: {
                caseQuad();
                break;
            }
            case NO_OBJECT: {
                caseNoObject();
                break;
            }

        }
    }


    protected void caseSingle() {

    }

    private void caseQuad() {

    }

    private void caseNoObject() {

    }
}
