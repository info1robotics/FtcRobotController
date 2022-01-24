package org.firstinspires.ftc.teamcode.mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Dictionary;
import java.util.concurrent.atomic.AtomicReference;

@Config
public class OuttakeMechanism {
    public enum State {
        LOADING,
        LOW,
        MID,
        HIGH
    }

    public static int LOADING_TICKS = 0;
    public static int LOW_TICKS = 0;
    public static int MID_TICKS = 0;
    public static int HIGH_TICKS = 0;

    AtomicReference<State> currentState = new AtomicReference<>();

    Thread workThread;

    OpMode opMode;

    DcMotor elevationMotor;
    Servo containerServo;


    public OuttakeMechanism(OpMode opMode) {
        this.opMode = opMode;

        elevationMotor = opMode.hardwareMap.get(DcMotor.class, "elevationMotor");
        containerServo = opMode.hardwareMap.get(Servo.class, "containerServo");

        elevationMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevationMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevationMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        containerServo.setPosition(0);

        setStateAsync(State.LOADING);


    }

    void setState(State state) {
        if(currentState.get() == state) return;

        currentState.set(state);
        switch (currentState.get()) {
            case LOADING: {
                containerServo.setPosition(0);
                goToTicks(LOADING_TICKS);
                break;
            }
            case LOW: {
                goToTicks(LOW_TICKS);
                break;
            }
            case MID: {
                goToTicks(MID_TICKS);
                break;
            }
            case HIGH: {
                goToTicks(HIGH_TICKS);
                break;
            }
        }
    }

    public void unloadGE() {
        containerServo.setPosition(1);
    }

    public void setStateAsync(State state) {
        // potentially unsafe; time wil tell..

        if(workThread != null && workThread.isAlive())
            workThread.interrupt();

        workThread = new Thread(() -> {
            setState(state);
        });
        workThread.start();
    }

    void goToTicks(int targetTicks)
    {
        elevationMotor.setTargetPosition(targetTicks);
        if(elevationMotor.getCurrentPosition() < elevationMotor.getTargetPosition()) {
            elevationMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        } else {
            elevationMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        elevationMotor.setPower(0.8);
        while(elevationMotor.isBusy() && !Thread.interrupted()) {
            Thread.yield();
        }
        elevationMotor.setPower(0);
    }

}
