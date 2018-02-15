package com.photos.filters;

import org.opencv.core.Mat;

/**
 * Created by Roxana on 1/7/2018.
 */
public class Filters {

    public static void filter(Mat image) {
        int totalBytes = (int) (image.total() * image.elemSize());
        byte buffer[] = new byte[totalBytes];
        image.get(0, 0, buffer);
        for (int i = 0; i < totalBytes; i++) {
            if (i % 3 == 0) buffer[i] = 9;
        }
        image.put(0, 0, buffer);
    }

}
