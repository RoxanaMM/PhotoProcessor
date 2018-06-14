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

import javax.jws.WebParam;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;

public class MainClass extends Calculate {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    static int i = 0;
    static Model modelHelper = new Model();
    static Map<AlgoName, double[]> algoPowerModel = new HashMap<AlgoName, double[]>();
    static Map<Integer, AlgoName> algoName = new HashMap<Integer, AlgoName>();
    static Map<TypesOfSet, Object> power = new HashMap<TypesOfSet, Object>();
    static Map<Integer, Results> results = new HashMap<Integer, Results>();
    private FilteredSepia filteredSepia;


    //channels e 3 pt Coloured si 1 pentru Greyscale
    public static void createFileWithResults(String name1, String name2, int channels, double[][] matrValues) {
        //:\forMaster\temaDisertatie\results
        String fileName = new String("C:\\forMaster\\temaDisertatie\\results\\");
        fileName = fileName.concat(name1).concat("__").concat(name2);
        String txtName = fileName.concat("\\").concat(name1).concat("__").concat(name2).concat(".txt");


        new File(fileName).mkdir();

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(txtName, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Model model = new Model();
        Map<Integer,AlgoName>algoNameMap = model.getAlgoNameMap();
        for (int i = 0; i < channels; i++) {
            writer.println("\n \n \n The" + i + "-th channel");
            for(int j=0; j<AlgorithmConstants.NR_OF_ALGORITHMS-1; j++){
                writer.print(algoNameMap.get(j) + " = ");
                writer.print(matrValues[i][j]);
                writer.println("");
            }
        }
        writer.close();

    }

    public static void powerOfResults(Map<Integer, Results> results) {
        // Euclidian -> 0 similar else nesimilar
        //intersectii de hitograme => 1 inseamna similar 0 nesimilar
        //la vectori
        // 0 -> 0 similaritate
        // valori mari -> similaritate mare
        //valori mici cu - -> directii opuse

        ///////results.get(1).calculateTheDistanceRGB[0][i]!!!!!!!!!
        //results.get(1).calculateTheDistance
        for (int i = 0; i < results.size(); i++) {
            for (int j = 0; j < AlgorithmConstants.NR_OF_ALGORITHMS; j++) {
                if (!algoName.get(i).name().contains("similarity")) {

                }
//                Results result = results.get(i);
//                createFileWithResults(result.getPicA(), results.get(i).getPicB(), result.getCalculateTheDistanceRGB().length,result.getCalculateTheDistanceRGB());

            }
        }
    }


    public static Map<Integer, Results> greyScaleHelper(String filePath, TypesOfSet typesOfSet, double[][] calculateTheDistance) throws Exception {

        Map<Integer, String> helperConverterGreyScalesVal = new HashMap<Integer, String>();
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        GrayScale greyScale = new GrayScale();
        Map<String, Object> greyscaleValuesWithNames = greyScale.convertImageToGrey(folder);
        helperConverterGreyScalesVal = greyScale.convertMe(folder);
        float[][] greyscleValues = new float[listOfFiles.length][AlgorithmConstants.NR_OF_PIXELS];


        for (int i = 0; i < listOfFiles.length; i++) {
            greyscleValues[i] = (float[]) greyscaleValuesWithNames.get(helperConverterGreyScalesVal.get(i));
        }
        int k = 0;
        System.out.println("GreyScale");
        for (int i = 0; i < listOfFiles.length * (listOfFiles.length / 2); i++) {
            for (int j = i + 1; j < listOfFiles.length - 1; j++) {
                calculateTheDistance[k] = calculateDistance(greyscleValues[i], greyscleValues[j], TypesOfSet.Greyscale);
                System.out.println("Distance from pic  " + helperConverterGreyScalesVal.get(i) + " in file and pic " + helperConverterGreyScalesVal.get(j));
                results.put(k, new Results(helperConverterGreyScalesVal.get(i), helperConverterGreyScalesVal.get(j), typesOfSet, calculateTheDistance[k]));
                k++;
            }
        }
        //   algoPowerModel = modelHelper.buildModel(calculateTheDistance,listOfFiles.length);
        //    power.put(TypesOfSet.Greyscale, algoPowerModel);
        return results;
    }

    public static Map<Integer, Results> colouredHelper(String filePath, TypesOfSet typesOfSet, double[][] calculateTheDistance) throws Exception {
        Map<Integer, String> helperConverterColouredVal = new HashMap<Integer, String>();
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        Coloured coloured = new Coloured();
        Map<String, Object> colouredValuesWithNames = coloured.calculateAndDrawHistogram(folder);
        helperConverterColouredVal = coloured.convertMe(folder);
        float[][][] colouredPdfs = new float[listOfFiles.length][3][AlgorithmConstants.NR_OF_PIXELS];
        double[][][] calculateTheDistance1 = new double[listOfFiles.length * (listOfFiles.length / 2)][3][AlgorithmConstants.NR_OF_PIXELS];
        for (int i = 0; i < listOfFiles.length; i++) {
            colouredPdfs[i] = (float[][]) colouredValuesWithNames.get(helperConverterColouredVal.get(i));
        }

        int k = 0;
        for (int i = 0; i < listOfFiles.length * (listOfFiles.length / 2); i++) {
            for (int j = i + 1; j < listOfFiles.length - 1; j++) {
                calculateTheDistance[k] = calculateDistance(colouredPdfs[i][0], colouredPdfs[j][0], TypesOfSet.Coloured);
                calculateTheDistance[k] = calculateDistance(colouredPdfs[i][1], colouredPdfs[j][1], TypesOfSet.Coloured);
                calculateTheDistance[k] = calculateDistance(colouredPdfs[i][2], colouredPdfs[j][2], TypesOfSet.Coloured);

                calculateTheDistance1[k][0] = calculateDistance(colouredPdfs[i][0], colouredPdfs[j][0], TypesOfSet.Coloured);
                calculateTheDistance1[k][1] = calculateDistance(colouredPdfs[i][1], colouredPdfs[j][1], TypesOfSet.Coloured);
                calculateTheDistance1[k][2] = calculateDistance(colouredPdfs[i][2], colouredPdfs[j][2], TypesOfSet.Coloured);

                results.put(k, new Results(helperConverterColouredVal.get(i), helperConverterColouredVal.get(j), typesOfSet, calculateTheDistance1[k]));

                k++;
            }
        }
        //  algoPowerModel = modelHelper.buildModel(calculateTheDistance,listOfFiles.length);
        //  power.put(TypesOfSet.Coloured, algoPowerModel);

        return results;
    }

    public static void main(String[] args) throws Exception {


//        String filePath = "C:\\forMaster\\temaDisertatie\\test\\gri-nesimilare";
        String filePath = "C:\\forMaster\\temaDisertatie\\test\\toBeGreyScalled-nesimilare";
//        String filePath = "C:\\forMaster\\temaDisertatie\\test\\gri-similare";
        //  String filePath = "C:\\forMaster\\temaDisertatie\\test\\faces-similare";
//        String filePath = "C:\\forMaster\\temaDisertatie\\test\\faces-nesimilare";

        int o = 0;
        for (AlgoName name : AlgoName.values()) {
            algoName.put(o, name);
            o++;
        }

        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();

        double[][] calculateTheDistance = new double[listOfFiles.length * (listOfFiles.length / 2)][AlgorithmConstants.NR_OF_ALGORITHMS];

        //1.GREYSCALE
        //   results = greyScaleHelper(filePath,TypesOfSet.Greyscale,calculateTheDistance);

        //2.COLOURED
        results = colouredHelper(filePath, TypesOfSet.Coloured, calculateTheDistance);

        powerOfResults(results);

    }
}