package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.movement.Chasis;
import org.firstinspires.ftc.teamcode.vision.WebcamVision;

@Autonomous()
abstract public class AutonomousBase extends LinearOpMode {

    protected Chasis chasis;
    protected WebcamVision webcamVision;

    @Override
    public void runOpMode() throws InterruptedException {
        chasis = new Chasis(this);
        webcamVision = new WebcamVision(this);
        before();
        while(!opModeIsActive()) {
            Thread.yield();
        }
        run();
    }

    protected abstract void before();

    protected abstract void run();

}
