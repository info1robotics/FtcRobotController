package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ge.Launcher;
import org.firstinspires.ftc.teamcode.ge.WobbleHandler;
import org.firstinspires.ftc.teamcode.movement.Chasis;
import org.firstinspires.ftc.teamcode.vision.WebcamVision;

abstract public class TeleopBase extends LinearOpMode {

    TeleopMovement movement;
    WobbleHandler wobble;
    Launcher launcher;


    @Override
    public void runOpMode() throws InterruptedException {
        movement = new TeleopMovement(this);
        wobble = new WobbleHandler(this);

        waitForStart();

        setup();





        while(opModeIsActive()) {
            Thread.yield();
        }
    }

    protected abstract void setup();

}
