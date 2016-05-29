package com.antrun.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by srattanakana on 5/28/2016 AD.
 */
public class Calculate {
    /***
     * We will keep index value start with 0
     *
     * if need to have index start with 1 variable
     * add "hu_" for prefix - that mean for human read
     * Ex. hu_currentTown
     *
     */

    public void test(){
        int numberOfRoute = 5;
        double[] cumpopList = initCumpopList(numberOfRoute);

    }

    /***
     * Cumpop Calculation
     */
    public double[] initCumpopList(final int numOfRoute){
        double[] result = new double[numOfRoute];
        double curr = 0;
        double step = 1d/numOfRoute;
        for(int i = 0 ; i < numOfRoute ; i++){
            curr = curr + step;
            result[i] = curr;
        }
        return result;
    }

    public double[] getCumppopList(final double[] wTownList,final List<Integer> antRunTownPath){
        double[] result = wTownList.clone();
        double curr = 0;

        double[] currentWTownList = wTownList.clone();

        for (Integer town : antRunTownPath){
            currentWTownList[town] = 0;
        }

        /** java 8 source code (lambda) to find sum of number in list*/
        double sumOfWTownList = Arrays.stream(currentWTownList).sum();

        for(int i = 0 ; i < currentWTownList.length ; i++){
            if(wTownList[i] == 0){
                result[i] = 0;
            }else {
                curr = curr + (currentWTownList[i] / sumOfWTownList);
                result[i] = curr;
            }
        }
        return result;
    }

    public double random(){
        /**
         * return 0-0.999999...
         */
        return Math.random();
    }

    public int getTown(final int numberOfRoute) throws Exception{
        /**
         * Be careful should call random only one time per getTown method
         */
        double randomNumber = random();
        System.out.print("Random Number : "+randomNumber);
        double[] cumppopList = initCumpopList(numberOfRoute);

        for(int i = 0 ; i < cumppopList.length ; i++){
            if(randomNumber <= cumppopList[i]){
                return i;
            }
        }
        /** this is vary rarely case */
        throw new Exception("Town not found!!! [Please make sure 'wTownList' is not Empty]");
    }

    public int getTown(final double[] wTownList,final List<Integer> antRunedTownPath) throws Exception{
        /**
         * Be careful should call random only one time per getTown method
         */
        double randomNumber = random();
        System.out.print("Random Number : "+randomNumber);
        double[] cumppopList = getCumppopList(wTownList, antRunedTownPath);


        for(int i = 0 ; i < cumppopList.length ; i++){
            if(randomNumber <= cumppopList[i]){
                return i;
            }
        }
        /** this is vary rarely case */
        throw new Exception("Town not found!!! [Please make sure 'wTownList' is not Empty]");
   }


}
