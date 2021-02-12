package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

abstract public class InputThread {
    Thread thread;
    TeleopBase opMode;

    public InputThread() {
        thread = new Thread() {
            @Override
            public void run() {
                super.run();
                while(opMode.opModeIsActive()) {
                    InputThread.this.run();
                }
                cleanup();
            }
        };
        thread.setName(getClass().getName());
        thread.start();
    }

    protected abstract void cleanup();

    protected abstract void run();

}
