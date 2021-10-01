package org.firstinspires.ftc.teamcode.X;

import com.info1robotics.rvm.RVRuntime;

import org.firstinspires.ftc.teamcode.components.KekFunction;

public class RVAutoOpMode extends RVOpMode {
    @Override
    void runRoutine() {
        RVRuntime.getInstance().runAutonomousScript();
    }

    @Override
    void javaPrelude() {
        super.javaPrelude();

    }
}
