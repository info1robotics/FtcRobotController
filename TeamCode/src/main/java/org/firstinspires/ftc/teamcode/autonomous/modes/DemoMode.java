package org.firstinspires.ftc.teamcode.autonomous.modes;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.vision.DetectedObject;

public class DemoMode extends AutonomousBase {

    @Override
    protected void before() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void run() {

    }


    protected void caseSingle() {

    }

    private void caseQuad() {

    }

    private void caseNoObject() {

    }
}
