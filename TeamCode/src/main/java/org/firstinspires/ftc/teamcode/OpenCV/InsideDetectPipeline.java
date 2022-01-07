package org.firstinspires.ftc.teamcode.OpenCV;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.List;

public class InsideDetectPipeline extends OpenCvPipeline {

    Mat mat = new Mat();
    Mat gateMat = new Mat();


    final int MIN_PIXELS = 61440;

    boolean pieceDetected = false;

    @Override
    public Mat processFrame(Mat input) {
        Core.normalize(input, mat, 0, 255, Core.NORM_MINMAX);


        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        Imgproc.blur(mat, mat, new Size(5, 5));

        Scalar lowHSV = new Scalar(0, 0, 80);
        Scalar highHSV = new Scalar(70, 255, 255);

        Core.inRange(mat, lowHSV, highHSV, gateMat);

        Mat edges = new Mat();
//        Imgproc.Canny(gateMat, edges, 150, 255);
//
//        gateMat = gateMat
        double correspondingPixels = Core.sumElems(gateMat).val[0] / gateMat.size().area() / 255;

//        List<MatOfPoint> Points;
//
//        Imgproc.findContours(gateMat, Points, );

        Imgproc.cvtColor(gateMat, gateMat, Imgproc.COLOR_GRAY2RGB);

        edges.release();

        return gateMat;
    }

    public boolean pieceInside() { return pieceDetected; }

}
