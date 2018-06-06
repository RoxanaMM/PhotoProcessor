package com.photos.processing;

import com.photos.algorithms.Algorithms;
import com.photos.build.model.Model;
import com.photos.enums.AlgoName;
import com.photos.enums.TypesOfPhotos;
import com.photos.algorithms.Algorithms.*;

import java.util.HashMap;
import java.util.Map;

public class Calculate extends Algorithms{

    public static double[] calculateDistance(float frequencyOfFirstPic[], float frequencyOfSecondPic[],
                                         TypesOfPhotos typesOfPhotos) {
        double[] similarities = new double[51];
        float[] R = new float[256];
        int p = 0;
        for (int i = 0; i < R.length; i++) {
            R[i] = 100;
        }
        Model m = new Model();
        similarities[0] = euclidianL2(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[1] = cityBlockL1(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[2] = minkowskiLp(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[3] = cebyshevLinf(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[4] = sorensen(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[5] = gower(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[6] = soergel(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[7] = kulczynskid(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[8] = canberra(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[9] = lorentzian(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[10] = intersectionDistance(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[11] = waveHedgesDistance(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[12] = distanceCzekanowski(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[13] = distanceMotyka(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[14] = distanceKulczynkyS(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[15] = ruzickaDistance(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[16] = tanimoto(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[20] = innerProductSimilarity(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[21] = harmonicMeanSimilarity(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[22] = cosineSimilarity(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[23] = kumarHassebrookDistance(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[24] = similarityJaccard(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[25] = distanceJaccard(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[26] = similarityDice(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[27] = distanceDice(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[28] = similarityFidelity(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[29] = distanceBhattacharyya(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[30] = distanceHellinger(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[31] = distanceMatusita(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[32] = distanceSquaredChord(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[34] = distanceSquaredEuclidian(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[35] = distancePearson(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[36] = distanceNeyman(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[37] = distanceSquared(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[38] = distanceProbabilisticSymmetric(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[39] = distanceDivergence(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[40] = distanceClark(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[41] = distanceAdditiceSymmetric(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[42] = distanceKullbackLeibler(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[43] = distanceJeffreys(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[44] = distanceKDivergence(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[45] = distanceTopsoe(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[46] = distanceJensenShannon(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[47] = distanceJensenDifference(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[48] = distanceKumarJohnson(frequencyOfFirstPic, frequencyOfSecondPic);
        similarities[49] = distanceAvg(frequencyOfFirstPic, frequencyOfSecondPic);

    return similarities;
    }
}
