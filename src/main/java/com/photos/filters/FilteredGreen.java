package com.photos.filters;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class FilteredGreen {
    public static void filter() {
        Mat image = Imgcodecs.imread("C:\\forMaster\\temaDisertatie\\c.jpg");
        int totalBytes = (int) (image.total() * image.elemSize());

        byte buffer[] = new byte[totalBytes];
        image.get(0, 0, buffer);
        for (int i = 0; i < totalBytes; i++) {
            if (i % 3 == 0) buffer[i] = 9;
        }
        image.put(0, 0, buffer);
        String pathResult = "C:\\forMaster\\temaDisertatie\\YellowFilter\\filetered.jpg";
        // pathResult = pathResult.concat(fileEntry.getName());
        Imgcodecs.imwrite(pathResult, image);
    }
}
