package org.firstinspires.ftc.teamcode.mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.OpenCV.InsideDetectPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

@Config
public class IntakeMechanism {

    // FFF IMPORTANT LA TUNING: FALSE POSITIVES MULT MAI OK DECAT FALSE NEGATIVES!
    public static double PIXELS_RATIO = 0.53f;
    public static double DETECTION_COOLDOWN_SEC = 0.4;
    public static double DETECTION_TRY_TIME_SEC = 100;
    public static double INTAKE_MOTOR_POWER = -1;
    // "motorFL" -- temporary hack
    public static String INTAKE_MOTOR_NAME = "motorFL";
    public static String INTAKE_SERVO_NAME = "intakeServo";
    public static double INTAKE_SERVO_ENGAGED_POS = 1.0f;
    public static double INTAKE_SERVO_IDLE_POS = 0.0f;



    DcMotor intakeMotor;
    Servo intakeServo;

    WebcamName webcamName;
    OpenCvCamera camera;
    InsideDetectPipeline insideDetectPipeline;

    OpMode opMode;

    int elementsCount = 0;

    final AtomicBoolean isEngaged = new AtomicBoolean(false);



    public IntakeMechanism(OpMode opMode) {
        int cameraMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());

        insideDetectPipeline = new InsideDetectPipeline(opMode.telemetry);


        webcamName = opMode.hardwareMap.get(WebcamName.class, "Webcam 1");
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
        camera.openCameraDevice();
        camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
        camera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
        camera.setPipeline(insideDetectPipeline);

        intakeMotor = opMode.hardwareMap.get(DcMotor.class, INTAKE_MOTOR_NAME);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intakeServo = opMode.hardwareMap.get(Servo.class, INTAKE_SERVO_NAME);

        this.opMode = opMode;
    }

    public int getElementsCount() { return elementsCount; }

    public boolean isEngaged() { return isEngaged.get(); }

    public void setIsEngaged(boolean value) { isEngaged.set(value); }


    boolean actionate()
    {
        // (positively?) insane method for changing a variable in inner and reading it in outer
        final boolean[] giveUp = {false};

        intakeMotor.setPower(INTAKE_MOTOR_POWER);
        intakeServo.setPosition(INTAKE_SERVO_ENGAGED_POS);

        Timer giveUpTimer = new Timer();

        giveUpTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                giveUp[0] = true;
            }
        }, (int)(DETECTION_TRY_TIME_SEC * 1000));


        while(!giveUp[0] && isEngaged.get()) {
            camera.openCameraDevice();

            if(insideDetectPipeline.pieceInside()) {
                elementsCount++;
                giveUpTimer.cancel();
                break;
            }
            Thread.yield();
        }

        intakeMotor.setPower(0);
        intakeServo.setPosition(INTAKE_SERVO_IDLE_POS);

        return !giveUp[0] && isEngaged.get();
    }

    public void actionateAsync(ActionateCallback actionateCallback)
    {
        if(isEngaged()) return;
        setIsEngaged(true);
        Thread t = new Thread() {
            @Override
            public void run() {
                super.run();
                if(actionateCallback != null) {
                    boolean result = actionate();
                    actionateCallback.onActionateFinished(result);
                } else {
                    actionate();
                }
                setIsEngaged(false);
            }

        };
        t.start();
    }

    public interface ActionateCallback {
        void onActionateFinished(boolean caughtPiece);
    }
}
