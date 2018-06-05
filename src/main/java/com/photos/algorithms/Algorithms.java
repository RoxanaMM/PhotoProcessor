package com.photos.algorithms;

import java.util.Random;

import static java.lang.Math.*;
import static java.lang.Float.*;
import static java.lang.Math.log10;

/**
 * Created by Roxana on 12/25/2017.
 */
public class Algorithms {

    static float[] R = new float[1228800];
    public static float euclidianL2(float P[], float Q[]){
        float dEuc = 0;
        for(int i=0;i<P.length;i++){
            dEuc= (float) sum(dEuc, (float) pow(abs(P[i]-Q[i]),2));
        }
        return (float) sqrt(dEuc);
    }
    public static double cityBlockL1(float P[],float Q[]){
        float dCB = 0;
        for(int i=0;i<P.length;i++){
            dCB=sum(dCB,abs(P[i]) - abs(Q[i]));
        }
        return dCB;
    }
    public static double minkowskiLp(float P[],float Q[]){
        float dMk = 0;
        double dMkHelper;
        //ii voi da lui P o valoare random in range-ul (0,100)
        Random r = new Random();
        double precision=0.5;
        double pe = r.nextInt(100-0) + 0;
        for(int i=0;i<P.length;i++){
            dMk= (float) sum(dMk, (float) pow(abs(P[i]-Q[i]),pe));
        }
        dMk = (float)Math.pow(dMk, 1.0 / pe);
        if( Math.abs(dMk - Math.round(dMk)) < precision) {
            return dMk;
        }
        return 0;
    }
    public static double cebyshevLinf(float P[],float Q[]){
        float dChev = 0, dChevMax= dChev;
        for(int i=0;i<P.length;i++){
            dChev= abs(P[i]-Q[i]);
            if ( dChev > dChevMax)
                dChevMax = dChev;
        }
        return dChevMax;
    }
    public static double sorensen(float P[],float Q[]){
        float dSor = 0,numarator = 0, numitor = 0;
        for(int i=0;i<P.length;i++){
             numarator =(float) sum(numarator, (float) (abs(P[i]-Q[i])));
             numitor= (float) sum(numitor, (float) (abs(P[i]+Q[i])));
        }
        if(numitor != 0){
            dSor = numarator/numitor;
        }
        return dSor;
    }
    public static double gower(float P[],float Q[]){
        float dGow = 0, numarator=0, numitor =0;
        for(int i=0;i<P.length;i++){
            numarator =(float) sum(numarator, (float) (abs(P[i]-Q[i])));
        }
        ///aici e o problema cine e d? mometan e 1/p.length
        dGow = (float)0.003 * (numarator);
        return dGow;
    }
    public static double soergel(float P[],float Q[]){
        float dSg = 0, numarator=0, numitor =0;
        for(int i=0;i<P.length;i++) {
            numarator = (float) sum(numarator, (float) (abs(P[i] - Q[i])));
            numitor = (float) sum(numitor, Float.max(P[i], Q[i]));
        }
        if(numitor != 0){
            dSg = numarator/numitor;
        }
        return dSg;
    }
    public static double kulczynskid(float P[],float Q[]){
        float dKul = 0, numarator=0, numitor =0;
        for(int i=0;i<P.length;i++) {
            numarator = (float) sum(numarator, (float) (abs(P[i] - Q[i])));
            numitor = (float) sum(numitor, Float.min(P[i], Q[i]));
        }
        if(numitor != 0){
            dKul = numarator/numitor;
        }
        return dKul;
    }
    public static double canberra(float P[],float Q[]){
        float dCan = 0, numarator=0, numitor =0;
        for(int i=0;i<P.length;i++) {
            numarator = (float) sqrt(sum(numarator, (float) (abs(P[i] - Q[i]))));
            numitor = (float) sum(numitor, Float.min(P[i], Q[i]));
        }
        if(numitor != 0){
            dCan = numarator/numitor;
        }
        return dCan;
    }
    public static double lorentzian(float P[],float Q[]){
        float dLor= 0;
        for(int i=0;i<P.length;i++) {
            dLor = (float) sum(dLor, (float) Math.log10(1+abs(P[i] - Q[i])));
        }
        return dLor;
    }
    public static double intersectionSimilarity(float P[],float Q[]){
        float sIS= 0;
        for(int i=0;i<P.length;i++) {
            sIS = (float) sum(sIS, Float.min(P[i],Q[i]));
        }
        return sIS;
    }
    public static double intersectionDistance(float P[],float Q[]){
        return 1- intersectionSimilarity(P,Q);
    }
    public static double waveHedgesDistance(float P[],float Q[]){
        float dWH = 0, numarator=0, numitor =0, suma = 0;
        for(int i=0;i<P.length;i++) {
            numarator = Float.min(P[i],Q[i]);
            numitor = Float.max(P[i],Q[i]);
            if(numitor != 0)
                dWH = sum(dWH, 1-(numarator/numitor));
        }
        return dWH;
    }
    public static double similarityCzekanowski(float P[],float Q[]){
        float sCze = 0, numarator=0, numitor =0, suma = 0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = sum(numarator , Float.min(P[i],Q[i]));
            numitor = sum(numitor,P[i]+Q[i]);
        }
        numarator=2*numarator;
        if(numitor != 0)
            sCze = numarator/numitor;
        return sCze;
    }
    public static double distanceCzekanowski(float P[],float Q[]) {
        return 1-similarityCzekanowski(P,Q);
    }
    public static double similarityMotyka(float P[],float Q[]){
        float sMot = 10, numarator=0, numitor =0, suma = 0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = sum(numarator , Float.min(P[i],Q[i]));
            numitor = sum(numitor,P[i]+Q[i]);
        }
        if(numitor != 0)
            sMot =numarator/numitor;
        return sMot;
    }
    public static double distanceMotyka(float P[],float Q[]) {
        return 1-similarityMotyka(P,Q);
    }
    public static double similarityKulczynkyS(float P[],float Q[]){
        float sKul=10, numarator=0, numitor =0, suma = 0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = sum(numarator , Float.min(P[i],Q[i]));
            numitor = sum(numitor,abs(P[i]-Q[i]));
        }
        if(numitor != 0)
            sKul = numarator/numitor;
        return sKul;
    }
    public static double distanceKulczynkyS(float P[],float Q[]) {
        return 1/similarityKulczynkyS(P,Q);
    }
    public static double ruzicka(float P[],float Q[]){
        float sRuz = 20, numarator=0, numitor =0, suma = 0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = sum(numarator , Float.min(P[i],Q[i]));
            numitor = sum(numitor,Float.max(P[i],Q[i]));
        }
        if(numitor != 0)
            sRuz = numarator/numitor;
        return sRuz;
    }
    public static double ruzickaDistance(float P[],float Q[]){
        return 1-ruzicka(P,Q);
    }
    public static double tanimoto(float P[],float Q[]){
        float sRuz = 20, numarator=0, numitor =0, suma = 0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = sum(numarator , (Float.max(P[i],Q[i]) - Float.min(P[i],Q[i])));
            numitor = sum(numitor,Float.max(P[i],Q[i]));
        }
        if(numitor != 0)
            sRuz = numarator/numitor;
        return sRuz;
    }
    public static double innerProductSimilarity(float P[],float Q[]){
        float sIP = 0;
        for(int i = 0; i< P.length ; i++ ) {
            sIP = sum(sIP , P[i]*Q[i]);
        }
        return sIP;
    }
    public static double harmonicMeanSimilarity(float P[],float Q[]){
        float sHM = 10, numarator=0, numitor =0, suma = 0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = (P[i]*Q[i]);
            numitor =  (P[i] + Q[i]);
        }
        if(numitor != 0)
            sHM = numarator/numitor;
        return sHM;
    }
    public static double cosineSimilarity(float P[],float Q[]){
        float sCos = 10, numarator=0, numitor =0, suma = 0,p=0,q=0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = sum(numarator,P[i]*Q[i]);
            p = (float) sqrt(sum(p, (float) pow(P[i],2)));
            q = (float) sqrt(sum(q, (float) pow(Q[i],2)));
            numitor =  p * q;
        }
        if(numitor != 0)
            sCos =  numarator/numitor;
        return sCos;
    }
    public static double kumarHassebrookDistance(float P[],float Q[]){
        float sJac = 10, numarator=0, numitor =0, suma = 0,p=0,q=0,pq=0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = sum(numarator,P[i]*Q[i]);
            p = sum(p, (float) pow(P[i],2));
            q = sum(q, (float) pow(Q[i],2));
            pq = sum(pq, P[i]*Q[i]);
            numitor =  p * q;
        }
        if(numitor != 0)
            sJac = numarator/numitor;
        return sJac;
    }
    public static double similarityJaccard(float P[],float Q[]){
        float sJac = 10, numarator=0, numitor =0, suma = 0,p=0,q=0,pq=0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = sum(numarator,P[i]*Q[i]);
            p = sum(p, (float) pow(P[i],2));
            q = sum(q, (float) pow(Q[i],2));
            pq = sum(pq, P[i]*Q[i]);
            numitor =  p * q;
        }
        if(numitor != 0)
            sJac = numarator/numitor;
        return sJac;
    }
    public static double distanceJaccard(float P[],float Q[]) {
        return 1-similarityJaccard(P,Q);
    }
    public static double similarityDice(float P[],float Q[]){
        float sDice = 10, numarator=0, numitor =0, suma = 0,p=0,q=0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = sum(numarator,P[i]*Q[i]);
            p = sum(p, (float) pow(P[i],2));
            q = sum(q, (float) pow(Q[i],2));
        }
        numarator = 2 * numarator;
        numitor =  p + q;
        if(numitor != 0)
            sDice = numarator/numitor;
        return sDice;
    }
    public static double distanceDice(float P[],float Q[]) {
        return 1-similarityDice(P,Q);
    }
    public static double similarityFidelity(float P[],float Q[]){
        float sFid = 0;
        for(int i = 0; i< P.length ; i++ ) {
            sFid = sum(sFid,((float)sqrt(P[i]*Q[i])));
        }
        return sFid;
    }
    public static double distanceBhattacharyya(float P[],float Q[]){
        float dB = 0;
        for(int i = 0; i< P.length ; i++ ) {
            dB = sum(dB,(float)sqrt(P[i]*Q[i]));
        }
        return -log10(dB);
    }
    public static double distanceHellinger(float P[],float Q[]){
        float dH = 0;int helper=1;
        for(int i = 0; i< P.length ; i++ ) {
            dH = sum(dH,(float)sqrt(P[i]*Q[i]));
        }
        if(1-dH<0)
            helper=-1;

        return 2*helper*(sqrt(abs(1-dH)));
    }
    public static double distanceMatusita(float P[],float Q[]){
        float dM = 0;
        for(int i = 0; i< P.length ; i++ ) {
            dM = sum(dM, (float) pow(((float)sqrt(P[i])-sqrt(Q[i])),2));
        }
        return sqrt(dM);
    }
    public static double distanceSquaredChord(float P[],float Q[]){
        float dSqc = 0;
        for(int i = 0; i< P.length ; i++ ) {
            dSqc = sum(dSqc, (float) pow(((float)sqrt(P[i])-sqrt(Q[i])),2));
        }
        return sqrt(dSqc);
    }
    public static double similaritySquaredChord(float P[],float Q[]){
        return 1-distanceSquaredChord(P,Q);
    }
    public static double distanceSquaredEuclidian(float P[],float Q[]){
        float dSqe = 0;
        for(int i = 0; i< P.length ; i++ ) {
            dSqe = sum(dSqe, (float) pow(P[i]-Q[i],2));
        }
        return sqrt(dSqe);
    }
    public static double distancePearson(float P[],float Q[]){
        float dP = 10, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = (float) pow((P[i] - Q[i]),2);
            numitor =  Q[i];
            if(numitor != 0)
                dP = numarator/numitor;
        }

        return dP;
    }
    public static double distanceNeyman(float P[],float Q[]){
        float dN = 10, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = (float) pow((P[i] - Q[i]),2);
            numitor =  P[i];
            if(numitor != 0)
                dN = numarator/numitor;
        }

        return dN;
    }
    public static double distanceSquared(float P[],float Q[]){
        float dSqChi = 10, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = (float) pow((P[i] - Q[i]),2);
            numitor =  P[i] + Q[i];
            if(numitor != 0)
                dSqChi = numarator/numitor;
        }

        return dSqChi;
    }
    public static double distanceProbabilisticSymmetric(float P[],float Q[]){
        float dSqChi = 10, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = (float) pow((P[i] - Q[i]),2);
            numitor =  P[i] + Q[i];
            if(numitor != 0)
                dSqChi = numarator/numitor;
        }

        return 2*dSqChi;
    }
    public static double distanceDivergence(float P[],float Q[]){
        float dSqChi = 10, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = (float) pow((P[i] - Q[i]),2);
            numitor =  (float) pow((P[i] + Q[i]),2);
            if(numitor != 0)
                dSqChi = numarator/numitor;
        }
        return 2*dSqChi;
    }
    public static double distanceClark(float P[],float Q[]){
        float dSqChi = 10, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = abs(P[i] - Q[i]);
            numitor =  P[i] + Q[i];
            if(numitor != 0)
                dSqChi = sum(dSqChi,(float) pow((numarator/numitor),2));
        }
        return sqrt(dSqChi);
    }
    public static double distanceAdditiceSymmetric(float P[],float Q[]){
        float adChi = 10, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = (float) (pow((P[i] - Q[i]),2)*(P[i]+Q[i]));
            numitor =  P[i] * Q[i];
            if(numitor != 0)
                adChi = sum(adChi,numarator/numitor);
        }
        return sqrt(adChi);
    }
    public static double distanceKullbackLeibler(float P[],float Q[]){
        float dKL = 10, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = P[i];
            numitor =  Q[i];
            if(numitor != 0 && numarator != 0)
                dKL = sum(dKL, (float) (P[i] * log10(numarator/numitor)));
        }
        return dKL;
    }
    public static double distanceJeffreys(float P[],float Q[]){
        float dJ = 10, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = P[i];
            numitor =  Q[i];
            if(numitor != 0 && numarator != 0)
                dJ = sum(dJ, (float) ((P[i] - Q[i]) * log10(numarator/numitor)));
        }
        return dJ;
    }
    public static double distanceKDivergence(float P[],float Q[]){
        float dJ = 10, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = 2*P[i];
            numitor =  P[i] + Q[i];
            if(numitor != 0 && numarator != 0)
                dJ = sum(dJ, (float) (P[i] * log10(numarator/numitor)));
        }
        return dJ;
    }
    public static double distanceTopsoe(float P[],float Q[]){
        float dTop = 10, p=0,qq=0,q=0;
        for(int i = 0; i< P.length ; i++ ) {
            p = 2*P[i];
            qq = 2 * Q[i];
            q =  P[i] + Q[i];
            if(p != 0 && q != 0 && qq !=0)
                dTop = sum(dTop, (float) ((float) (P[i] * log10(p/q)) + Q[i] * log10(qq/q)));
        }
        return dTop;
    }
    public static double distanceJensenShannon(float P[],float Q[]){
        float dJS = 10, p=0,qq=0,q=0;
        for(int i = 0; i< P.length ; i++ ) {

            p = (P[i] == 0 || Q[i] == 0) ? p : sum( p, (float) (P[i] * log10( 2*P[i] / (P[i] + Q[i]))));
            q = (Q[i] == 0 || P[i] == 0) ? q : sum( q, (float) (Q[i] * log10( 2*P[i] / (P[i] + Q[i]))));
        }
        dJS = p+q;
        return ((float)1/2 * dJS);
    }
    public static double distanceJensenDifference(float P[],float Q[]){
        float dJD = 10, p=0,qq=0,q=0;
        for(int i = 0; i< P.length ; i++ ) {
            p = (P[i] == 0 || Q[i] == 0) ? p : (float) (P[i] * log10(P[i]) + Q[i] * log10(Q[i]))/2;
            q = (Q[i] == 0 || P[i] == 0) ? q : (P[i] + Q[i])/2;
            dJD = q !=0 ? sum(dJD, (float) (p-q*log10(q))) : sum(dJD, (float) (p-q));
        }
        return dJD;
    }
    public static double distanceKumarJohnson(float P[],float Q[]){
        float dKJ = 10, p=0,qq=0,q=0;
        for(int i = 0; i< P.length ; i++ ) {
            p = (P[i] == 0 || Q[i] == 0) ? p : (float) pow(pow(P[i],2) - pow(Q[i],2),2);
            q = (Q[i] == 0 || P[i] == 0) ? q : (float) (2* pow((P[i]*Q[i]),3/2));
            dKJ = q != 0 ? sum(dKJ, p/q) : dKJ;
        }
        return dKJ;
    }
    public static double distanceAvg(float P[],float Q[]){
        float dAcc = 10, numarator=0,numitor=0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = sum(numarator,(abs(P[i]-Q[i])) + Float.max(abs(P[i]-Q[i]),i));
            numitor = 2;
            dAcc = sum(numarator,numitor);
        }
        return dAcc;
    }
}
