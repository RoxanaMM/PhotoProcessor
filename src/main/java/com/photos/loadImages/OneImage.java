package com.photos.loadImages;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;

public class OneImage {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\forMaster\\temaDisertatie\\Original Brodatz";
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        Mat A, B = new Mat(480,640,CvType.CV_8UC3);
        A = Imgcodecs.imread("C:\\forMaster\\temaDisertatie\\Original Brodatz\\a.jpg");
       A.total();// B = Imgcodecs.imread("C:\\forMaster\\temaDisertatie\\Original Brodatz\\D2.gif");

        Mat image2 = new Mat(480,640,CvType.CV_8UC3);
        Mat image3 = new Mat(new Size(640,480), CvType.CV_8UC3);
System.out.println( "   "+A.rows() +""
+A.cols() + " ");
    }

}