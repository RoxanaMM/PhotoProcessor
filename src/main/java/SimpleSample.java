import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * Created by Roxana on 1/3/2018.
 */
public class SimpleSample
{
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static Mat openFile(String fileName) throws Exception{
        Mat newImage = Imgcodecs.imread(fileName);
        if(newImage.dataAddr()==0){
            throw new Exception ("Couldn't open file "+fileName);
        }
        return newImage;
    }

    public static void filter(Mat image){
        int totalBytes = (int)(image.total() * image.elemSize());
        byte buffer[] = new byte[totalBytes];
        image.get(0, 0,buffer);
        for(int i=0;i<totalBytes;i++){
            if(i%3==0) buffer[i]=0;
        }
        image.put(0, 0, buffer);
    }

    public static void main(String[] args) {


        Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
        Mat image2 = new Mat(640,640,CvType.CV_8UC3);
        Mat image3 = new Mat(new Size(640,640), CvType.CV_8UC3);
        Mat image = new Mat(new Size(3,3), CvType.CV_8UC3, new Scalar(new
                double[]{128,3,4}));
        //the array of bytes {1, 2, 3}, for our matrix, 1 stands
       // for the blue channel, 2 for the green, and 3 for the red channel
        for(int i=0;i<image.rows();i++){
            for(int j=0;j<image.cols();j++){
                image.put(i, j, new byte[]{1,2,3});
            }
        }
//
        Mat mr1 = m.row(0);
        mr1.setTo(new Scalar(1));
        Mat mc5 = m.col(4);
        mc5.setTo(new Scalar(5));
        System.out.println("OpenCV Mat data:\n" + m.dump());
        System.out.println("OpenCV Mat data:\n" + image.dump());
        filter(image);
        System.out.println("OpenCV Mat data:\n" + image.dump());
        try {
            openFile("E:\\MASTER\\an2\\disertatie\\temaDisertatie\\Set.jpg");
            System.out.println("file read");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
