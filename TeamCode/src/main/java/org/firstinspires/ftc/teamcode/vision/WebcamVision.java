package org.firstinspires.ftc.teamcode.vision;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WebcamVision {

    private static final String VUFORIA_LICENSE_KEY = "AcTB3h7/////AAABma7je7SvYkkqrJT5rzDrtvh/dZ4kzPKDHWZs" +
            "kG12sOplNFyVylw2VzUahIt1kP22rq+oYVqkn+++JewM0W0NXk7KDbcMo0cQAtI8WcgJjYh+jTmoNuokUg2A" +
            "NIpNyrqpKBR9VU5tjQEb5akUNBkyfJiKLXWfxv79vaTGptYiGoK4pn9THnHo2PTWtlE5mpts4NjjdUJJe5u8D" +
            "9g8g0GIaLYDr6qmVuGaZ/ZeM8ZVwIo390U6uc5xJ37SmvZH4DNCALdd+isOsOJ9LYJV5Qvn0kZhO3IAoN0mLk" +
            "fJ2lhqTjHnzba9K8JSTM2LE+fbmdxSsoUj3uEhCWENSqyjZCbLouLfBpRjkVEVor3mhYI+emGF\n";



    private static final String TFOD_MODEL_ASSET = "UltimateGoal.tflite";
    private final LinearOpMode opMode;

    private VuforiaLocalizer vuforia;
    public TFObjectDetector tfod;
    public FtcDashboard dashboard;
    private Telemetry dashboardTelemetry;

    public WebcamVision(LinearOpMode opMode) {
        this.opMode = opMode;
        VuforiaLocalizer.Parameters vuforiaParams = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        vuforiaParams.cameraName = this.opMode.hardwareMap.get(WebcamName.class, "Webcam 1");
        vuforiaParams.vuforiaLicenseKey = VUFORIA_LICENSE_KEY;
        vuforiaParams.cameraDirection = VuforiaLocalizer.CameraDirection.DEFAULT;
        vuforia = ClassFactory.getInstance().createVuforia(vuforiaParams);

        int tfodMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, ObjectCodes.QUAD.toString(), ObjectCodes.SINGLE.toString());
        tfod.setZoom(1.25, 1920.f/1080);

        if (tfod != null) {
            tfod.activate();
        }

        dashboard = FtcDashboard.getInstance();
        dashboard.startCameraStream(tfod, 0);

        dashboardTelemetry = dashboard.getTelemetry();


    }



    public DetectedObject getFrontDetection() {
        if (tfod != null) {
            List<Recognition> recognitions = tfod.getRecognitions();
            if(recognitions.size() > 0)
            {
                if (recognitions.get(0).getLabel().equals(ObjectCodes.QUAD.toString()))
                    return new DetectedObject(ObjectCodes.QUAD);
                if (recognitions.get(0).getLabel().equals(ObjectCodes.SINGLE.toString()))
                    return new DetectedObject(ObjectCodes.SINGLE);
            }


        }
        return new DetectedObject(ObjectCodes.NO_OBJECT);
    }


    public void getAllDetections() {
        if (tfod != null) {
            List<Recognition> recognitions = tfod.getRecognitions();

            for (Recognition r: recognitions) {
                dashboardTelemetry.addLine(r.getLabel() + " " + r.getConfidence());
            }
            if(recognitions.size() == 0) dashboardTelemetry.addLine("NO_OBJECT");
            dashboardTelemetry.update();
        }
    }

}