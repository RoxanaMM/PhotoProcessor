package com.photos.loadImages;

import com.photos.algorithms.Algorithms;
import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferByte;
import java.io.*;
import java.util.*;

import static org.opencv.core.CvType.CV_8UC3;
import static org.opencv.core.CvType.CV_8UC1;
import static org.opencv.highgui.Highgui.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgproc.Imgproc.calcHist;

/**
 * Created by Roxana on 1/3/2018.
 */
public class LoadImage extends Algorithms {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    static float[] R = new float[256];
    static  int i =0;
    // hue varies from 0 to 179, see cvtColor
    static float[] hranges = { 0, 180 };
    // saturation varies from 0 (black-gray-white) to
    // 255 (pure spectrum color)
    static float[] sranges = { 0, 256 };
    static float [][]ranges = { hranges, sranges };
    public static final int MAX_PIXELS = 256;
    private float[][] firstPicPdf;

    public static float[] readPixels(float[] frequencyOfFirstPic){//File folder, float[] frequencyOfFirstPic){//,float[] frequencyOfSecondPic) {
//        File[] listOfFiles = folder.listFiles();
//        for (File firstFileEntry : listOfFiles) {
//            for (File secondFileEntry : listOfFiles) {
                    String filePathA =  "C:\\forMaster\\temaDisertatie\\Original Brodatz\\D7_COLORED.jpg";//String.valueOf(firstFileEntry.getAbsoluteFile());
                    Mat A = Imgcodecs.imread(filePathA, 1);
                    A = Imgcodecs.imread(filePathA, CV_LOAD_IMAGE_COLOR);
                    frequencyOfFirstPic = calculateFrequency(A, frequencyOfFirstPic);
//frequencyOfFirstPic= Imgproc.calcHist(A,new MatOfInt(0),new Mat(),histogram, new MatOfInt(25), ranges);
                String filePathB = "C:\\forMaster\\temaDisertatie\\Original Brodatz\\D9_COLORED.jpg"; //String.valueOf(firstFileEntry.getAbsoluteFile());
                Mat B = Imgcodecs.imread(filePathB, 1);
                A = Imgcodecs.imread(filePathA, CV_LOAD_IMAGE_COLOR);
               // frequencyOfSecondPic = calculateFrequency(B, frequencyOfSecondPic);
//            }
//        }
      //  calcHistogram();
        return frequencyOfFirstPic;
    }
    public static void extractLBP(Mat A) {
        Mat srcMat = A.clone();
        A.convertTo(A, CvType.CV_64FC3);

        CascadeClassifier carClassifier = new CascadeClassifier("car_side_roty270_demo.xml");

        MatOfRect cars = new MatOfRect();
        carClassifier.detectMultiScale(srcMat, cars);


        for (Rect rect : cars.toArray()) {
         //   System.out.println(String.format("Detected rectangle at %d,%d  %dx%d", rect.x, rect.y, rect.width, rect.height));
        }


    }


    public static float[] convertToGreyHelper(Mat A){
        float[] floatArrayA = new float[256];
        Mat destinImg = new Mat(A.rows(), A.cols(), A.type());
        Imgproc.cvtColor(A,destinImg,Imgproc.COLOR_RGB2GRAY);

        String pathResult = "C:\\forMaster\\savedHisto\\caca.jpg";
        Imgcodecs.imwrite(pathResult, destinImg);

        int histSize = 256;
        int hist_w = 512;
        int hist_h = 400;
        int bin_w = (int) ((double) hist_w / histSize);

        Mat hist = new Mat(256, 1, CvType.CV_8UC1);
        MatOfFloat ranges = new MatOfFloat(0, 256);
        MatOfInt channels = new MatOfInt(0);

        List<Mat> bgrPlanes = new ArrayList<>();
        Core.split(destinImg, bgrPlanes);

        Mat bHist = new Mat();

        Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(), bHist, new MatOfInt(histSize), ranges, false);
        System.out.println("The dimesions are; "+ bHist.dims());
        System.out.println(bHist.size());
        System.out.println(bHist.rows());
        System.out.println(bHist.cols());
        for(int i = 0 ;i<bHist.rows();i++) {
            for (int j = 0; j < bHist.cols(); j++) {
                for (int k = 0; k < bHist.get(i, j).length; k++) {
                    System.out.print(i + " - " + Math.round((bHist.get(i, j)[k]* 100.0) / 100.0));
                    floatArrayA[i] = (float)Math.round((bHist.get(i, j)[k]* 100.0) / 100.0); // Or whatever default you want.
                }
            }
            System.out.println("\n");
        }
        return floatArrayA;
    }

    public static void convertImageToGrey(File folder) throws IOException {
        float[][] firstPicPdf = new float[6][256];
        int i=0;
        File[] listOfFiles = folder.listFiles();
        for (File firstFileEntry : listOfFiles) {
            for (File fileEntry : listOfFiles) {
                if (fileEntry.getAbsolutePath().contains("jpg")) {
                    Mat A = Imgcodecs.imread(fileEntry.getAbsolutePath());

//
//                    Mat destinImg = new Mat(A.rows(), A.cols(), A.type());
//                    Imgproc.cvtColor(A,destinImg,Imgproc.COLOR_RGB2GRAY);
//                    int histSize = 256;
//                    int hist_w = 512;
//                    int hist_h = 400;
//                    int bin_w = (int) ((double) hist_w / histSize);
//
//
//                    Mat hist = new Mat(256, 1, CvType.CV_8UC1);
//                    MatOfFloat ranges = new MatOfFloat(0, 256);
//                    MatOfInt channels = new MatOfInt(0);
//
//                    List<Mat> bgrPlanes = new ArrayList<>();
//                    Core.split(destinImg, bgrPlanes);
//
//                    Mat bHist = new Mat();
//
//                    Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(), bHist, new MatOfInt(histSize), ranges, false);
//                    System.out.println("The dimesions are; "+ bHist.dims());
//                    System.out.println(bHist.size());
//                    System.out.println(bHist.rows());
//                    System.out.println(bHist.cols());
//                    for(int i = 0 ;i<bHist.rows();i++) {
//                        for (int j = 0; j < bHist.cols(); j++) {
//                            for (int k = 0; k < bHist.get(i, j).length; k++) {
//                                System.out.print(i + " - " + Math.round((bHist.get(i, j)[k]* 100.0) / 100.0));
//                                firstPicPdf = (float)Math.round(bHist.get(i, j)[k]);
//                                calculateDistance(firstPicPdf, secondPicPdf);
//                            }
//                            System.out.println("\n");
//                        }
//                    }
//
//                    Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC1, new Scalar(0));
//                    Core.normalize(bHist, bHist, 0, histImage.rows(), Core.NORM_MINMAX);
//
//                    float[] bHistData = new float[(int) (bHist.total() * bHist.channels())];
//                    bHist.get(0, 0, bHistData);
//
//                    for (int i = 1; i < histSize; i++) {
//                        Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(bHistData[i - 1])),
//                                new Point(bin_w * (i), hist_h - Math.round(bHistData[i])), new Scalar(255, 0, 0), 2);
//
//                    }
//
//                    String pathResult = "C:\\forMaster\\savedHisto\\";
//                    pathResult = pathResult.concat(fileEntry.getName());
//                    Imgcodecs.imwrite(pathResult, histImage);
//                }
                    if(i<=5)
                        firstPicPdf[i++] = convertToGreyHelper(A);
                }
            }
        }
                calculateDistance(firstPicPdf[0],firstPicPdf[1]);
    }
    
    public static void calculateAndDrawHistogram(File folder) throws IOException {
        File[] listOfFiles = folder.listFiles();
        for (File firstFileEntry : listOfFiles) {
            for (File fileEntry : listOfFiles) {
                if (fileEntry.getAbsolutePath().contains("jpg")) {
                    Mat A = Imgcodecs.imread(fileEntry.getAbsolutePath());

                    int histSize = 256;
                    int hist_w = 512;
                    int hist_h = 400;
                    int bin_w = (int) ((double) hist_w / histSize);

                    Mat hist = new Mat(256, 1, CvType.CV_8UC1);
                    MatOfFloat ranges = new MatOfFloat(0, 256);
                    MatOfInt channels = new MatOfInt(3);

                    List<Mat> bgrPlanes = new ArrayList<>();
                    Core.split(A, bgrPlanes);

                    Mat bHist = new Mat(), gHist = new Mat(), rHist = new Mat();
                    Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(), bHist, new MatOfInt(histSize), ranges, false);
                    Imgproc.calcHist(bgrPlanes, new MatOfInt(1), new Mat(), gHist, new MatOfInt(histSize), ranges, false);
                    Imgproc.calcHist(bgrPlanes, new MatOfInt(2), new Mat(), rHist, new MatOfInt(histSize), ranges, false);

                    System.out.println(bHist.size());
                    System.out.println(gHist.size());
                    System.out.println(rHist.size());

                    Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC3, new Scalar(0, 0, 0));
                    Core.normalize(bHist, bHist, 0, histImage.rows(), Core.NORM_MINMAX);
                    Core.normalize(gHist, gHist, 0, histImage.rows(), Core.NORM_MINMAX);
                    Core.normalize(rHist, rHist, 0, histImage.rows(), Core.NORM_MINMAX);

                    float[] bHistData = new float[(int) (bHist.total() * bHist.channels())];
                    bHist.get(0, 0, bHistData);
                    float[] gHistData = new float[(int) (gHist.total() * gHist.channels())];
                    gHist.get(0, 0, gHistData);
                    float[] rHistData = new float[(int) (rHist.total() * rHist.channels())];
                    rHist.get(0, 0, rHistData);
                    for (int i = 1; i < histSize; i++) {
                        Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(bHistData[i - 1])),
                                new Point(bin_w * (i), hist_h - Math.round(bHistData[i])), new Scalar(255, 0, 0), 2);
                        Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(gHistData[i - 1])),
                                new Point(bin_w * (i), hist_h - Math.round(gHistData[i])), new Scalar(0, 255, 0), 2);
                        Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(rHistData[i - 1])),
                                new Point(bin_w * (i), hist_h - Math.round(rHistData[i])), new Scalar(0, 0, 255), 2);
                    }

                    String pathResult = "C:\\forMaster\\savedHisto\\";
                    pathResult = pathResult.concat(fileEntry.getName());
                    Imgcodecs.imwrite(pathResult, histImage);
                }
            }
        }

    }
    public static float[] calculateFrequency(Mat A, float frequency[]) {
        Mat C = A.clone();
        A.convertTo(A, CvType.CV_64FC3);
      //  A.rows()
        int size = (int) (A.total() * A.channels());
        double[] temp = new double[size];
        A.get(0, 0, temp);
        for (int i = 0; i < size; i++) {
            double pixel = temp[i];
            frequency[(int) pixel]++;
        }
        C.put(0, 0, temp);
        return frequency;
    }

    static float[] calculatepdf(float[] frequency) {
        float[] pdf = new float[256];
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] != 0) {
                pdf[i]=frequency[i]/MAX_PIXELS/i;
            }
            //System.out.println(pdf[i]);
        }
        return pdf;
    }

    public static void calculateDistance(float frequencyOfFirstPic[], float frequencyOfSecondPic[]) {
        int p = 0;
        for (int i = 0; i < R.length; i++) {
            R[i] = 100;
        }
        double[]similarities = new double[51];
            similarities[1] = euclidianL2(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[2] = cityBlockL1(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[3] = minkowskiLp(frequencyOfFirstPic, frequencyOfSecondPic, p);
            similarities[4] = cebyshevLinf(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[5] = sorensen(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[6] = gower(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[7] = soergel(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[8] = kulczynskid(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[9] = canberra(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[10] = lorentzian(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[11] = intersection(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[12] = waveHedges(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[13] = similarityCzekanowski(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[14] = distanceCzekanowski(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[15] = similarityMotyka(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[16] = distanceMotyka(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[17] = similarityKulczynkyS(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[18] = distanceKulczynkyS(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[19] = ruzicka(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[20] = tanimoto(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[21] = innerProduct(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[22] = harmonicMean(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[23] = cosine(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[24] = kumarHassebrook(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[25] = similarityJaccard(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[26] = distanceJaccard(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[27] = similarityDice(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[28] = distanceDice(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[29] = similarityFidelity(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[30] = distanceBhattacharyya(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[31] = distanceHellinger(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[32] = distanceMatusita(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[33] = distanceSquaredChord(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[34] = similaritySquaredChord(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[35] = distanceSquaredEuclidian(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[36] = distancePearson(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[37] = distanceNeyman(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[38] = distanceSquared(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[39] = distanceProbabilisticSymmetric(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[40] = distanceDivergence(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[41] = distanceClark(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[42] = distanceAdditiceSymmetric(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[43] = distanceKullbackLeibler(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[44] = distanceJeffreys(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[45] = distanceKDivergence(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[46] = distanceTopsoe(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[47] = distanceJensenShannon(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[48] = distanceJensenDifference(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[49] = distanceKumarJohnson(frequencyOfFirstPic, frequencyOfSecondPic);
            similarities[50] = distanceAvg(frequencyOfFirstPic, frequencyOfSecondPic);
    }
//    public void compareSimilarityVector(double[]similarities){
//        for( int i= 0;i<51;i++){
//            if(similarities[i] >)
//        }
//    }
    public static void main(String[] args) throws Exception {

        String filePath = "C:\\forMaster\\temaDisertatie\\Original Brodatz";
        File folder = new File(filePath);
        //readPixels(folder);
        //showPixelFrequencies(frequencyOfFirstPic,frequencyOfSecondPic);
        //  int [] frequency = calculateFrequency(matrix);

     //   Mat A, B,C,D = null;
//        A = Imgcodecs.imread("C:\\forMaster\\temaDisertatie\\Original Brodatz\\D1.gif");
//        D = Imgcodecs.imread("C:\\Users\\Roxana\\Downloads\\est.jpg");
//
//CV_COMP_CORREL: [-1;1] where 1 is perfect match and -1 is the worst.
//CV_COMP_CHISQR: [0;+infinty] where 0 is perfect match and mismatch is unbounded
//CV_COMP_INTERSECT: [0;1] (if histograms are normalized) 1 is perfect match and 0 mismatch.
//CV_COMP_BHATTACHARYYA and CV_COMP_HELLINGER: [0;1] where 0 is perfect match and 1 mismatch.

//        List<Mat> matList = new ArrayList<Mat>();
//        matList.add(A);
//        matList.add(D);
//
//        Mat hist_1 = new Mat();
//        Mat hist_2 = new Mat();
//
//        System.out.println(hist_1);
//        System.out.println(hist_2);
//
//        Mat mask = new Mat();
//        Mat hist = new Mat(256, 1, CvType.CV_8UC1);
//        MatOfInt histSize = new MatOfInt(256);
//        MatOfFloat ranges = new MatOfFloat(0, 256);
//        MatOfInt channels = new MatOfInt(0);

//        Imgproc.calcHist(Arrays.asList(A), new MatOfInt(0),
//                new Mat(), hist_1, histSize, ranges);
//        Imgproc.calcHist(Arrays.asList(D), new MatOfInt(0),
//                new Mat(), hist_2, histSize, ranges);

//        System.out.println(hist_1);
//        System.out.println(hist_2);

//        double res = Imgproc.compareHist(hist_1, hist_2, Imgproc.CV_COMP_CORREL);
//        Imgproc.calcHist(matList, channels, mask, hist, histSize, ranges);
//        Core.randu(hist_1, 1, 10);


//        Plot2d plot = Plot2d.create(hist_1);
//        Mat mplot = new Mat();
//        plot.setPlotLineColor(new Scalar(0,0,255));
//        plot.render(mplot);
//        Imgcodecs.imwrite("e:/test.png", jg.plot2d);
        convertImageToGrey(folder);
        float[] frequencyOfFirstPic = new float[256];
        float[] frequencyOfSecondPic = new float[256];

        float[] pdfOfFirstPic = new float[256];
        float[] pdfOfSecondPic = new float[256];

        //readPixels(folder, frequencyOfFirstPic, frequencyOfSecondPic);
        pdfOfFirstPic = calculatepdf(frequencyOfFirstPic);
        pdfOfSecondPic = calculatepdf(frequencyOfSecondPic);
        //calculateAndDrawHistogram(folder);
        calculateDistance(pdfOfFirstPic,pdfOfSecondPic);
    }
}
