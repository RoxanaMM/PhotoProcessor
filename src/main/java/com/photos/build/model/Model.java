package com.photos.build.model;

import com.photos.enums.AlgoName;
import com.photos.enums.AlgorithmConstants;
import com.photos.enums.TypesOfSet;

import java.util.HashMap;
import java.util.Map;

public class Model{

    static Map<AlgoName, double[]> algoPowerModel = new HashMap<AlgoName, double[]>();
    static Map<Integer,AlgoName>algoNameMap = new HashMap<Integer, AlgoName>();
    static Map<Integer,TypesOfSet>typeOfSet = new HashMap<Integer, TypesOfSet>();

    public Model(Map<AlgoName, double[]> algoPowerModel){
        this.algoPowerModel = algoPowerModel;
    }
    public Model(){

    }

    public static Map<Integer, AlgoName> getAlgoNameMap() {
        buildNameMaps();
        return algoNameMap;
    }

    public static void buildNameMaps() {
        int z = 0;
        for (AlgoName algoName : AlgoName.values()) {
            algoNameMap.put(z, algoName);
            z++;
        }

        int t = 0;
        for (TypesOfSet type : TypesOfSet.values()) {
            typeOfSet.put(t, type);
            t++;
        }
    }

    public static Map<AlgoName, double[]> buildModel(double[][] calculateTheDistance, int length){
        buildNameMaps();
        int p=0;
        double [][]processed = new double[AlgorithmConstants.NR_OF_ALGORITHMS][length];
        for(int h=0;h<AlgorithmConstants.NR_OF_ALGORITHMS;h++){
            for(int l=0;l<length;l++){
                if(h==p) {
                    processed[h][l] = calculateTheDistance[l][h];
                }
            }
            p++;
        }
        for(int k=0;k<AlgorithmConstants.NR_OF_ALGORITHMS;k++){
            algoPowerModel.put(algoNameMap.get(k),processed[k]);
        }
        return algoPowerModel;
    }
}
