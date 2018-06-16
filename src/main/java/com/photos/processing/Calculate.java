package com.photos.processing;

import com.photos.algorithms.Algorithms;
import com.photos.build.model.Model;
import com.photos.build.model.Results;
import com.photos.enums.AlgoName;
import com.photos.enums.TypesOfSet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Calculate extends Algorithms {
    public static HashMap<AlgoName,Command> keepAlgoFunc = new HashMap<AlgoName,Command>();

    public static void calculateDistance(Results results,float P[], float Q[]) {

        for(AlgoName a: AlgoName.values()) {
            keepAlgoFunc.put(a, new Help(results,P,Q));
        }
        receiveCommand(results.getAlgoName(),P,Q);
    }

    public static void receiveCommand(AlgoName command, float P[], float Q[]) {
        Object damerge = keepAlgoFunc.get(command).execute(command,P,Q);
        System.out.println(damerge.toString());
    }
}
