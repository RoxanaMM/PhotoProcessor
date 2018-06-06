package com.photos.filters;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class FilteredSepia {

    public static Mat sepia(Mat bgr, int depth) {
        // Sepia values
        double red = 60;
        double green = 35;
        double blue = 0;

        int rows = bgr.rows();
        int cols = bgr.cols();

        // constant grayscale
        final double GS_RED = 0.3;
        final double GS_GREEN = 0.59;
        final double GS_BLUE = 0.11;

        double[] pixel = new double[3];

        // scan through all pixels
        for (int x = 0; x < rows; ++x) {
            for (int y = 0; y < cols; ++y) {

                // get pixel color
                pixel = bgr.get(x, y);
                // apply grayscale sample
                pixel[0] = pixel[1] = pixel[2] = (int) (GS_RED * pixel[2] + GS_GREEN * pixel[1] + GS_BLUE * pixel[0]);
                // apply intensity level for sepid-toning on each channel
                pixel[2] += (depth * red);
                if (pixel[2] > 255) {
                    pixel[2] = 255;
                }
                pixel[1] += (depth * green);
                if (pixel[1] > 255) {
                    pixel[1] = 255;
                }
                pixel[0] += (depth * blue);
                if (pixel[0] > 255) {
                    pixel[0] = 255;
                }
                // set new pixel color to output image
                bgr.put(x, y, pixel);
            }
        }
        // return final image
        return bgr;
    }



    public static void filter() throws IOException {
        Mat image = Imgcodecs.imread("C:\\forMaster\\temaDisertatie\\c.jpg");
//        int sepiaDepth = 20;
//        int sepiaIntensity =100;
//        final double GS_RED = 0.3;
//        final double GS_GREEN = 0.59;
//        final double GS_BLUE = 0.11;
//
//
//        int totalBytes = (int) (image.total() * image.elemSize());
//
//        byte buffer[] = new byte[totalBytes];
//        image.get(0, 0, buffer);
//        for (int i = 0; i < totalBytes-2; i++) {
//            int r = buffer[i];
//            int g = buffer[i + 1];
//            int b = buffer[i + 2];
//
//            int gry = (r + g + b) / 3;
//            r = g = b = gry;
//            r = r + (sepiaDepth * 2);
//            g = g + sepiaDepth;
//
//            if (r > 255) {
//                r = 255;
//            }
//            if (g > 255) {
//                g = 255;
//            }
//            if (b > 255) {
//                b = 255;
//            }
//
//            // Darken blue color to increase sepia effect
//            b -= sepiaIntensity;
//            if (b < 0) {
//                b = 0;
//            }
//            if (b > 255) {
//                b = 255;
//            }
//
//            buffer[i] = (byte) r;
//            buffer[i + 1] = (byte) g;
//            buffer[i + 2] = (byte) b;
//
//        }
//        image.put(0, 0, buffer);
        //Mat destinImg = new Mat(image.rows(), image.cols(), image.type());
       // Imgproc.cvtColor(image,destinImg,Imgproc.COLORMAP_WINTER);

        sepia(image,100);
        String pathResult = "C:\\forMaster\\temaDisertatie\\SepiaFilter\\filetered.jpg";
        // pathResult = pathResult.concat(fileEntry.getName());
        Imgcodecs.imwrite(pathResult, image);
    }
}