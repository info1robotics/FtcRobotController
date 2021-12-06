package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

@Autonomous
public class AutoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

//        DcMotor motor = hardwareMap.get(DcMotor.class, "motor");
//        waitForStart();
//        while (opModeIsActive())
//        {

        telemetry.addLine("Boot key: " + FtcRobotControllerActivity.RVM_IS_KEY_ACTIVE);

//        DigitalChannel RVkey =  hardwareMap.get(DigitalChannel.class, "RVM_SERVER_STATE_KEY");
//      HardwareMap hMap = eventLoop.getOpModeManager().getHardwareMap();
//      DigitalChannel RVkey = hMap.get(DigitalChannel.class, "key");
//        RVkey.setMode(DigitalChannel.Mode.INPUT);
//        telemetry.addLine("Current key: " + RVkey.getState());

        telemetry.update();
//
        waitForStart();
//        }

//        DcMotor fl = hardwareMap.get(DcMotor.class, "motorFL");
//        DcMotor fr = hardwareMap.get(DcMotor.class, "motorFR");
//        DcMotor bl = hardwareMap.get(DcMotor.class, "motorBL");
//        DcMotor br = hardwareMap.get(DcMotor.class, "motorBR");
//        fr.setDirection(DcMotorSimple.Direction.REVERSE);
//        br.setDirection(DcMotorSimple.Direction.REVERSE);
//        waitForStart();
//
//        double power = 0.6;
//        fl.setPower(1.0 * power);
//        fr.setPower(-1.0 * power);
//        bl.setPower(-1.0 * power);
//        br.setPower(1.0 * power);
//        sleep(1000);
//        fl.setPower(0);
//        fr.setPower(0);
//        bl.setPower(0);
//        br.setPower(0);

    }
}
