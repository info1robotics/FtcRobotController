package org.firstinspires.ftc.teamcode.X;

import com.info1robotics.rvm.RVRuntime;

public class RVTeleOpMode extends RVOpMode{
    @Override
    void runRoutine() {
        RVRuntime.getInstance().runTeleOpScript();
    }

    @Override
    void javaPrelude() {

    }
}
