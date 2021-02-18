package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleopDefault extends TeleopBase {


    protected MovementThread movementThread;
    protected GEThread geThread;

    @Override
    protected void setup() {
        telemetry.speak("set up");
        telemetry.update();
        movementThread = new MovementThread(this);
        geThread = new GEThread(this);
    }
}
