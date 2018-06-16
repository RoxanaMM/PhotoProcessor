package com.photos.processing;

import com.photos.draw.histogram.DrawGrayScaleHistogram;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrayScale extends DrawGrayScaleHistogram {


    public static float[] convertToGreyHelper(Mat A) {
        float[] floatArrayA = new float[256];
        Mat destinImg = new Mat(A.rows(), A.cols(), A.type());
        Imgproc.cvtColor(A, destinImg, Imgproc.COLOR_RGB2GRAY);
        int histSize = 256;
        MatOfFloat ranges = new MatOfFloat(0, 256);

        List<Mat> bgrPlanes = new ArrayList<>();
        Core.split(destinImg, bgrPlanes);

        Mat bHist = new Mat();

        Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(), bHist, new MatOfInt(histSize), ranges, false);
        for (int i = 0; i < bHist.rows(); i++) {
            for (int j = 0; j < bHist.cols(); j++) {
                for (int k = 0; k < bHist.get(i, j).length; k++) {
                    floatArrayA[i] = (float) Math.round((bHist.get(i, j)[k] * 100.0) / 100.0);
                }
            }
        }
        return floatArrayA;
    }

    public static float[] convertImageToGrey(String sourcePic1) throws IOException {
        float[] picsPdf = new float[256];
        int histSize = 256;
        Mat histImage;
        if (sourcePic1.contains("jpg")) {
            Mat A = Imgcodecs.imread(sourcePic1);
            Mat destinImg = new Mat(A.rows(), A.cols(), A.type());
            Imgproc.cvtColor(A, destinImg, Imgproc.COLOR_RGB2GRAY);

         //   Mat hist = new Mat(256, 1, CvType.CV_8UC1);
            MatOfFloat ranges = new MatOfFloat(0, 256);
         //   MatOfInt channels = new MatOfInt(0);

            List<Mat> bgrPlanes = new ArrayList<>();
            Core.split(destinImg, bgrPlanes);

            Mat bHist = new Mat();

            Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(), bHist, new MatOfInt(histSize), ranges, false);

            histImage = drawColouredHistogram(bHist);

            //String pathResult = "C:\\forMaster\\temaDisertatie\\histogramePozeSimilare\\";
            String pathResult = "C:\\forMaster\\temaDisertatie\\test\\histograme\\histograme-faces-similare\\";
            pathResult = pathResult.concat(sourcePic1);
            Imgcodecs.imwrite(pathResult, histImage);

            picsPdf = convertToGreyHelper(A);
            }
            return picsPdf;

    }
}
