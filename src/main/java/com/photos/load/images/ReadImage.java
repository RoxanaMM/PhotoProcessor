package com.photos.load.images;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

/**
 * Created by Roxana on 1/6/2018.
 */
public class ReadImage {


    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            Mat A = Highgui.imread("E:\\MASTER\\an2\\disertatie\\temaDisertatie\\Set.jpg"); //"image_addr" is the address of the image
            Mat C = A.clone();
            A.convertTo(A, CvType.CV_64FC3); //New line added.
            int size = (int) (A.total() * A.channels());
            double[] temp = new double[size]; //use double[] instead of byte[]
            A.get(0, 0, temp);
            for (int i = 0; i < size ;i++)
                temp[i] = (temp[i] / 2);  //no more casting required.
            C.put(0, 0, temp);

            System.out.println(C.dump());
    }
}
