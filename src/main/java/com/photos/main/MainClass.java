package com.photos.main;

import com.photos.build.model.Model;
import com.photos.build.model.Results;
import com.photos.enums.AlgoName;
import com.photos.enums.TypesOfSet;
import com.photos.processing.Calculate;
import com.photos.processing.Coloured;
import org.opencv.core.Core;

import java.io.*;
import java.util.Map;

public class MainClass extends Calculate {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) throws Exception {

        String filePath = "C:\\forMaster\\temaDisertatie\\test\\toBeGreyScalled-nesimilare";
        //        String filePath = "C:\\forMaster\\temaDisertatie\\test\\gri-nesimilare";
//        String filePath = "C:\\forMaster\\temaDisertatie\\test\\gri-similare";
        //  String filePath = "C:\\forMaster\\temaDisertatie\\test\\faces-similare";
//        String filePath = "C:\\forMaster\\temaDisertatie\\test\\faces-nesimilare";

        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();

        //teoretic trebuie de la tastatura, practic n-am interfata inca
        Results results = new Results();
        results.setPicA("C:\\forMaster\\temaDisertatie\\test\\color-similare\\1.jpg");
        results.setPicB("C:\\forMaster\\temaDisertatie\\test\\color-similare\\1.jpg");
        results.setTypesOfSet(TypesOfSet.Coloured);
        results.setAlgoName(AlgoName.similarityFidelity);
      //  results.setCalculatedDistances();
        Coloured coloured = new Coloured();
        //aici e trratat doar cazul in care se face o comparatie 1 la 1

        float[][] pixelArray3Channels1= coloured.calculateAndDrawHistogram(results.getPicA());
        float[][] pixelArray3Channels2= coloured.calculateAndDrawHistogram(results.getPicB());

        for(int i =0;i<pixelArray3Channels1.length;i++){
            calculateDistance(results, pixelArray3Channels1[i], pixelArray3Channels2[i]);
        }



    }
}