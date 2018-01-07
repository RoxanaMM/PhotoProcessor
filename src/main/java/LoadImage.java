import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

/**
 * Created by Roxana on 1/3/2018.
 */
public class LoadImage {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public static void readPixels() {
        ImageViewer imageViewer = new ImageViewer();
        String filePath = "E:\\MASTER\\an2\\disertatie\\temaDisertatie\\Set.jpg";
        Mat A = Imgcodecs.imread(filePath);
        Mat pixelCheck = Imgcodecs.imread(filePath);

        Mat C = A.clone();
        Mat pixelCheckClone = pixelCheck.clone();

        A.convertTo(A, CvType.CV_64FC3); //New line added.
        int size = (int) (A.total() * A.channels());
        double[] temp = new double[size]; //use double[] instead of byte[]
        //double[] pixelMatrix = new double[size];
        int[] frequency = new int[size];
        A.get(0, 0, temp);
        System.out.println(size);
        for (int i = 0; i < size; i++) {
            double pixel = temp[i];
            frequency[(int) pixel]++;
            System.out.println("the pixel is: " + (int)temp[i]);
            System.out.println("the frequency is: " + frequency[(int) temp[i]]);

        }
        for (int i = 0; i < size; i++) {

        }
        C.put(0, 0, temp);
        Imgcodecs.imwrite("E:\\MASTER\\an2\\disertatie\\temaDisertatie\\Clona.jpg", C);
    }


    public static void main(String[] args) throws Exception {
        readPixels();
    }
}
