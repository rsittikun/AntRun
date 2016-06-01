package com.antrun.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srattanakana on 5/28/2016 AD.
 */
public final class PheromonesCalculate {
	class PairIndex{
		public final int i,j;
		public PairIndex(final int i,final int j){
			this.i = i;
			this.j = j;
		}
    }
	
    /***
     * For 1st round
     */
    public double[][] initPheromones(final int size, final double initPheromones){
        double[][] result = new double[size][size];
        for(int i = 0 ; i < result.length ; i++){
            for(int j = 0 ; j < result[i].length ; j++){
                if(i==j)
                    result[i][j] = 0d;
                else
                    result[i][j] = initPheromones;
            }
        }
        return result;
    }

    /***
     * For Pheromones Volatile after ant run
     * After ant run step 1
     */
    public double[][] pheromonesVolatileCalculate(final double[][] pheromonesArray,final double pheromonesVolatile){
        double[][] result = ArrayHelper.deepCopy(pheromonesArray);

        for(int i = 0 ; i < pheromonesArray.length ; i++){
            for(int j = 0 ; j < pheromonesArray[i].length ; j++){
                if(i==j)
                    result[i][j] = pheromonesArray[i][j];
                else
                    result[i][j] = pheromonesArray[i][j] - pheromonesVolatile;
            }
        }
        return result;
    }
    /***
     * For Pheromones Volatile after ant run
     * After ant run step 2
     */
    public double[][] pheromonesUpdateCalculate(final double[][] pheromonesArray, final List<Integer> listOfTown,final double bestDistanceOfLastRun){
        double[][] result = ArrayHelper.deepCopy(pheromonesArray);
        double extraPheromonesFromLastRun = 1d/bestDistanceOfLastRun;
        List<PairIndex> pairIndexList = genneratePairIndex(listOfTown);

        for(PairIndex p: pairIndexList){
                    result[p.i][p.j] = pheromonesArray[p.i][p.j] + extraPheromonesFromLastRun;
        }
        return result;
    }

    public static double[][] pheromonesPowerAlpha(final double[][] pheromonesArray, double alpha){
        double[][] result = ArrayHelper.deepCopy(pheromonesArray);

        for(int i = 0 ; i < pheromonesArray.length ; i++){
            for(int j = 0 ; j < pheromonesArray[i].length ; j++){
                    result[i][j] = Math.pow(pheromonesArray[i][j], alpha);
            }
        }
        return result;
    }
    
    public List<PairIndex> genneratePairIndex(final List<Integer> listOfTown){
    	List<PairIndex> result = new  ArrayList<PairIndex>();
        if(listOfTown.size() < 2){
            return result;
        }else{
            for(int i = 1; i < listOfTown.size() ; i++){
                result.add(new PairIndex(listOfTown.get(i-1), listOfTown.get(i)));
            }
            return result;
        }

    }


    public double runDistance(final double[][] distanceArray,final List<Integer> listOfTown){
        final List<PairIndex> pairIndexList = genneratePairIndex(listOfTown);
        double runDistance = 0d;
        for(PairIndex pairIndex :pairIndexList){
            runDistance += distanceArray[pairIndex.i][pairIndex.j];
        }
        return runDistance;
    }

    public String runPath(final List<Integer> listOfTown){
        StringBuilder path = new StringBuilder();
        for(int i = 0 ; i < listOfTown.size() ; i++){
            if(i==0){
                path.append(listOfTown.get(i));
            }else{
                path.append(" -> "+listOfTown.get(i));
            }
        }

        return path.toString();
    }
}
