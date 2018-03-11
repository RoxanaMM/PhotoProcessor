package com.photos.loadImages;

import com.photos.algorithms.Algorithms;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Roxana on 1/3/2018.
 */
public class LoadImage extends Algorithms {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    static float[] frequency = new float[256];
    static float[] frequencyOfFirstPic = new float[256];
    static float[] frequencyOfSecondPic = new float[256];
    static float[] R = new float[256];
    static  int i =0;

    public static void readPixels(File folder) {
        File[] listOfFiles = folder.listFiles();
        Mat A, B = null;
        for (File firstFileEntry : listOfFiles) {
            for (File fileEntry : listOfFiles) {
                if (fileEntry.getAbsolutePath().contains("gif") && i == 0) {
                    String filePathA = String.valueOf(firstFileEntry.getAbsoluteFile());
                    A = Imgcodecs.imread(filePathA);
                    String filePathB = String.valueOf(fileEntry.getAbsoluteFile());
                    B = Imgcodecs.imread(filePathB);
                    frequencyOfFirstPic = calculateFrequency(A, frequencyOfFirstPic);
                    frequencyOfSecondPic = calculateFrequency(B, frequencyOfSecondPic);
                    calculateDistance(frequencyOfFirstPic, frequencyOfSecondPic);
                    i = 1;
                    System.out.println("another second image");
                }
            }
        }
    }
    public static void extractLBP(Mat A) {
        Mat srcMat = A.clone();
        A.convertTo(A, CvType.CV_64FC3);

        CascadeClassifier carClassifier = new CascadeClassifier("car_side_roty270_demo.xml");

        MatOfRect cars = new MatOfRect();
        carClassifier.detectMultiScale(srcMat, cars);


        for (Rect rect : cars.toArray()) {
            System.out.println(String.format("Detected rectangle at %d,%d  %dx%d", rect.x, rect.y, rect.width, rect.height));
        }


    }

    public static float[] calculateFrequency(Mat A, float frequency[]) {
        Mat C = A.clone();
        A.convertTo(A, CvType.CV_64FC3);
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

    static void showPixelFrequenciesf() {
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] != 0) {
                System.out.println("the pixel" + i);
                System.out.println(frequency[i]);
            }
        }
    }

    public static void calculateDistance(float frequencyOfFirstPic[], float frequencyOfSecondPic[]) {
        int p = 0;
        for (int i = 0; i < R.length; i++) {
            R[i] = 100;
        }
        double[][] similarities = new double[113][51];
        for (int i = 0; i < 113; i++) {
            similarities[i][1] = euclidianL2(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][2] = cityBlockL1(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][3] = minkowskiLp(frequencyOfFirstPic, frequencyOfSecondPic, p);
//            similarities[i][4] = cebyshevLinf(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][5] = sorensen(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][6] = gower(frequencyOfFirstPic, frequencyOfSecondPic, R);
//            similarities[i][7] = soergel(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][8] = kulczynskid(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][9] = canberra(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][10] = lorentzian(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][11] = intersection(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][12] = waveHedges(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][13] = similarityCzekanowski(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][14] = distanceCzekanowski(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][15] = similarityMotyka(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][16] = distanceMotyka(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][17] = similarityKulczynkyS(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][18] = distanceKulczynkyS(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][19] = ruzicka(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][20] = tanimoto(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][21] = innerProduct(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][22] = harmonicMean(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][23] = cosine(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][24] = kumarHassebrook(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][25] = similarityJaccard(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][26] = distanceJaccard(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][27] = similarityDice(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][28] = distanceDice(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][29] = similarityFidelity(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][30] = distanceBhattacharyya(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][31] = distanceHellinger(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][32] = distanceMatusita(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][33] = distanceSquaredChord(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][34] = similaritySquaredChord(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][35] = distanceSquaredEuclidian(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][36] = distancePearson(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][37] = distanceNeyman(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][38] = distanceSquared(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][39] = distanceProbabilisticSymmetric(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][40] = distanceDivergence(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][41] = distanceClark(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][42] = distanceAdditiceSymmetric(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][43] = distanceKullbackLeibler(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][44] = distanceJeffreys(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][45] = distanceKDivergence(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][46] = distanceTopsoe(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][47] = distanceJensenShannon(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][48] = distanceJensenDifference(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][49] = distanceKumarJohnson(frequencyOfFirstPic, frequencyOfSecondPic);
//            similarities[i][50] = distanceAvg(frequencyOfFirstPic, frequencyOfSecondPic);
        }
    }

    public static void main(String[] args) throws Exception {
      //  File folder = new File(filePath);
      //  File[] listOfFiles = folder.listFiles();
      //  String filePath = "C:\\forMaster\\temaDisertatie\\Original Brodatz";

        //readPixels(folder);
        //showPixelFrequencies(frequencyOfFirstPic,frequencyOfSecondPic);
        //  int [] frequency = calculateFrequency(matrix);

        Mat A, B,C,D = null;
        A = Imgcodecs.imread("C:\\forMaster\\temaDisertatie\\Original Brodatz\\D1.gif");
        D = Imgcodecs.imread("C:\\Users\\Roxana\\Downloads\\est.jpg");
/*
CV_COMP_CORREL: [-1;1] where 1 is perfect match and -1 is the worst.
CV_COMP_CHISQR: [0;+infinty] where 0 is perfect match and mismatch is unbounded
CV_COMP_INTERSECT: [0;1] (if histograms are normalized) 1 is perfect match and 0 mismatch.
CV_COMP_BHATTACHARYYA and CV_COMP_HELLINGER: [0;1] where 0 is perfect match and 1 mismatch.*/
        List<Mat> matList = new ArrayList<Mat>();
        matList.add(A);
        matList.add(D);

        Mat hist_1 = new Mat();
        Mat hist_2 = new Mat();

        System.out.println(hist_1);
        System.out.println(hist_2);

        Mat mask = new Mat();
        Mat hist = new Mat(256, 1, CvType.CV_8UC1);
        MatOfInt histSize = new MatOfInt(256);
        MatOfFloat ranges = new MatOfFloat(0, 256);
        MatOfInt channels = new MatOfInt(0);

        Imgproc.calcHist(Arrays.asList(A), new MatOfInt(0),
                new Mat(), hist_1, histSize, ranges);
        Imgproc.calcHist(Arrays.asList(D), new MatOfInt(0),
                new Mat(), hist_2, histSize, ranges);

        System.out.println(hist_1);
        System.out.println(hist_2);

        double res = Imgproc.compareHist(hist_1, hist_2, Imgproc.CV_COMP_CORREL);
        Imgproc.calcHist(matList, channels, mask, hist, histSize, ranges);
        Core.randu(hist_1, 1, 10);
        Plot2d plot = Plot2d.create(data);
        Mat mplot = new Mat();
        plot.setPlotLineColor(new Scalar(0,0,255));
        plot.render(mplot);
        Imgcodecs.imwrite("e:/test.png", mplot);

         Double d = new Double(res * 100);
        System.out.println(res);
        //ar trebui sa afisez ploturile histogramelor ptc nu imi afiseaza corect pt CORREL-> asta ptc "n-are limita superioara" vezi Doamne
        //use the others
        //indicatia ar fi sa export un csv si sa fac plot cu R studio maybeh :D
    }
}
