package org.firstinspires.ftc.teamcode.autonomous.modes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.vision.DetectedObject;

@Autonomous
public class AutoVision extends AutonomousBase {

    private DetectedObject detectedObject;
    @Override
    protected void setup() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void run() {
        while(opModeIsActive()) {
            webcamVision.getAllDetections();
        }
//        switch (detectedObject.objectCode) {
//            case SINGLE: {
//                caseSingle();
//                break;
//            }
//            case QUAD: {
//                caseQuad();
//                break;
//            }
//            case NO_OBJECT: {
//                caseNoObject();
//                break;
//            }
//
//        }
        while(opModeIsActive());
    }


    protected void caseSingle() {

    }

    private void caseQuad() {

    }

    private void caseNoObject() {

    }
}
