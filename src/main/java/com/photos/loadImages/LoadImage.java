package com.photos.loadImages;

import com.photos.build.model.Model;
import com.photos.enums.AlgoName;
import com.photos.algorithms.Algorithms;
import com.photos.enums.TypesOfPhotos;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.io.*;
import java.util.*;


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
    static Map<AlgoName,Double> algoPower= new HashMap<AlgoName,Double>();
    static Map<AlgoName, Model>algoPowerModel = new HashMap<AlgoName, Model>();
    static double[]similarities = new double[51];
    static Model m = new Model();
    public static float[] convertToGreyHelper(Mat A){
        float[] floatArrayA = new float[256];
        Mat destinImg = new Mat(A.rows(), A.cols(), A.type());
        Imgproc.cvtColor(A,destinImg,Imgproc.COLOR_RGB2GRAY);

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
        for(int i = 0 ;i<bHist.rows();i++) {
            for (int j = 0; j < bHist.cols(); j++) {
                for (int k = 0; k < bHist.get(i, j).length; k++) {
                    floatArrayA[i] = (float)Math.round((bHist.get(i, j)[k]* 100.0) / 100.0);
                }
            }
        }
        return floatArrayA;
    }

    public static void convertImageToGrey(File folder) throws IOException {
        float[][] firstPicPdf = new float[6][256];
        float first;
        int i=0;
        int histSize = 256;
        int hist_w = 512;
        int hist_h = 400;
        int bin_w = (int) ((double) hist_w / histSize);

        File[] listOfFiles = folder.listFiles();
        for (File firstFileEntry : listOfFiles) {
            for (File fileEntry : listOfFiles) {
                if (fileEntry.getAbsolutePath().contains("jpg")) {
                    Mat A = Imgcodecs.imread(fileEntry.getAbsolutePath());

                    Mat destinImg = new Mat(A.rows(), A.cols(), A.type());
                    Imgproc.cvtColor(A,destinImg,Imgproc.COLOR_RGB2GRAY);

                    Mat hist = new Mat(256, 1, CvType.CV_8UC1);
                    MatOfFloat ranges = new MatOfFloat(0, 256);
                    MatOfInt channels = new MatOfInt(0);

                    List<Mat> bgrPlanes = new ArrayList<>();
                    Core.split(destinImg, bgrPlanes);

                    Mat bHist = new Mat();

                    Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(), bHist, new MatOfInt(histSize), ranges, false);

                    Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC1, new Scalar(0));
                    Core.normalize(bHist, bHist, 0, histImage.rows(), Core.NORM_MINMAX);

                    float[] bHistData = new float[(int) (bHist.total() * bHist.channels())];
                    bHist.get(0, 0, bHistData);

                    for (int k = 1; k < histSize; k++) {
                        Imgproc.line(histImage, new Point(bin_w * (k - 1), hist_h - Math.round(bHistData[k - 1])),
                                new Point(bin_w * (k), hist_h - Math.round(bHistData[k])), new Scalar(255, 0, 0), 1);

                    }

                    String pathResult = "C:\\forMaster\\savedHisto\\";
                    pathResult = pathResult.concat(fileEntry.getName());
                    Imgcodecs.imwrite(pathResult, histImage);

                    if(i<=5)//asta ptc stiu cate poze am in foolder ^______________________________^
                        firstPicPdf[i++] = convertToGreyHelper(A);
                }
            }
        }
            calculateDistance(firstPicPdf[0],firstPicPdf[1], TypesOfPhotos.Greyscale);
    }


    public static float[][] colouredHelper(Mat A){
        float[][]floatArray = new float[3][256];
        int histSize = 256;
        int hist_w = 512;
        int hist_h = 400;
        int bin_w = (int) ((double) hist_w / histSize);

        Mat hist = new Mat(256, 1, CvType.CV_8UC1);
        MatOfFloat ranges = new MatOfFloat(0, 256);
        MatOfInt channels = new MatOfInt(3);

        List<Mat> bgrPlanes = new ArrayList<>();
        Core.split(A, bgrPlanes);

        Mat bHist = new Mat(),gHist = new Mat(),rHist = new Mat();

        Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(), bHist, new MatOfInt(histSize), ranges, false);
        for(int i = 0 ;i<bHist.rows();i++) {
            for (int j = 0; j < bHist.cols(); j++) {
                for (int k = 0; k < bHist.get(i, j).length; k++) {
                    floatArray[0][i] = (float)Math.round((bHist.get(i, j)[k]* 100.0) / 100.0);
                }
            }
        }

        Imgproc.calcHist(bgrPlanes, new MatOfInt(1), new Mat(), gHist, new MatOfInt(histSize), ranges, false);
        for (int i = 0; i < gHist.rows(); i++) {
            for (int j = 0; j < gHist.cols(); j++) {
                for (int k = 0; k < gHist.get(i, j).length; k++) {
                    floatArray[1][i] = (float) Math.round((gHist.get(i, j)[k] * 100.0) / 100.0);
                }
            }
        }
        Imgproc.calcHist(bgrPlanes, new MatOfInt(2), new Mat(), rHist, new MatOfInt(histSize), ranges, false);
        for (int i = 0; i < rHist.rows(); i++) {
            for (int j = 0; j < rHist.cols(); j++) {
                for (int k = 0; k < rHist.get(i, j).length; k++) {
                    floatArray[2][i] = (float) Math.round((rHist.get(i, j)[k] * 100.0) / 100.0);
                }
            }
        }


        return floatArray;
    }


    public static void calculateAndDrawHistogram(File folder) throws IOException {
        int histSize = 256;
        int hist_w = 512;
        int hist_h = 400;
        int bin_w = (int) ((double) hist_w / histSize);
        float[][][] floatArray = new float[2][3][256];
        File[] listOfFiles = folder.listFiles();
        for (File firstFileEntry : listOfFiles) {
            for (File fileEntry : listOfFiles) {
                if (fileEntry.getAbsolutePath().contains("jpg")) {

                    Mat A = Imgcodecs.imread(fileEntry.getAbsolutePath());
                    Mat hist = new Mat(256, 1, CvType.CV_8UC1);
                    MatOfFloat ranges = new MatOfFloat(0, 256);
                    MatOfInt channels = new MatOfInt(3);

                    List<Mat> bgrPlanes = new ArrayList<>();
                    Core.split(A, bgrPlanes);

                        Mat bHist = new Mat(), gHist = new Mat(), rHist = new Mat();

                        Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(), bHist, new MatOfInt(histSize), ranges, false);
                        Imgproc.calcHist(bgrPlanes, new MatOfInt(1), new Mat(), gHist, new MatOfInt(histSize), ranges, false);
                        Imgproc.calcHist(bgrPlanes, new MatOfInt(2), new Mat(), rHist, new MatOfInt(histSize), ranges, false);

                        //primul array este pentru blue, al doilea red, al 3-lea green


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
                                    new Point(bin_w * (i), hist_h - Math.round(bHistData[i])), new Scalar(255, 0, 0), 1);
                            Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(gHistData[i - 1])),
                                    new Point(bin_w * (i), hist_h - Math.round(gHistData[i])), new Scalar(0, 255, 0), 1);
                            Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(rHistData[i - 1])),
                                    new Point(bin_w * (i), hist_h - Math.round(rHistData[i])), new Scalar(0, 0, 255), 1);
                        }



                if(i<2)//asta ptc stiu cate poze am in foolder ^______________________________^
                    floatArray[i++] = colouredHelper(A);

                calculateDistance(floatArray[0][0], floatArray[1][0], TypesOfPhotos.Coloured);
                calculateDistance(floatArray[0][1], floatArray[1][1], TypesOfPhotos.Coloured);
                calculateDistance(floatArray[0][2], floatArray[1][2], TypesOfPhotos.Coloured);

                String pathResult = "C:\\forMaster\\temaDisertatie\\histogramePozeSimilare\\";
                pathResult = pathResult.concat(fileEntry.getName());
                Imgcodecs.imwrite(pathResult, histImage);
                }
            }

        }
    }


    public static void calculateDistance(float frequencyOfFirstPic[], float frequencyOfSecondPic[], TypesOfPhotos typesOfPhotos) {
        int p = 0;
        for (int i = 0; i < R.length; i++) {
            R[i] = 100;
        }
            similarities[0] = euclidianL2(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.euclidianL2, similarities[0]);
            similarities[1] = cityBlockL1(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.cityBlockL1, similarities[1]);
            similarities[2] = minkowskiLp(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.minkowskiLp, similarities[2]);
            similarities[3] = cebyshevLinf(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.cebyshevLinf, similarities[3]);
            similarities[4] = sorensen(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.sorensen, similarities[4]);
            similarities[5] = gower(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.gower, similarities[5]);
            similarities[6] = soergel(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.soergel, similarities[6]);
            similarities[7] = kulczynskid(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.kulczynskid, similarities[7]);
            similarities[8] = canberra(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.canberra, similarities[8]);
            similarities[9] = lorentzian(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.lorentzian, similarities[9]);
            similarities[10] = intersectionDistance(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.intersectionDistance, similarities[10]);
            similarities[11] = waveHedgesDistance(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.waveHedgesDistance, similarities[11]);
            similarities[12] = distanceCzekanowski(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceCzekanowski, similarities[12]);
            similarities[13] = distanceMotyka(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceMotyka, similarities[13]);
            similarities[14] = distanceKulczynkyS(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceKulczynkyS, similarities[14]);
            similarities[15] = ruzickaDistance(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.ruzicka, similarities[18]);
            similarities[16] = tanimoto(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.tanimoto, similarities[19]);
            similarities[20] = innerProductSimilarity(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.innerProductSimilarity, similarities[20]);
            similarities[21] = harmonicMeanSimilarity(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.harmonicMean, similarities[21]);
            similarities[22] = cosineSimilarity(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.cosine, similarities[22]);
            similarities[23] = kumarHassebrookDistance(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.kumarHassebrookDistance, similarities[23]);
            similarities[24] = similarityJaccard(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.similarityJaccard, similarities[24]);
            similarities[25] = distanceJaccard(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceJaccard, similarities[25]);
            similarities[26] = similarityDice(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.similarityDice, similarities[26]);
            similarities[27] = distanceDice(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceDice, similarities[27]);
            similarities[28] = similarityFidelity(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.similarityFidelity, similarities[28]);
            similarities[29] = distanceBhattacharyya(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceBhattacharyya, similarities[29]);
            similarities[30] = distanceHellinger(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceHellinger, similarities[30]);
            similarities[31] = distanceMatusita(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceMatusita, similarities[31]);
            similarities[32] = distanceSquaredChord(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceSquaredChord, similarities[32]);
            similarities[34] = distanceSquaredEuclidian(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceSquaredEuclidian, similarities[34]);
            similarities[35] = distancePearson(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distancePearson, similarities[35]);
            similarities[36] = distanceNeyman(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceNeyman, similarities[36]);
            similarities[37] = distanceSquared(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceSquared, similarities[37]);
            similarities[38] = distanceProbabilisticSymmetric(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceProbabilisticSymmetric, similarities[38]);
            similarities[39] = distanceDivergence(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceDivergence, similarities[39]);
            similarities[40] = distanceClark(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceClark, similarities[40]);
            similarities[41] = distanceAdditiceSymmetric(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceAdditiceSymmetric, similarities[41]);
            similarities[42] = distanceKullbackLeibler(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceKullbackLeibler, similarities[42]);
            similarities[43] = distanceJeffreys(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceJeffreys, similarities[43]);
            similarities[44] = distanceKDivergence(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceKDivergence, similarities[44]);
            similarities[45] = distanceTopsoe(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceTopsoe, similarities[45]);
            similarities[46] = distanceJensenShannon(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceJensenShannon, similarities[46]);
            similarities[47] = distanceJensenDifference(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceJensenDifference, similarities[47]);
            similarities[48] = distanceKumarJohnson(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceKumarJohnson, similarities[48]);
            similarities[49] = distanceAvg(frequencyOfFirstPic, frequencyOfSecondPic);
        algoPower.put(AlgoName.distanceAvg, similarities[49]);

        m.setValuesPerType(typesOfPhotos,similarities);
        compareSimilarityVector(similarities);
    }
    public static void compareSimilarityVector(double[]similarities){
           // Euclidian -> 0 similar else nesimilar
        //intersectii de hitograme => 1 inseamna similar 0 nesimilar
        //la vectori
        // 0 -> 0 similaritate
        // valori mari -> similaritate mare
        //valori mici cu - -> directii opuse
       for(Map.Entry<AlgoName, Double> entry: algoPower.entrySet()){
           if (entry.getKey().name().contains("similarity")){
               System.out.println("Similarity values: 1-> similar , 0 -> nesimilar ");

           }else{
               System.out.println("Distance values: 0-> similar, 1-> not similar");
           }
       }

    }
    public static void main(String[] args) throws Exception {

        //    String filePath = "C:\\forMaster\\temaDisertatie\\pozeNesimilareGreyScaleTransform";
      String filePath = "C:\\forMaster\\temaDisertatie\\pozeSimilareGreyScaleTransform";
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
        calculateAndDrawHistogram(folder);

    }
}
