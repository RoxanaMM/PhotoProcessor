package com.photos.filters;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class FilteredRed {
    public static void filter() {
        Mat image = Imgcodecs.imread("C:\\forMaster\\temaDisertatie\\test\\filters\\redFilter");
        int totalBytes = (int) (image.total() * image.elemSize());

        byte buffer[] = new byte[totalBytes];
        image.get(0, 0, buffer);
        for (int i = 0; i < totalBytes; i++) {
            if (i % 5 == 0) buffer[i] = 12;
        }
        image.put(0, 0, buffer);
        String pathResult = "C:\\forMaster\\temaDisertatie\\RedFilter\\filetered.jpg";
        // pathResult = pathResult.concat(fileEntry.getName());
        //C:\forMaster\temaDisertatie\test\histograme\histograme-blueFilter
        Imgcodecs.imwrite(pathResult, image);
    }
}
