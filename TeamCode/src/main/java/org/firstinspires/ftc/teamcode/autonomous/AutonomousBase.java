package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.movement.Chasis;

@Autonomous()
abstract public class AutonomousBase extends LinearOpMode {

    protected Chasis chasis;
    AutonomousBase() {
        chasis = new Chasis(this);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        before();
        while(!opModeIsActive()) {
            Thread.yield();
        }
        run();
    }

    abstract void before();

    abstract void run();

}
