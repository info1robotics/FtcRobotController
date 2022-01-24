package org.firstinspires.ftc.teamcode.base;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RR.drive.SampleMecanumDrive;

public class TeleOpBasicMoveWorker extends NonBlockingWorker {

    SampleMecanumDrive drive;

    public TeleOpBasicMoveWorker(LinearOpMode opMode) {
        super(opMode);
        drive = new SampleMecanumDrive(opMode.hardwareMap);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            drive.setWeightedDrivePower(new Pose2d(
                    opMode.gamepad1.left_stick_x,
                    opMode.gamepad1.left_stick_y,
                    Math.max(opMode.gamepad1.left_trigger, opMode.gamepad1.right_trigger)
            ));
            Thread.yield();
        }

    }
}
