package com.photos.build.model;

import com.photos.enums.TypesOfPhotos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    Map<TypesOfPhotos, double[]> moreValuesPerType= new HashMap<TypesOfPhotos, double[]>();
    Map<TypesOfPhotos, Double> valuesPerType= new HashMap<TypesOfPhotos, Double>();
    public Map<TypesOfPhotos, double[]> getMoreValuesPerType() {
        return moreValuesPerType;
    }

    public void setMoreValuesPerType(TypesOfPhotos typesOfPhotos, double[] moreValuesPerType) {
        this.moreValuesPerType.put(typesOfPhotos,moreValuesPerType);
    }

    public Map<TypesOfPhotos, Double> getValuesPerType() {
        return valuesPerType;
    }

    public void setValuesPerType(TypesOfPhotos typesOfPhotos, Double moreValuesPerType) {
        this.valuesPerType.put(typesOfPhotos,moreValuesPerType);
    }
}
