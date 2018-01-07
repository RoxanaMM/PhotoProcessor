import static java.lang.Math.*;
import static java.lang.Float.*;
import static java.lang.Math.log10;

/**
 * Created by Roxana on 12/25/2017.
 */
public class Algorithms {

    public float euclidianL2(float P[],float Q[]){
        float dEuc = 0;
        for(int i=0;i<P.length;i++){
            dEuc= (float) sqrt(sum(dEuc, (float) pow(abs(P[i]-Q[i]),2)));
        }
        return dEuc;
    }
    public double cityBlockL1(float P[],float Q[]){
        float dCB = 0;
        for(int i=0;i<P.length;i++){
            dCB=sum(dCB,abs(P[i]) - abs(Q[i]));
        }
        return dCB;
    }

    public double minkowskiLp(float P[],float Q[], int p){
        float dMk = 0;
        for(int i=0;i<P.length;i++){
            dMk= (float) sqrt(sum(dMk, (float) pow(abs(P[i]-Q[i]),2)));
        }
        return dMk;//NOK because of the square root
    }


    public double cebyshevLinf(float P[],float Q[]){
        float dChev = 0, dChevMax= dChev;
        for(int i=0;i<P.length;i++){
            dChev= (float) sqrt(sum(dChev, (float) pow(abs(P[i]-Q[i]),2)));
            if ( dChev > dChevMax)
                dChevMax = dChev;
        }
        return dChevMax;
    }

    public double sorensen(float P[],float Q[]){
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

    public double gower(float P[],float Q[], float R[]){
        float dGow = 0, numarator=0, numitor =0;
        for(int i=0;i<P.length;i++){
            numarator =(float) sum(numarator, (float) (abs(P[i]-Q[i])));
            numitor= R[i];
        }
        if(numitor != 0){
            dGow = (1/P.length) * (numarator/numitor);
        }
        return dGow;
    }

    public double soergel(float P[],float Q[]){
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

    public double kulczynskid(float P[],float Q[]){
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


    public double canberra(float P[],float Q[]){
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

    public double lorentzian(float P[],float Q[]){
        float dLor= 0;
        for(int i=0;i<P.length;i++) {
            dLor = (float) sum(dLor, (float) Math.log10(1+abs(P[i] - Q[i])));
        }
        return dLor;
    }

    public double intersection(float P[],float Q[]){
        float sIS= 0;
        for(int i=0;i<P.length;i++) {
            sIS = (float) sum(sIS, Float.min(P[i],Q[i]));
        }
        return sIS;
    }

    public double waveHedges(float P[],float Q[]){
        float dWH = 0, numarator=0, numitor =0, suma = 0;
        for(int i=0;i<P.length;i++) {
            numarator = Float.min(P[i],Q[i]);
            numitor = Float.max(P[i],Q[i]);
            if(numitor != 0)
                dWH = sum(dWH, 1-(numarator/numitor));
        }
        return dWH;
    }

    public double similarityCzekanowski(float P[],float Q[]){
        float sCze = 0, numarator=0, numitor =0, suma = 0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = 2* sum(numarator , Float.min(P[i],Q[i]));
            numitor = sum(numitor,P[i]+Q[i]);
            if(numitor != 0)
                sCze = numarator/numitor;
        }
        return sCze;
    }

    public double distanceCzekanowski(float P[],float Q[]) {
        return 1-similarityCzekanowski(P,Q);
    }

    public double similarityMotyka(float P[],float Q[]){
        float sMot = 0, numarator=0, numitor =0, suma = 0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = sum(numarator , Float.min(P[i],Q[i]));
            numitor = sum(numitor,P[i]+Q[i]);
        }
        if(numitor != 0)
            sMot = numarator/numitor;
        return sMot;
    }

    public double distanceMotyka(float P[],float Q[]) {
        return 1-similarityMotyka(P,Q);
    }

    public double similarityKulczynkyS(float P[],float Q[]){
        float sKul = 0, numarator=0, numitor =0, suma = 0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = sum(numarator , Float.min(P[i],Q[i]));
            numitor = sum(numitor,abs(P[i]-Q[i]));
        }
        if(numitor != 0)
            sKul = numarator/numitor;
        return sKul;
    }

    public double distanceKulczynkyS(float P[],float Q[]) {
        return 1/similarityKulczynkyS(P,Q);
    }

    public double ruzicka(float P[],float Q[]){
        float sRuz = 0, numarator=0, numitor =0, suma = 0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = sum(numarator , Float.min(P[i],Q[i]));
            numitor = sum(numitor,Float.max(P[i],Q[i]));
        }
        if(numitor != 0)
            sRuz = numarator/numitor;
        return sRuz;
    }

    public double tanimoto(float P[],float Q[]){
        float sRuz = 0, numarator=0, numitor =0, suma = 0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = sum(numarator , (Float.max(P[i],Q[i]) - Float.min(P[i],Q[i])));
            numitor = sum(numitor,Float.max(P[i],Q[i]));
        }
        if(numitor != 0)
            sRuz = numarator/numitor;
        return sRuz;
    }

    public double innerProduct(float P[],float Q[]){
        float sIP = 0;
        for(int i = 0; i< P.length ; i++ ) {
            sIP = sum(sIP , P[i]*Q[i]);
        }
        return sIP;
    }

    public double harmonicMean(float P[],float Q[]){
        float sHM = 0, numarator=0, numitor =0, suma = 0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = (P[i]*Q[i]);
            numitor =  (P[i] + Q[i]);
        }
        if(numitor != 0)
            sHM = numarator/numitor;
        return sHM;
    }

    public double cosine(float P[],float Q[]){
        float sCos = 0, numarator=0, numitor =0, suma = 0,p=0,q=0;
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

    public double kumarHassebrook(float P[],float Q[]){
        float sJac = 0, numarator=0, numitor =0, suma = 0,p=0,q=0,pq=0;
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

    public double similarityJaccard(float P[],float Q[]){
        float sJac = 0, numarator=0, numitor =0, suma = 0,p=0,q=0,pq=0;
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

    public double distanceJaccard(float P[],float Q[]) {
        return 1-similarityJaccard(P,Q);
    }

    public double similarityDice(float P[],float Q[]){
        float sDice = 0, numarator=0, numitor =0, suma = 0,p=0,q=0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = 2 * sum(numarator,P[i]*Q[i]);
            p = sum(p, (float) pow(P[i],2));
            q = sum(q, (float) pow(Q[i],2));
            numitor =  p + q;
        }
        if(numitor != 0)
            sDice = numarator/numitor;
        return sDice;
    }

    public double distanceDice(float P[],float Q[]) {
        return 1-similarityDice(P,Q);
    }

    public double similarityFidelity(float P[],float Q[]){
        float sFid = 0;
        for(int i = 0; i< P.length ; i++ ) {
            sFid = sum(sFid,(float)sqrt(P[i]*Q[i]));
        }
        return sFid;
    }

    public double distanceBhattacharyya(float P[],float Q[]){
        float dB = 0;
        for(int i = 0; i< P.length ; i++ ) {
            dB = sum(dB,(float)sqrt(P[i]*Q[i]));
        }
        return -log10(dB);
    }

    public double distanceHellinger(float P[],float Q[]){
        float dH = 0;
        for(int i = 0; i< P.length ; i++ ) {
            dH = sum(dH,(float)sqrt(P[i]*Q[i]));
        }
        return 2*sqrt(1-dH);
    }

    public double distanceMatusita(float P[],float Q[]){
        float dM = 0;
        for(int i = 0; i< P.length ; i++ ) {
            dM = sum(dM, (float) pow(((float)sqrt(P[i])-sqrt(Q[i])),2));
        }
        return sqrt(dM);
    }

    public double distanceSquaredChord(float P[],float Q[]){
        float dSqc = 0;
        for(int i = 0; i< P.length ; i++ ) {
            dSqc = sum(dSqc, (float) pow(((float)sqrt(P[i])-sqrt(Q[i])),2));
        }
        return sqrt(dSqc);
    }

    public double similaritySquaredChord(float P[],float Q[]){
        return 1-distanceSquaredChord(P,Q);
    }

    public double distanceSquaredEuclidian(float P[],float Q[]){
        float dSqe = 0;
        for(int i = 0; i< P.length ; i++ ) {
            dSqe = sum(dSqe, (float) pow(P[i]-Q[i],2));
        }
        return sqrt(dSqe);
    }

    public double distancePearson(float P[],float Q[]){
        float dP = 0, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = (float) pow((P[i] - Q[i]),2);
            numitor =  Q[i];
            if(numitor != 0)
                dP = numarator/numitor;
        }

        return dP;
    }

    public double distanceNeyman(float P[],float Q[]){
        float dN = 0, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = (float) pow((P[i] - Q[i]),2);
            numitor =  P[i];
            if(numitor != 0)
                dN = numarator/numitor;
        }

        return dN;
    }

    public double distanceSquared(float P[],float Q[]){
        float dSqChi = 0, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = (float) pow((P[i] - Q[i]),2);
            numitor =  P[i] + Q[i];
            if(numitor != 0)
                dSqChi = numarator/numitor;
        }

        return dSqChi;
    }

    public double distanceProbabilisticSymmetric(float P[],float Q[]){
        float dSqChi = 0, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = (float) pow((P[i] - Q[i]),2);
            numitor =  P[i] + Q[i];
            if(numitor != 0)
                dSqChi = numarator/numitor;
        }

        return 2*dSqChi;
    }

    public double distanceDivergence(float P[],float Q[]){
        float dSqChi = 0, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = (float) pow((P[i] - Q[i]),2);
            numitor =  (float) pow((P[i] + Q[i]),2);
            if(numitor != 0)
                dSqChi = numarator/numitor;
        }
        return 2*dSqChi;
    }

    public double distanceClark(float P[],float Q[]){
        float dSqChi = 0, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = abs(P[i] - Q[i]);
            numitor =  P[i] + Q[i];
            if(numitor != 0)
                dSqChi = sum(dSqChi,(float) pow((numarator/numitor),2));
        }
        return sqrt(dSqChi);
    }

    public double distanceAdditiceSymmetric(float P[],float Q[]){
        float adChi = 0, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = (float) (pow((P[i] - Q[i]),2)*(P[i]+Q[i]));
            numitor =  P[i] * Q[i];
            if(numitor != 0)
                adChi = sum(adChi,numarator/numitor);
        }
        return sqrt(adChi);
    }

    public double distanceKullbackLeibler(float P[],float Q[]){
        float dKL = 0, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = P[i];
            numitor =  Q[i];
            if(numitor != 0)
                dKL = sum(dKL, (float) (P[i] * log10(numarator/numitor)));
        }
        return dKL;
    }

    public double distanceJeffreys(float P[],float Q[]){
        float dJ = 0, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = P[i];
            numitor =  Q[i];
            if(numitor != 0)
                dJ = sum(dJ, (float) ((P[i] - Q[i]) * log10(numarator/numitor)));
        }
        return dJ;
    }

    public double distanceKDivergence(float P[],float Q[]){
        float dJ = 0, numarator=0, numitor =0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = 2*P[i];
            numitor =  P[i] + Q[i];
            if(numitor != 0)
                dJ = sum(dJ, (float) (P[i] * log10(numarator/numitor)));
        }
        return dJ;
    }

    public double distanceTopsoe(float P[],float Q[]){
        float dTop = 0, p=0,qq=0,q=0;
        for(int i = 0; i< P.length ; i++ ) {
            p = 2*P[i];
            qq = 2 * Q[i];
            q =  P[i] + Q[i];
            dTop = sum(dTop, (float) ((float) (P[i] * log10(p/q)) + Q[i] * log10(qq/q)));
        }
        return dTop;
    }

    public double distanceJensenShannon(float P[],float Q[]){
        float dJS = 0, p=0,qq=0,q=0;
        for(int i = 0; i< P.length ; i++ ) {
            p = sum( p, (float) (P[i] * log10( 2*P[i] / (P[i] + Q[i]))));
            q = sum( q, (float) (Q[i] * log10( 2*P[i] / (P[i] + Q[i]))));
            dJS = p+q;
        }
        return 1/2 * dJS;
    }

    public double distanceJensenDifference(float P[],float Q[]){
        float dJD = 0, p=0,qq=0,q=0;
        for(int i = 0; i< P.length ; i++ ) {
            p = (float) (P[i] * log10(P[i]) + Q[i] * log10(Q[i]))/2;
            q = (P[i] + Q[i])/2;
            dJD = sum(dJD, (float) (p-q*log10(q)));
        }
        return dJD;
    }

    public double distanceKumarJohnson(float P[],float Q[]){
        float dKJ = 0, p=0,qq=0,q=0;
        for(int i = 0; i< P.length ; i++ ) {
            p = (float) pow(pow(P[i],2) - pow(Q[i],2),2);
            q = (float) (2* pow((P[i]*Q[i]),3/2));
            dKJ = sum(dKJ, p/q);
        }
        return dKJ;
    }

    public double distanceAvg(float P[],float Q[]){
        float dAcc = 0, numarator=0,numitor=0;
        for(int i = 0; i< P.length ; i++ ) {
            numarator = sum(numarator,(abs(P[i]-Q[i])) + Float.max(abs(P[i]-Q[i]),i));
            numitor = 2;
            dAcc = sum(numarator,numitor);
        }
        return dAcc;
    }
}
