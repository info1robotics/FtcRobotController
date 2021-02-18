package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.ge.WobbleHandler;

@TeleOp
public class teleopTest extends LinearOpMode {

    DcMotor motor;

    @Override
    public void runOpMode() throws InterruptedException {

        motor = hardwareMap.get(DcMotor.class, "motor");

        waitForStart();


        motor.setPower(1.0);


        while(opModeIsActive()) {
            Thread.yield();
        }
    }
}
