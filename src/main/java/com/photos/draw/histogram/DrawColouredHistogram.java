package com.photos.draw.histogram;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

public class DrawColouredHistogram {

   public static Mat drawColouredHistogram(Mat bHist, Mat gHist, Mat rHist){

       int hist_w = 512;
       int hist_h = 400;
       int histSize = 256;
       int bin_w = (int) ((double) hist_w / histSize);
        Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC3, new Scalar(0, 0, 0));
        Core.normalize(bHist, bHist, 0, histImage.rows(), Core.NORM_MINMAX);
        Core.normalize(gHist, gHist, 0, histImage.rows(), Core.NORM_MINMAX);
        Core.normalize(rHist, rHist, 0, histImage.rows(), Core.NORM_MINMAX);

        float[] bHistData = new float[(int) (bHist.total() * bHist.channels())];
        bHist.get(0, 0, bHistData);
        float[] gHistData = new float[(int) (gHist.total() * gHist.channels())];
        gHist.get(0, 0, gHistData);
        float[] rHistData = new float[(int) (rHist.total() * rHist.channels())];
        rHist.get(0, 0, rHistData);
        for (int i = 1; i < histSize; i++) {
            Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(bHistData[i - 1])),
                    new Point(bin_w * (i), hist_h - Math.round(bHistData[i])), new Scalar(255, 0, 0), 1);
            Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(gHistData[i - 1])),
                    new Point(bin_w * (i), hist_h - Math.round(gHistData[i])), new Scalar(0, 255, 0), 1);
            Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(rHistData[i - 1])),
                    new Point(bin_w * (i), hist_h - Math.round(rHistData[i])), new Scalar(0, 0, 255), 1);
        }
    return histImage;
    }
}
