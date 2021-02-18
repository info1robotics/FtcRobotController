package org.firstinspires.ftc.teamcode.autonomous.modes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.movement.OdometryDumb;
import org.firstinspires.ftc.teamcode.movement.Power;
import org.firstinspires.ftc.teamcode.movement.TickGroup;
import org.firstinspires.ftc.teamcode.vision.DetectedObject;

@Config
@Autonomous
public class AutoVision extends AutonomousBase {

    private DetectedObject detectedObject;


    OdometryDumb odometry;
    @Override
    protected void setup() {
        odometry = new OdometryDumb(this);
    }

    @Override
    protected void run() {
//        while(opModeIsActive())
//        {
//                    while(opModeIsActive()) {
//                        webcamVision.getAllDetections();
//                }
//        }
//        odometry.moveTo(0, 3600);
//        odometry.moveTo(200, 0);
        DetectedObject object = webcamVision.getFrontDetection();

        switch(object.objectCode) {
            case NO_OBJECT: {
                caseNoObject();
                break;
            }
            case SINGLE: {
                caseSingle();
                break;
            }
            case QUAD: {
                caseQuad();
                break;
            }
        }


    }


    protected void caseSingle() {
        odometry.moveRel(-800, 6000);
        odometry.moveRel(800, -1300);
    }

    private void caseQuad() {
        odometry.moveRel(900, 7500);
        odometry.moveRel(0, -2600);
//        moveLeft(300);
    }

//    private void moveLeft(int ticks) {
//        chasis.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        chasis.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        chasis.setTargetPosition(new TickGroup(
//                ticks,
//                -ticks,
//                -ticks,
//                ticks
//        ));
//        chasis.setPower(new Power(
//                1f,
//                -1f,
//                -1f,
//                1
//        ));
//        while(chasis.isBusy());
//    }

    private void caseNoObject() {
        odometry.moveRel(600, 4700);
        odometry.moveRel(0, -500);

    }
}
