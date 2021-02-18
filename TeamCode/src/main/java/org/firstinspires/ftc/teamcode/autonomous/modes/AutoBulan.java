package org.firstinspires.ftc.teamcode.autonomous.modes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.ge.WobbleHandler;
import org.firstinspires.ftc.teamcode.movement.Chasis;
import org.firstinspires.ftc.teamcode.vision.WebcamVision;

@Config
@Autonomous
public class AutoBulan extends AutonomousBase {
    @Override
    protected void setup() {

    }

    @Override
    protected void run() {
        chasis.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        chasis.setTargetPosition(0);
        chasis.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        chasis.setTargetPosition(3000);
        chasis.setPower(0.5);
    }
}
