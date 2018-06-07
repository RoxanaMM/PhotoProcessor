package com.photos.build.model;

import com.photos.enums.TypesOfSet;

public class Results {
    String picA;
    String picB;
    TypesOfSet typesOfSet;
    double[] calculateTheDistance;
    double[][] calculateTheDistanceRGB;

    public Results(String picA, String picB, TypesOfSet typesOfSet, double[] calculateTheDistance) {
        this.picA = picA;
        this.picB = picB;
        this.typesOfSet = typesOfSet;
        this.calculateTheDistance = calculateTheDistance;
    }
    public Results(String picA, String picB, TypesOfSet typesOfSet, double[][] calculateTheDistanceRGB) {
        this.picA = picA;
        this.picB = picB;
        this.typesOfSet = typesOfSet;
        this.calculateTheDistanceRGB = calculateTheDistanceRGB;
    }

    public Results(String picA, String picB, TypesOfSet typesOfSet, double[] calculateTheDistance, double[][] calculateTheDistanceRGB) {
        this.picA = picA;
        this.picB = picB;
        this.typesOfSet = typesOfSet;
        this.calculateTheDistance = calculateTheDistance;
        this.calculateTheDistanceRGB = calculateTheDistanceRGB;
    }
}
