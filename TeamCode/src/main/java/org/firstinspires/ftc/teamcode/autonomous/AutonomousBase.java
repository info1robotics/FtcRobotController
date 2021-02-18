package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ge.WobbleHandler;
import org.firstinspires.ftc.teamcode.movement.Chasis;
import org.firstinspires.ftc.teamcode.vision.WebcamVision;

@Autonomous()
abstract public class AutonomousBase extends LinearOpMode {

    protected Chasis chasis;
    protected WebcamVision webcamVision;
    protected WobbleHandler wobble;

    @Override
    public void runOpMode() throws InterruptedException {
        webcamVision = new WebcamVision(this);
        chasis = new Chasis(this);
        wobble = new WobbleHandler(this);
        setup();
        waitForStart();
        run();
        while(opModeIsActive());
    }

    protected abstract void setup();

    protected abstract void run();

}
