package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.base.BaseOpMode;
import org.firstinspires.ftc.teamcode.base.NonBlockingWorker;
import org.firstinspires.ftc.teamcode.base.TeleOpBasicMoveWorker;
import org.firstinspires.ftc.teamcode.mechanisms.IntakeMechanism;

import java.util.concurrent.Future;

@TeleOp()
@Config
public class ColorTestOpMode extends BaseOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
    }

    @Override
    protected void runInit() throws InterruptedException {
        nonBlockingWorkers = new NonBlockingWorker[]{
                new TeleOpBasicMoveWorker(this)
        };

    }

    @Override
    protected void runMain() throws InterruptedException {
        IntakeMechanism intakeMechanism = new IntakeMechanism(this);

        Future<Boolean> intakeMechanismHandle;

        while(!Thread.interrupted())
        {
            if(gamepad1.a) {
            } else {
            }


            Thread.sleep(300);
            telemetry.addData("elementsCaught", intakeMechanism.getElementsCount());
            Thread.yield();
        }
    }
}
