package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.movement.Chasis;
import org.firstinspires.ftc.teamcode.vision.WebcamVision;

abstract public class TeleopBase extends LinearOpMode {
    protected TeleopMovement movement;

    @Override
    public void runOpMode() throws InterruptedException {
        movement = new TeleopMovement(this);;
        setup();
        while(!opModeIsActive()) {
            Thread.yield();
        }
    }

    protected abstract void setup();

}
