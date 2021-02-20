package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleopDefault extends TeleopBase {


    protected MovementThread movementThread;
    protected GEThread geThread;
    protected WobbleThread wobbleThread;

    @Override
    protected void setup() {
        telemetry.speak("set up");
        telemetry.update();
        wobbleThread = new WobbleThread(this);
        movementThread = new MovementThread(this);
        geThread = new GEThread(this);
    }
}
