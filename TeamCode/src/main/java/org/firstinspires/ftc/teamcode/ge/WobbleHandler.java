package org.firstinspires.ftc.teamcode.ge;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class WobbleHandler {
   Servo servo;
   DcMotor dcMotor;

   private static final int RAISE_TICKS = 23;
   public WobbleHandler(LinearOpMode opMode) {
       servo = opMode.hardwareMap.get(Servo.class, "wobbleServo");
       dcMotor = opMode.hardwareMap.get(DcMotor.class, "wobbleDc");

       servo.scaleRange(0.2, 0.7);
       dcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }

   public void raise() {
       dcMotor.setTargetPosition(RAISE_TICKS);
       dcMotor.setPower(.7);
       dcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
   }

   public void lower() {
       dcMotor.setTargetPosition(-RAISE_TICKS);
       dcMotor.setPower(-.7);
       dcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
   }

   public void get() {
       servo.setPosition(1.0);
   }

   public void let() {
       servo.setPosition(0.0);
    }


}
