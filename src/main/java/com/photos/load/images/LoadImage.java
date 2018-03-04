package com.photos.load.images;

import com.photos.algorithms.Algorithms;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;

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
        String filePath = "E:\\MASTER\\an2\\disertatie\\temaDisertatie\\Original Brodatz";
        File folder = new File(filePath);
        readPixels(folder);
        //showPixelFrequencies(frequencyOfFirstPic,frequencyOfSecondPic);
        //  int [] frequency = calculateFrequency(matrix);
    }
}
