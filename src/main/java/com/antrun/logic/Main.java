package com.antrun.logic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by srattanakana on 5/28/2016 AD.
 */
public class Main {
    public static void main(String args[]) throws Exception{
        final int numberOfRoute = 5;
        final int numberOfLoop = 10;
        final int numberOfAnt = 3;
        final double initPheromones = 1d;
        final double volatileRate = 0.02d;
        final double alpha = 5d;
        final double beta = 5d;

        //TODO: Get from Excel file
        final double [][] distanceArray = {
                { 0,10,15,8,4 },
                { 10,0,12,5,8 },
                { 15,12,0,14,20 },
                { 8,5,14,0,15 },
                { 4,8,20,15,0 }
        };

        HashMap<String,Integer> pp = new HashMap<String,Integer>();

        PheromonesCalculate p = new PheromonesCalculate();
        double[][] pheromonesList = p.pheromonesPowerAlpha(p.initPheromones(numberOfRoute, initPheromones), alpha);

        double [][] wIJ = WijCalculation.getWij(pheromonesList, alpha, distanceArray, beta);

        PreromonesExporter preromonesExporter = new PreromonesExporter(System.getProperty("user.dir")+"/test.xls");
        for (int l=0 ;l < numberOfLoop ; l++) {
            preromonesExporter.printString("==========preromones list round["+(l+1)+"]==========");
            preromonesExporter.printArray(pheromonesList);
            preromonesExporter.printLint();

            //System.out.println(Arrays.deepToString(pheromonesList));

            Calculate c = new Calculate();
            //TODO : cal wIJ;

            List<Integer> antRunedTownPath = new ArrayList<>();
            int town = c.getTown(numberOfRoute);
            antRunedTownPath.add(town);
            do {
                town = c.getTown(wIJ[town], antRunedTownPath);
                //TODO : cal wIJ
                antRunedTownPath.add(town);

            } while (antRunedTownPath.size() < numberOfRoute);

            String ss = "";
            //System.out.println("");
            for (Integer i : antRunedTownPath) {
                int hu_i = i + 1;
                //System.out.print(hu_i + " -> ");
                ss += hu_i + " -> ";
            }
            ss += "Distance => "+p.runDistance(distanceArray, antRunedTownPath);


            pp.put(ss, pp.getOrDefault(ss,0)+1);
            System.out.println(ss);
            //System.out.print("Distance => "+p.runDistance(distanceArray, antRunedTownPath));

            //TODO: Make sure update Pheromones and wIJ only one time
            pheromonesList = p.pheromonesVolatileCalculate(pheromonesList, volatileRate);
            pheromonesList = p.pheromonesUpdateCalculate(pheromonesList, antRunedTownPath ,p.runDistance(distanceArray, antRunedTownPath));
            pheromonesList = p.pheromonesPowerAlpha(pheromonesList, alpha);
            //update wIJ
            wIJ = WijCalculation.getWij(pheromonesList, alpha, distanceArray, beta);
        }
        preromonesExporter.dowrite();

        /*for(String key : pp.keySet()){
            System.out.println(key+"  count :  "+pp.get(key));
        }*/

    }

}
