package com.photos.loadImages;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;

public class OneImage {
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\forMaster\\temaDisertatie\\Original Brodatz";
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        Mat A, B = null;
        A = Imgcodecs.imread("C:\\forMaster\\temaDisertatie\\Original Brodatz\\D1.gif");
        B = Imgcodecs.imread("C:\\forMaster\\temaDisertatie\\Original Brodatz\\D2.gif");
    }
}
