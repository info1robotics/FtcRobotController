package org.firstinspires.ftc.teamcode.ge;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class WobbleHandler {
    private final LinearOpMode opMode;
    Servo servo;
   DcMotor dcMotor;

   private static final int RAISE_TICKS = 110;
   public WobbleHandler(LinearOpMode opMode) {
       this.opMode = opMode;
       servo = opMode.hardwareMap.get(Servo.class, "wobbleServo");
       dcMotor = opMode.hardwareMap.get(DcMotor.class, "wobbleDc");

       dcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       dcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

   }

   public void raiseInit() {
       dcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       dcMotor.setTargetPosition(165);
       dcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       dcMotor.setPower(.7);

       opMode.telemetry.addLine("init raising");
   }

   public void raise() {
       dcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       dcMotor.setTargetPosition(RAISE_TICKS);
       dcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       dcMotor.setPower(.7);

       opMode.telemetry.addLine("raising");
   }

   public void lower() {
       dcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       dcMotor.setTargetPosition(-RAISE_TICKS);
       dcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       dcMotor.setPower(-.7);
       opMode.telemetry.addLine("lowering");
   }

   public void get() {
       servo.setPosition(1.0);
   }

   public void let() {
       servo.setPosition(0.0);
    }


}
