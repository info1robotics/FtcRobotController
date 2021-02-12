package org.firstinspires.ftc.teamcode.teleop;

public class MovementThread extends InputThread {
    @Override
    protected void cleanup() { }

    double MIN_VAL = 0.10f;

    @Override
    protected void run() {
        double x = opMode.gamepad1.left_stick_x;
        double y = opMode.gamepad1.left_stick_y;
        double t = Math.max(opMode.gamepad1.left_trigger, opMode.gamepad1.right_trigger);
        if(x < MIN_VAL) x = 0;
        if(y < MIN_VAL) y = 0;
        if(t < MIN_VAL) t = 0;

        opMode.movement.moveXYT(x, y, t);
    }
}
