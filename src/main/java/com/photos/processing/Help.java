package com.photos.processing;

import com.photos.algorithms.Algorithms;
import com.photos.build.model.Results;
import com.photos.enums.AlgoName;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Help extends Algorithms implements Command {
    public Help(Results results, float P[], float Q[]) {
        executeAlgorithm(results.getAlgoName(),P,Q);

    }

    public Help() {
    }

    public static Object executeAlgorithm(AlgoName algoName, float P[], float Q[]) {
        Class<?> className = null;
        Object damerge=new float[256];
        try {
            className = Class.forName("com.photos.algorithms.Algorithms");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object completeClassName = null;
        try {
            completeClassName = className.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Class<?>[]paramTypes = {float[].class, float[].class};
        Method method = null;
        try {
            method = completeClassName.getClass().getDeclaredMethod(algoName.name(),paramTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            damerge= method.invoke(completeClassName, P,Q);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return damerge;
    }

    @Override
    public Object execute(AlgoName algoName, float[] P, float[] Q) {
        return executeAlgorithm(algoName,P,Q);
    }
}
