package com.photos.processing;

import com.photos.enums.AlgoName;

public interface Command {
    Object execute(AlgoName algoName, float[] P, float[] Q);
}
