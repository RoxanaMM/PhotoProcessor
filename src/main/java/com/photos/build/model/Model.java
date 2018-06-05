package com.photos.build.model;

import com.photos.enums.TypesOfPhotos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    Map<TypesOfPhotos, double[]> valuesPerType= new HashMap<TypesOfPhotos, double[]>();

    public Map<TypesOfPhotos, double[]> getValuesPerType() {
        return valuesPerType;
    }

    public void setValuesPerType(TypesOfPhotos typesOfPhotos, double[] valuesPerType) {
        this.valuesPerType.put(typesOfPhotos,valuesPerType);
    }
}
