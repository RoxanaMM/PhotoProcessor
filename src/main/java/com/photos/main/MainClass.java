package com.photos.main;

import com.photos.build.model.Model;
import com.photos.enums.AlgoName;
import com.photos.enums.AlgorithmConstants;
import com.photos.enums.TypesOfSet;
import com.photos.filters.FilteredSepia;
import com.photos.processing.Calculate;
import com.photos.processing.Coloured;
import com.photos.processing.GrayScale;
import org.opencv.core.Core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MainClass extends Calculate {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    static int i = 0;
    static Model modelHelper = new Model();
    static Map<AlgoName, double[]> algoPowerModel = new HashMap<AlgoName, double[]>();
    static Map<TypesOfSet,Object > power = new HashMap<TypesOfSet,Object >();
    private FilteredSepia filteredSepia;

    public static void compareSimilarityVector(double[]similarities){
        // Euclidian -> 0 similar else nesimilar
        //intersectii de hitograme => 1 inseamna similar 0 nesimilar
        //la vectori
        // 0 -> 0 similaritate
        // valori mari -> similaritate mare
        //valori mici cu - -> directii opuse
//        for(Map.Entry<AlgoName, Model> entry: algoPowerModel.entrySet()){
//            if (entry.getKey().name().contains("similarity")){
//                System.out.println("Similarity values: 1-> similar , 0 -> nesimilar ");
//
//            }else{
//                System.out.println("Distance values: 0-> similar, 1-> not similar");
//            }
//        }

    }


    public static Map<TypesOfSet,Object> greyScaleHelper(String filePath, TypesOfSet typesOfSet, double[][] calculateTheDistance) throws Exception{
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        GrayScale greyScale = new GrayScale();
        float[][] greyscleValues = greyScale.convertImageToGrey(folder);
        for (int i = 0; i < listOfFiles.length-1; i++) {
            calculateTheDistance[i] = calculateDistance(greyscleValues[i], greyscleValues[i + 1], TypesOfSet.Greyscale);
        }
        algoPowerModel = modelHelper.buildModel(calculateTheDistance,listOfFiles.length);
        power.put(TypesOfSet.Greyscale, algoPowerModel);
        return power;
    }


    public static Map<TypesOfSet,Object> colouredHelper(String filePath, TypesOfSet typesOfSet, double[][] calculateTheDistance) throws Exception{
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        Coloured coloured = new Coloured();
        calculateTheDistance = new double[listOfFiles.length][AlgorithmConstants.NR_OF_ALGORITHMS];
        float[][][] colouredPdfs = coloured.calculateAndDrawHistogram(folder);
        for (int i = 0; i < listOfFiles.length - 1; i++) {
            calculateTheDistance[i] = calculateDistance(colouredPdfs[i][0], colouredPdfs[i + 1][0], TypesOfSet.Coloured);
            calculateTheDistance[i]= calculateDistance(colouredPdfs[i][1], colouredPdfs[i + 1][1], TypesOfSet.Coloured);
            calculateTheDistance[i] = calculateDistance(colouredPdfs[i][2], colouredPdfs[i + 1][2], TypesOfSet.Coloured);
        }
        algoPowerModel = modelHelper.buildModel(calculateTheDistance,listOfFiles.length);
        power.put(TypesOfSet.Coloured, algoPowerModel);

        return power;
    }

    public static void main(String[] args) throws Exception {

        String filePath = "C:\\forMaster\\temaDisertatie\\\\pozeSimilareGreyScaleTransform";
        //  String filePath = "C:\\forMaster\\temaDisertatie\\pozeSimilareGreyScaleTransform";
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();

        double[][] calculateTheDistance = new double[listOfFiles.length][AlgorithmConstants.NR_OF_ALGORITHMS];

        //1.GREYSCALE
        power = greyScaleHelper(filePath,TypesOfSet.Greyscale,calculateTheDistance);

        //2.COLOURED
        power = colouredHelper(filePath, TypesOfSet.Coloured, calculateTheDistance);

        compareSimilarityVector(calculateTheDistance[i]);

    }
}