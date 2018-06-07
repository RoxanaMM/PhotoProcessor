package com.photos.filters;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class FilteredSaturation {
    public static void filter() {
        Mat image = Imgcodecs.imread("C:\\forMaster\\temaDisertatie\\test\\filters\\saturationFilter\\");
        int totalBytes = (int) (image.total() * image.elemSize());

        byte buffer[] = new byte[totalBytes];
        image.get(0, 0, buffer);
        for (int i = 0; i < totalBytes; i++) {
            if (i % 2 == 0) buffer[i] = 4;
        }
        image.put(0, 0, buffer);
        String pathResult = "C:\\forMaster\\temaDisertatie\\BlueFilter\\filetered.jpg";
        // pathResult = pathResult.concat(fileEntry.getName());
        //C:\forMaster\temaDisertatie\test\histograme\histograme-blueFilter
        Imgcodecs.imwrite(pathResult, image);
    }
}
