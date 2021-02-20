package org.firstinspires.ftc.teamcode.teleop;

public class WobbleThread extends InputThread {


    enum WobbleState {
        RELAXED,
        PICKED,
        UPPED
    };

    int wobbleState = 0;

    public WobbleThread(TeleopBase opMode) {
        super(opMode);

    }

    @Override
    protected void cleanup() {

    }

    @Override
    protected void run() {
        if(opMode.gamepad1.y) {
            switch(wobbleState) {
                case 0: { // RELAXED
                    opMode.wobble.setRelaxed();
                    break;
                }
                case 1: { // PICKED
                    opMode.wobble.setPicked();
                    break;
                }
                case 2: {
                    opMode.wobble.setUpped();
                    break;
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wobbleState = (wobbleState + 1) % 3;
        }
    }
}
