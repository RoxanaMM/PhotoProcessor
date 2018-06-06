package com.photos.main;

import com.photos.build.model.Model;
import com.photos.enums.AlgoName;
import com.photos.enums.TypesOfPhotos;
import com.photos.filters.FilteredBlue;
import com.photos.filters.FilteredRed;
import com.photos.filters.FilteredSepia;
import com.photos.processing.Calculate;
import com.photos.processing.Coloured;
import com.photos.processing.GrayScale;
import org.opencv.core.Core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainClass extends Calculate {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    static int i = 0;
    // hue varies from 0 to 179, see cvtColor
    static float[] hranges = {0, 180};
    // saturation varies from 0 (black-gray-white) to
    // 255 (pure spectrum color)
    static float[] sranges = {0, 256};
    static float[][] ranges = {hranges, sranges};
    public static final int MAX_PIXELS = 256;
    private float[][] firstPicPdf;
    static Map<AlgoName, Model> algoPowerModel = new HashMap<AlgoName, Model>();
    static Model modelHelper = new Model();
    static List<Model> m = new ArrayList<Model>(51);
    private FilteredSepia filteredSepia;

    public static void compareSimilarityVector(double[]similarities){
        // Euclidian -> 0 similar else nesimilar
        //intersectii de hitograme => 1 inseamna similar 0 nesimilar
        //la vectori
        // 0 -> 0 similaritate
        // valori mari -> similaritate mare
        //valori mici cu - -> directii opuse
        for(Map.Entry<AlgoName, Model> entry: algoPowerModel.entrySet()){
            if (entry.getKey().name().contains("similarity")){
                System.out.println("Similarity values: 1-> similar , 0 -> nesimilar ");

            }else{
                System.out.println("Distance values: 0-> similar, 1-> not similar");
            }
        }

    }

    public static void helperAlgorithmPower(TypesOfPhotos typesOfPhotos, double[] calculateTheDistance){
        for(int k =0;k<51;k++){
            modelHelper.setValuesPerType(typesOfPhotos, calculateTheDistance[k]);
            m.add(modelHelper );
        }
        List<AlgoName> algoNameList = new ArrayList<AlgoName>();
        for (AlgoName type : AlgoName.values()) {
            algoNameList.add(type);
        }
        for (int k=0;k<50;k++) {
            algoPowerModel.put(algoNameList.get(k),m.get(k));
        }
    }
    public static void main(String[] args) throws Exception {

        String filePath = "C:\\forMaster\\temaDisertatie\\pozeNesimilareGreyScaleTransform";
        //  String filePath = "C:\\forMaster\\temaDisertatie\\pozeSimilareGreyScaleTransform";
        File folder = new File(filePath);
        GrayScale greyScale = new GrayScale();
        Coloured coloured = new Coloured();

        double[] calculateTheDistance = new double[51];

        float[][] greyscleValues = greyScale.convertImageToGrey(folder);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length-1; i++) {
            calculateTheDistance = calculateDistance(greyscleValues[i], greyscleValues[i + 1], TypesOfPhotos.Greyscale);
        }

       helperAlgorithmPower(TypesOfPhotos.Greyscale, calculateTheDistance);

        float[][][] colouredPdfs = coloured.calculateAndDrawHistogram(folder);
        for (int i = 0; i < listOfFiles.length - 1; i++) {
            calculateTheDistance = calculateDistance(colouredPdfs[i][0], colouredPdfs[i + 1][0], TypesOfPhotos.Coloured);
            calculateTheDistance = calculateDistance(colouredPdfs[i][1], colouredPdfs[i + 1][1], TypesOfPhotos.Coloured);
            calculateTheDistance = calculateDistance(colouredPdfs[i][2], colouredPdfs[i + 1][2], TypesOfPhotos.Coloured);
        }
        helperAlgorithmPower(TypesOfPhotos.Coloured, calculateTheDistance);

        compareSimilarityVector(calculateTheDistance);
//        FilteredSepia filteredSepia = new FilteredSepia();
//        filteredSepia.filter();
//        FilteredBlue filteredBlue = new FilteredBlue();
//        filteredBlue.filter();
//        FilteredRed filteredRed = new FilteredRed();
//        filteredRed.filter();

    }
}