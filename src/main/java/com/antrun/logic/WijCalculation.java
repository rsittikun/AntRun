package com.antrun.logic;

/**
 * Created by srattanakana on 5/29/2016 AD.
 */
public class WijCalculation {
    private static double[][] distanceRatio(final double[][] distanceArray){
        double[][] result = ArrayHelper.deepCopy(distanceArray);
        for(int i=0 ; i < distanceArray.length ; i++){
            for(int j=0 ; j < distanceArray[i].length ; j++){
                if(distanceArray[i][j] == 0)
                    result[i][j] = 0d;
                else
                    result[i][j] = 1d/distanceArray[i][j];
            }
        }
        return result;
    }

    private static double[][] distanceRatioPowerBeta(final double[][] distanceArray, final double beta){
        double[][] result = ArrayHelper.deepCopy(distanceRatio(distanceArray));
        for(int i=0 ; i < result.length ; i++){
            for(int j=0 ; j < result[i].length ; j++){
                if(result[i][j] == 0)
                    result[i][j] = 0d;
                else
                    result[i][j] = Math.pow(result[i][j], beta);
            }
        }
        return result;
    }

    public static double[][] getWij(final double[][] pheromonesArray, double alpha,final double[][] distanceArray, final double beta){
        double[][] pheromonesPowerAlpha = PheromonesCalculate.pheromonesPowerAlpha(pheromonesArray, alpha);
        double[][] distanceRatioPowerBeta = distanceRatioPowerBeta(distanceArray, beta);
            double[][] result = ArrayHelper.deepCopy(pheromonesArray);
            for(int i=0 ; i < result.length ; i++){
                for(int j=0 ; j < result[i].length ; j++){
                        result[i][j] = pheromonesPowerAlpha[i][j] * distanceRatioPowerBeta[i][j];
                }
            }
            return result;
    }

/*    public double[][] pheromonesPowerAlpha(final double[][] distanceRatioArray, final double alpha){
        double[][] result = distanceRatioArray.clone();
        for(int i=0 ; i < distanceRatioArray.length ; i++){
            for(int j=0 ; j < distanceRatioArray[i].length ; j++){
                if(distanceRatioArray[i][j] == 0)
                    result[i][j] = 0d;
                else
                    result[i][j] = Math.pow(distanceRatioArray[i][j], alpha);
            }
        }
        return result;
    }*/
}
