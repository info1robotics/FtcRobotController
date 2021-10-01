package org.firstinspires.ftc.teamcode.X;

import com.info1robotics.rvm.RVRuntime;

import org.firstinspires.ftc.teamcode.components.KekFunction;
import org.firstinspires.ftc.teamcode.components.SleepFunction;

public class RVTeleOpMode extends RVOpMode{
    @Override
    void runRoutine() {
        RVRuntime.getInstance().runTeleOpScript();
    }

    @Override
    void javaPrelude() {
        super.javaPrelude();

        new KekFunction();

        MecanumDrivetrain mecanumDrivetrain = new MecanumDrivetrain(this,
                "motorFL", "motorFR", "motorBL", "motorBR");

        new SleepFunction();
    }
}
