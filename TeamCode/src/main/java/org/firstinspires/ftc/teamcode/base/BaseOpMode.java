package org.firstinspires.ftc.teamcode.base;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@TeleOp
public abstract class BaseOpMode extends LinearOpMode {

    ExecutorService executor;

    protected NonBlockingWorker[] nonBlockingWorkers = new NonBlockingWorker[0];

    @Override
    public void runOpMode() throws InterruptedException {
        runInit();
        waitForStart();

        executor = Executors.newFixedThreadPool(6);

        executor.submit(() -> {
            try {
                nonBlockingWork();
            } catch (InterruptedException e) {
                telemetry.addLine("[INFO] Non Blocking Thread Interrupted");
                telemetry.update();
            }
        });
        executor.submit(() -> {
            try {
                runMain();
            } catch (InterruptedException e) {
                telemetry.addLine("[INFO] Main Thread Interrupted");
                telemetry.update();
            }
        });

        while(opModeIsActive() && !isStopRequested())
        {
            nonBlockingWork();
            telemetry.update();
            Thread.yield();
        }

        executor.shutdownNow();
        telemetry.addLine("[INFO] Awaiting tasks termination⏰\n[INFO] Stopping motors⚙️");
        telemetry.update();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        stopAllMotors();
        telemetry.addLine("[INFO] Tasks Terminated✅");
        telemetry.update();
    }

    protected abstract void runInit() throws InterruptedException;

    protected abstract void runMain() throws InterruptedException;

    void stopAllMotors() {
        for(DcMotor motor : hardwareMap.dcMotor) {
            motor.setPower(0);
        }
    }

    void nonBlockingWork() throws InterruptedException {
        for(NonBlockingWorker worker : nonBlockingWorkers) {
            worker.run();
        }
    }

    public Future<?> executeBlockingTask(Callable task) {
        if(!executor.isShutdown())
            return executor.submit(task);
        else
            return null;
    }


}
