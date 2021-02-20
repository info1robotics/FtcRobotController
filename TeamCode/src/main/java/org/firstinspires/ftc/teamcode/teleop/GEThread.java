package org.firstinspires.ftc.teamcode.teleop;

public class GEThread extends InputThread {

    boolean risen = true;
    boolean hooked = false;
    boolean spinning = false;
    public GEThread(TeleopBase opMode) {
        super(opMode);
        //opMode.wobble.let();
    }

    @Override
    protected void cleanup() {

    }

    @Override
    protected void run() {
        if(opMode.gamepad1.b) {
//            if(risen) {
//                opMode.wobble.lower();
//            } else {
//                opMode.wobble.raise();
//            }
            risen = !risen;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(opMode.gamepad1.a) {
//            if(hooked) {
//                opMode.wobble.let();
//            } else {
//                opMode.wobble.get();
//            }
            hooked = !hooked;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(opMode.gamepad1.x) {
            if(hooked) {
                opMode.launcher.stop();
            } else {
                opMode.launcher.start();
            }
            spinning = !spinning;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
