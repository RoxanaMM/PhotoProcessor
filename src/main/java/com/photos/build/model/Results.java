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

    public String getPicA() {
        return picA;
    }

    public void setPicA(String picA) {
        this.picA = picA;
    }

    public String getPicB() {
        return picB;
    }

    public void setPicB(String picB) {
        this.picB = picB;
    }

    public TypesOfSet getTypesOfSet() {
        return typesOfSet;
    }

    public void setTypesOfSet(TypesOfSet typesOfSet) {
        this.typesOfSet = typesOfSet;
    }

    public double[] getCalculateTheDistance() {
        return calculateTheDistance;
    }

    public void setCalculateTheDistance(double[] calculateTheDistance) {
        this.calculateTheDistance = calculateTheDistance;
    }

    public double[][] getCalculateTheDistanceRGB() {
        return calculateTheDistanceRGB;
    }

    public void setCalculateTheDistanceRGB(double[][] calculateTheDistanceRGB) {
        this.calculateTheDistanceRGB = calculateTheDistanceRGB;
    }
}
