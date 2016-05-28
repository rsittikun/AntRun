package com.antrun.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srattanakana on 5/28/2016 AD.
 */
public final class PheromonesCalculate {
	class PairIndex{
		int i,j;
		public PairIndex(final int i,final int j){
			this.i = i;
			this.j = j;
		}
        public int getI() {
            return i;
        }
        public int getJ() {
            return j;
        }
    }
	
    /***
     * For 1st round
     */
    public double[][] initPheromones(int size){
        double[][] result = new double[size][size];
        for(int i = 0 ; i < result.length ; i++){
            for(int j = 0 ; j < result[i].length ; j++){
                if(i==j)
                    result[i][j] = 0;
                else
                    result[i][j] = 1;
            }
        }
        return result;
    }

    /***
     * For Pheromones Volatile after ant run
     * After ant run step 1
     */
    public double[][] pheromonesVolatileCalculate(final double[][] pheromonesArray,double pheromonesVolatile){
        double[][] result = pheromonesArray.clone();

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
    public double[][] pheromonesUpdateCalculate(final double[][] pheromonesArray, List<Integer> listOfTown, double bestDistanceOfLastRun){
        double[][] result = pheromonesArray.clone();
        double extraPheromonesFromLastRun = 1d/bestDistanceOfLastRun;

        for(int i = 0 ; i < pheromonesArray.length ; i++){
            for(int j = 0 ; j < pheromonesArray[i].length ; j++){
                if(i==j)
                    result[i][j] = pheromonesArray[i][j];
                else
                    result[i][j] = pheromonesArray[i][j] + extraPheromonesFromLastRun;
            }
        }
        return result;
    }
    
    public List<PairIndex> gennerate(List<Integer> listOfTown){
    	List<PairIndex> result = new  ArrayList<PairIndex>();
        if(listOfTown.size() < 2){
            return result;
        }else{
            for(int i = 1; i < listOfTown.size() ; i++){
                result.add(new PairIndex(listOfTown.get(i), listOfTown.get(i+1)));
            }
            return result;
        }
    			
    }
}
