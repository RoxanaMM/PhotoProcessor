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
import java.lang.reflect.Type;
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
    static Map<AlgoName, double[]> algoPowerModel = new HashMap<AlgoName, double[]>();
    static Map<Integer,AlgoName>algoNameMap = new HashMap<Integer, AlgoName>();
    static Map<Integer,TypesOfSet>typeOfSet = new HashMap<Integer, TypesOfSet>();

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
//        for(Map.Entry<AlgoName, Model> entry: algoPowerModel.entrySet()){
//            if (entry.getKey().name().contains("similarity")){
//                System.out.println("Similarity values: 1-> similar , 0 -> nesimilar ");
//
//            }else{
//                System.out.println("Distance values: 0-> similar, 1-> not similar");
//            }
//        }

    }


    public static void main(String[] args) throws Exception {

        String filePath = "C:\\forMaster\\temaDisertatie\\\\pozeSimilareGreyScaleTransform";
        //  String filePath = "C:\\forMaster\\temaDisertatie\\pozeSimilareGreyScaleTransform";
        File folder = new File(filePath);
        GrayScale greyScale = new GrayScale();
        Coloured coloured = new Coloured();
        File[] listOfFiles = folder.listFiles();

        double[][] calculateTheDistance = new double[listOfFiles.length][AlgorithmConstants.NR_OF_ALGORITHMS];
        float[][] greyscleValues = greyScale.convertImageToGrey(folder);


        for (int i = 0; i < listOfFiles.length-1; i++) {
            calculateTheDistance[i] = calculateDistance(greyscleValues[i], greyscleValues[i + 1], TypesOfSet.Greyscale);
        }
        int z=0;
        for(AlgoName algoName:AlgoName.values()){
            algoNameMap.put(z,algoName);
            z++;
        }

        int t=0;
        for(TypesOfSet type : TypesOfSet.values()){
            typeOfSet.put(t,type);
            t++;
        }



        int p=0;
        double [][]processed = new double[AlgorithmConstants.NR_OF_ALGORITHMS][listOfFiles.length];
        for(int h=0;h<AlgorithmConstants.NR_OF_ALGORITHMS;h++){
            for(int l=0;l<listOfFiles.length;l++){
                if(h==p) {
                    processed[h][l] = calculateTheDistance[l][h];
                }
            }
            p++;
        }
        double [][]filler = new double[1][1];
        for(int k=0;k<AlgorithmConstants.NR_OF_ALGORITHMS;k++){
         algoPowerModel.put(algoNameMap.get(k),processed[k]);
        }

                calculateTheDistance = new double[listOfFiles.length][AlgorithmConstants.NR_OF_ALGORITHMS];
        float[][][] colouredPdfs = coloured.calculateAndDrawHistogram(folder);
        for (int i = 0; i < listOfFiles.length - 1; i++) {
            calculateTheDistance[i] = calculateDistance(colouredPdfs[i][0], colouredPdfs[i + 1][0], TypesOfSet.Coloured);
            calculateTheDistance[i]= calculateDistance(colouredPdfs[i][1], colouredPdfs[i + 1][1], TypesOfSet.Coloured);
            calculateTheDistance[i] = calculateDistance(colouredPdfs[i][2], colouredPdfs[i + 1][2], TypesOfSet.Coloured);
        }


        compareSimilarityVector(calculateTheDistance[i]);
//        FilteredSepia filteredSepia = new FilteredSepia();
//        filteredSepia.filter();
//        FilteredBlue filteredBlue = new FilteredBlue();
//        filteredBlue.filter();
//        FilteredRed filteredRed = new FilteredRed();
//        filteredRed.filter();

    }
}