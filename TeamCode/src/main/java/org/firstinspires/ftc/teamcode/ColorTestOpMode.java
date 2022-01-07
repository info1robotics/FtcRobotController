package org.firstinspires.ftc.teamcode;

import android.hardware.camera2.CameraCaptureSession;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
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

@TeleOp()
public class ColorTestOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {


//        ColorSensor colorSensor = (ColorSensor) hardwareMap.get("colorSensor");
//        DistanceSensor distanceSensor = (DistanceSensor) hardwareMap.get("distanceSensor");

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        InsideDetectPipeline insideDetectPipeline = new InsideDetectPipeline();


        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
        camera.openCameraDevice();
        camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
        camera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
        camera.setPipeline(insideDetectPipeline);

        while(!isStarted()) {
            telemetry.clear();
//            int alpha = colorSensor.alpha();
//            int red = colorSensor.red();
//            int green = colorSensor.green();
//            int blue = colorSensor.blue();
//
//            String sb = "ALPHA: " +
//                    alpha +
//                    "  RED: " +
//                    red +
//                    "  GREEN: " +
//                    green +
//                    "  BLUE: " +
//                    blue +
//                    "  MM: " +
//                    distanceSensor.getDistance(DistanceUnit.MM);
            //telemetry.log().add(sb);

            telemetry.addLine(Boolean.toString(insideDetectPipeline.pieceInside()));
            telemetry.update();
            Thread.yield();
        }
    }
}
