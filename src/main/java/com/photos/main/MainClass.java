package com.photos.main;

import com.photos.build.model.Model;
import com.photos.build.model.Results;
import com.photos.enums.AlgoName;
import com.photos.enums.AlgorithmConstants;
import com.photos.enums.TypesOfSet;
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
    static Model modelHelper = new Model();
    static Map<AlgoName, double[]> algoPowerModel = new HashMap<AlgoName, double[]>();
    static Map<TypesOfSet,Object > power = new HashMap<TypesOfSet,Object >();
    static Map<Integer,Results> results = new HashMap<Integer, Results>();
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


    public static Map<Integer,Results> greyScaleHelper(String filePath, TypesOfSet typesOfSet, double[][] calculateTheDistance) throws Exception{

        Map<Integer, String>helperConverterGreyScalesVal= new HashMap<Integer, String>();
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        GrayScale greyScale = new GrayScale();
        Map<String, Object>greyscaleValuesWithNames = greyScale.convertImageToGrey(folder);
        helperConverterGreyScalesVal = greyScale.convertMe(folder);
        float[][] greyscleValues = new float[listOfFiles.length][AlgorithmConstants.NR_OF_PIXELS];


        for(int i =0;i<listOfFiles.length;i++){
            greyscleValues[i] = (float[]) greyscaleValuesWithNames.get(helperConverterGreyScalesVal.get(i));
        }
    int k =0;
        System.out.println("GreyScale");
        for (int i = 0; i < listOfFiles.length * (listOfFiles.length/2); i++) {
            for (int j = i+1; j < listOfFiles.length; j++) {
                calculateTheDistance[k] = calculateDistance(greyscleValues[i], greyscleValues[j], TypesOfSet.Greyscale);
                System.out.println("Distance from pic  " + helperConverterGreyScalesVal.get(i) + " in file and pic " + helperConverterGreyScalesVal.get(j));
                results.put(k,new Results(helperConverterGreyScalesVal.get(i), helperConverterGreyScalesVal.get(j),typesOfSet, calculateTheDistance[k]));
                k++;
            }
        }
     //   algoPowerModel = modelHelper.buildModel(calculateTheDistance,listOfFiles.length);
    //    power.put(TypesOfSet.Greyscale, algoPowerModel);
        return results;
    }

    public static Map<Integer,Results> colouredHelper(String filePath, TypesOfSet typesOfSet, double[][] calculateTheDistance) throws Exception{
        Map<Integer, String>helperConverterColouredVal= new HashMap<Integer, String>();
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        Coloured coloured = new Coloured();
        Map<String, Object>colouredValuesWithNames  = coloured.calculateAndDrawHistogram(folder);
        helperConverterColouredVal = coloured.convertMe(folder);
        float[][][] colouredPdfs = new float[listOfFiles.length][3][AlgorithmConstants.NR_OF_PIXELS];
        for(int i =0;i<listOfFiles.length;i++){
            colouredPdfs[i] = (float[][]) colouredValuesWithNames.get(helperConverterColouredVal.get(i));
        }

        int k =0;
        for (int i = 0; i < listOfFiles.length * (listOfFiles.length/2); i++) {
            for (int j = i + 1; j < listOfFiles.length-1; j++) {
                calculateTheDistance[k] = calculateDistance(colouredPdfs[i][0], colouredPdfs[j][0], TypesOfSet.Coloured);
                calculateTheDistance[k] = calculateDistance(colouredPdfs[i][1], colouredPdfs[j][1], TypesOfSet.Coloured);
                calculateTheDistance[k] = calculateDistance(colouredPdfs[i][2], colouredPdfs[j][2], TypesOfSet.Coloured);
                results.put(k,new Results(helperConverterColouredVal.get(i), helperConverterColouredVal.get(j),typesOfSet, calculateTheDistance[k]));
                k++;
            }
        }
      //  algoPowerModel = modelHelper.buildModel(calculateTheDistance,listOfFiles.length);
      //  power.put(TypesOfSet.Coloured, algoPowerModel);

        return results;
    }

    public static void main(String[] args) throws Exception {

        String filePath = "C:\\forMaster\\temaDisertatie\\\\pozeSimilareGreyScaleTransform";
        //  String filePath = "C:\\forMaster\\temaDisertatie\\pozeSimilareGreyScaleTransform";
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();

        double[][] calculateTheDistance = new double[listOfFiles.length * (listOfFiles.length/2)][AlgorithmConstants.NR_OF_ALGORITHMS];

        //1.GREYSCALE
        results = greyScaleHelper(filePath,TypesOfSet.Greyscale,calculateTheDistance);

        //2.COLOURED
        results = colouredHelper(filePath, TypesOfSet.Coloured, calculateTheDistance);

        //(calculateTheDistance[i]);

    }
}