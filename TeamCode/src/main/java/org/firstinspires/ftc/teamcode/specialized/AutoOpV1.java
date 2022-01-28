package org.firstinspires.ftc.teamcode.specialized;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RR.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.RR.util.AssetsTrajectoryManager;

import java.util.Map;

@Config
@Autonomous
public class AutoOpV1 extends LinearOpMode {

    public static int CASE = 1;
    public static Pose2d STARTING_POSE = new Pose2d(12, -66.59, Math.toRadians(0));

    SampleMecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        runInit();
        waitForStart();
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(STARTING_POSE);
        switch (CASE) {
            case 1: {
                case1();
                break;
            }
            case 2: {
                case2();
                break;
            }
            case 3: {
                case3();
                break;
            }
            default: {
                break;
            }
        }
    }



    private void case1() {
//        Trajectory t = AssetsTrajectoryManager.load("trig");
//        drive.followTrajectorySequence(drive.trajectorySequenceBuilder(STARTING_POSE)
//            .setTangent(Math.toRadians(180))
//            .splineTo(new Vector2d(0, 0), 0)
//            .build());

        drive.followTrajectorySequence(drive.trajectorySequenceBuilder(STARTING_POSE)
            .lineTo(new Vector2d(48, -66.59))
            .waitSeconds(1)
            .lineTo(new Vector2d(12, -66.59))
            .splineToConstantHeading(new Vector2d(0, -43.5), Math.toRadians(0))

        .build());
//        drive.followTrajectory(t);
    }

    private void case2() {

    }

    private void case3() {

    }

    private void runInit() {

    }




}
