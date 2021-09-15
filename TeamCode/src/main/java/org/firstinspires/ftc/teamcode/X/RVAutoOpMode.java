package org.firstinspires.ftc.teamcode.X;

import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import com.info1robotics.rvm.RVRuntime;

import org.firstinspires.ftc.teamcode.Mecanum;

public class RVAutoOpMode extends RVOpMode {
    @Override
    void runRoutine() {
        RVRuntime.getInstance().runAutonomousScript();
    }

    @Override
    void javaPrelude() {
        RVRuntime.getInstance().v8.getLocker().acquire();
        Mecanum mecanum = new Mecanum(this);
        RVRuntime.getInstance().v8.registerJavaMethod((v8Object, v8Array) -> {
            // move(1, 1, 0, 0.3)
            double[] dirs = v8Array.getDoubles(0, 4);
            mecanum.vectorMove(dirs[0], dirs[1], dirs[2], dirs[3]);
        }, "move");
        RVRuntime.getInstance().v8.getLocker().release();
    }
}
