package org.firstinspires.ftc.teamcode.specialized;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RR.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.IntakeMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.OuttakeMechanism;

import java.util.concurrent.atomic.AtomicReference;

@TeleOp(name = "TeleOp Version 0.1")
public class TeleOpV1 extends OpMode {

    SampleMecanumDrive drive;
    AtomicReference<WorkState> state;
    IntakeMechanism intakeMechanism;
    OuttakeMechanism outtakeMechanism;

    @Override
    public void init() {
        //drive = new SampleMecanumDrive(hardwareMap);
        state = new AtomicReference<>(WorkState.RESET);
        intakeMechanism = new IntakeMechanism(this);
        outtakeMechanism = new OuttakeMechanism(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        // Basic movement
//        drive.setWeightedDrivePower(new Pose2d(
//                gamepad1.left_stick_x,
//                gamepad1.left_stick_y,
//                Math.max(gamepad1.left_trigger, gamepad1.right_trigger)
//        ));

        workflow();

        if(gamepad1.y) {
            intakeMechanism.DEB_ToggleIntermediary();
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(gamepad1.dpad_up) {
            intakeMechanism.DEB_ToggleIntake();
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(gamepad1.dpad_down) {
            intakeMechanism.DEB_ToggleServo();
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        telemetry.update();

    }

    @Override
    public void stop() {

    }

    //////// Intake + Outtake worflow

    enum WorkState {
        RESET,
        INTAKE_RUNNING,
        SELECT_LEVEL_AND_CONFIRM,
    }

    public void workflow() {
        telemetry.addData("WORK STATE", state.get().toString());
        switch (state.get()) {
            case RESET: {
                if(gamepad1.a) {
                    state.compareAndSet(WorkState.RESET, WorkState.INTAKE_RUNNING);
                    intakeMechanism.startWorkAsync();
                } else
                break;
            }
            case INTAKE_RUNNING: {
                if(intakeMechanism.workHasFinished()) {
                    boolean caughtPiece = intakeMechanism.getLastResult();
                    if(caughtPiece) {
                        gamepad1.rumbleBlips(1);
                        state.compareAndSet(WorkState.INTAKE_RUNNING, WorkState.SELECT_LEVEL_AND_CONFIRM);
                    } else {
                        gamepad1.rumbleBlips(2);
                        state.set(WorkState.RESET);
                    }
                } else {
                    if(gamepad1.x) {
                        intakeMechanism.interruptWork();
                        state.set(WorkState.RESET);
                    }
                    if(gamepad1.b) {
                        intakeMechanism.toggleDirection();
                    }
                }

                break;
            }
            case SELECT_LEVEL_AND_CONFIRM: {
                if(gamepad1.b) {
                    outtakeMechanism.unloadGE();
                    outtakeMechanism.setStateAsync(OuttakeMechanism.State.LOADING);
                    state.compareAndSet(WorkState.SELECT_LEVEL_AND_CONFIRM, WorkState.RESET);
                } else if(gamepad1.dpad_down) {
                    outtakeMechanism.setStateAsync(OuttakeMechanism.State.LOW);
                } else if(gamepad1.dpad_left || gamepad1.dpad_right) {
                    outtakeMechanism.setStateAsync(OuttakeMechanism.State.MID);
                } else if(gamepad1.dpad_up) {
                    outtakeMechanism.setStateAsync(OuttakeMechanism.State.HIGH);
                }
            }
        }
    }
}
