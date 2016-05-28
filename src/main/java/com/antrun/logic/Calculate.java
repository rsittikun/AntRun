package com.antrun.logic;

import java.util.ArrayList;
import java.util.List;
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
        List<Double> cumpopList = initCumpopList(numberOfRoute);

    }

    /***
     * Cumpop Calculation
     */
    public List<Double> initCumpopList(final int numOfRoute){
        List<Double> result = new ArrayList<Double>();
        double curr = 0;
        double step = 1/numOfRoute;
        for(int i = 0 ; i < numOfRoute ; i++){
            curr = curr + step;
            result.add(curr);
        }
        return result;
    }

    public List<Double> getCumppopList(final List<Double> wTownList){
        List<Double> result = new ArrayList<Double>();
        double curr = 0;
        /** java 8 source code (lambda) to find sum of number in list*/
        double sumOfWTownList = wTownList.stream().mapToDouble(Double::doubleValue).sum();

        for(int i = 0 ; i < wTownList.size() ; i++){
            if(wTownList.get(i) == 0){
                result.add(0d);
            }else {
                curr = curr + (wTownList.get(i) / sumOfWTownList);
                result.add(curr);
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

    public int getTown(final List<Double> wTownList) throws Exception{
        /**
         * Be careful should call random only one time per getTown method
         */
        double randomNumber = random();
        List<Double> cumppopList = getCumppopList(wTownList);
/*        for(int i = (cumppopList.size() - 1) ; i > 0 ; i--){
            if(randomNumber <= cumppopList.get(i)){
                return i;
            }
        }*/
        for(int i = 0 ; i < cumppopList.size() ; i++){
            if(randomNumber <= cumppopList.get(i)){
                return i;
            }
        }
        /** this is vary rarely case */
        throw new Exception("Town not found!!! [Please make sure 'wTownList' is not Empty]");
   }


}
