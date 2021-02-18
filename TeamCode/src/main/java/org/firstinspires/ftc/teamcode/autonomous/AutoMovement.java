package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.teleop.InputThread;
import org.firstinspires.ftc.teamcode.teleop.TeleopBase;

public class AutoMovement{
    Thread thread;
    TeleopBase opMode;
    public AutoMovement(final TeleopBase opMode) {
        this.opMode = opMode;
        thread = new Thread() {
            @Override
            public void run() {
                super.run();
                while(opMode.opModeIsActive()) {
                    AutoMovement.this.run();
                    opMode.telemetry.update();
                    Thread.yield();
                }
                cleanup();
            }
        };
        thread.setName(getClass().getName());
        thread.start();
    }

    protected void cleanup()
    {

    }

    protected void run()
    {
    }



}
