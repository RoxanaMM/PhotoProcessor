package com.photos.draw.histogram;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

public class DrawGrayScaleHistogram {

    public static Mat drawColouredHistogram(Mat bHist){
        int histSize = 256;
        int hist_w = 512;
        int hist_h = 400;
        int bin_w = (int) ((double) hist_w / histSize);
        Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC1, new Scalar(0));
        Core.normalize(bHist, bHist, 0, histImage.rows(), Core.NORM_MINMAX);

        float[] bHistData = new float[(int) (bHist.total() * bHist.channels())];
        bHist.get(0, 0, bHistData);

        for (int k = 1; k < histSize; k++) {
            Imgproc.line(histImage, new Point(bin_w * (k - 1), hist_h - Math.round(bHistData[k - 1])),
                    new Point(bin_w * (k), hist_h - Math.round(bHistData[k])), new Scalar(255, 0, 0), 1);

        }
        return histImage;
    }
}
