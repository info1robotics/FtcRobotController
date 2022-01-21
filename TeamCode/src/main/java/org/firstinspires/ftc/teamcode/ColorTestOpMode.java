package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.mechanisms.IntakeMechanism;

@TeleOp()
@Config
public class ColorTestOpMode extends LinearOpMode {




    @Override
    public void runOpMode() throws InterruptedException {
        IntakeMechanism intakeMechanism = new IntakeMechanism(this);

        boolean temp = false;

        Servo intakeServo = hardwareMap.get(Servo.class, "intakeServo");

        while(!isStarted()) {
            if(gamepad1.a) {
                if(intakeMechanism.isEngaged()) {
                    intakeMechanism.setIsEngaged(false);
                }
                else {
                    intakeMechanism.actionateAsync(caughtPiece -> {
                        if (caughtPiece) {
                            telemetry.speak("Event piece in!");
                        } else {
                            telemetry.speak("Piece not caught!");
                        }
                        telemetry.addData("caughtPiece", caughtPiece);
                    });
                }
                Thread.sleep(300);
            }
            if(gamepad1.b) {
                if(temp) {
                    intakeServo.setPosition(1);
                }
                else {
                    intakeServo.setPosition(0);
                }
                temp = !temp;
                Thread.sleep(300);
            }
            telemetry.addData("elementsCaught", intakeMechanism.getElementsCount());
            telemetry.update();
            Thread.yield();
        }
    }
}
