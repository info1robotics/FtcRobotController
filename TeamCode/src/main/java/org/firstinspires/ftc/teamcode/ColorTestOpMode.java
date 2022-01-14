package org.firstinspires.ftc.teamcode;

import android.hardware.camera2.CameraCaptureSession;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.internal.camera.WebcamExample;
import org.firstinspires.ftc.robotcore.internal.camera.delegating.UsbResiliantWebcam;
import org.firstinspires.ftc.robotcore.internal.vuforia.externalprovider.VuforiaWebcam;
import org.firstinspires.ftc.teamcode.OpenCV.InsideDetectPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.tensorflow.lite.TensorFlowLite;

import java.util.Date;

@TeleOp()
@Config
public class ColorTestOpMode extends LinearOpMode {


    public static double PIXELS_RATIO = 1.f/3;
    public static double DETECTION_COOLDOWN_SEC = 5;
    public static double MOTOR_POWER_TEMP = 0.2;

    @Override
    public void runOpMode() throws InterruptedException {


//        ColorSensor colorSensor = (ColorSensor) hardwareMap.get("colorSensor");
//        DistanceSensor distanceSensor = (DistanceSensor) hardwareMap.get("distanceSensor");

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        InsideDetectPipeline insideDetectPipeline = new InsideDetectPipeline(telemetry);


        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
        camera.openCameraDevice();
        camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
        camera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
        camera.setPipeline(insideDetectPipeline);

        int elementsCount = 0;
        boolean gate = true;
        long lastDetectionTime = 0;

        // "motorFL" -- temporary hack
        DcMotor intakeMotor = hardwareMap.get(DcMotor.class, "motorFL");
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        while(!isStarted()) {
            intakeMotor.setPower(MOTOR_POWER_TEMP);

            Date date = new Date();

            if(insideDetectPipeline.pieceInside() && gate) {
                gate = false;
                lastDetectionTime = date.getTime();
                elementsCount++;
            } else if(date.getTime() - lastDetectionTime > DETECTION_COOLDOWN_SEC * 1000) gate = true;


            telemetry.addData("Elements Count", elementsCount);
            telemetry.update();
            Thread.yield();
        }
    }
}
