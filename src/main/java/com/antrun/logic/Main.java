/*
package com.antrun.logic;

import java.util.*;
import java.util.stream.Collectors;

*/
/**
 * Created by srattanakana on 5/28/2016 AD.
 *//*

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

        preromonesExporter.printStrings(PreromonesExporter.SHEET_PATH, "Ant No.", "Round", "Route", "Distance");

        for (int l=0 ;l < numberOfLoop ; l++) {
            preromonesExporter.printString(PreromonesExporter.SHEET_PHERO, "==========preromones list round["+(l+1)+"]==========");
            preromonesExporter.printArray(PreromonesExporter.SHEET_PHERO, pheromonesList);
            preromonesExporter.printLint(PreromonesExporter.SHEET_PHERO );

            preromonesExporter.printString(PreromonesExporter.SHEET_WIJ, "==========Wij list round["+(l+1)+"]==========");
            preromonesExporter.printArray(PreromonesExporter.SHEET_WIJ, pheromonesList);
            preromonesExporter.printLint(PreromonesExporter.SHEET_WIJ );

            //System.out.println(Arrays.deepToString(pheromonesList));

            Calculate c = new Calculate();
            //TODO : cal wIJ;

            List<Integer> bestAntRunedTownPath = new ArrayList<>();
            double bestPath = Double.MAX_VALUE;
            for(int a = 0 ; a < numberOfAnt ; a++) {
                List<Integer> antRunedTownPath  = new ArrayList<>();
                int town = c.getTown(numberOfRoute);
                antRunedTownPath.add(town);
                do {
                    town = c.getTown(wIJ[town], antRunedTownPath);
                    //TODO : cal wIJ
                    antRunedTownPath.add(town);

                } while (antRunedTownPath.size() < numberOfRoute);
                double currDistance = p.runDistance(distanceArray, antRunedTownPath);
                if(currDistance < bestPath){
                    bestPath = currDistance;
                    bestAntRunedTownPath = antRunedTownPath;
                }
                String hu_antNo = String.valueOf(a+1);
                String hu_roundNo = String.valueOf(l+1);
                preromonesExporter.printStrings(PreromonesExporter.SHEET_PATH, hu_antNo, hu_roundNo, p.runPath(antRunedTownPath), String.valueOf(p.runDistance(distanceArray, antRunedTownPath)));
                //preromonesExporter.printLint(PreromonesExporter.SHEET_PATH );
            }


            //TODO: Make sure update Pheromones and wIJ only one time
            pheromonesList = p.pheromonesVolatileCalculate(pheromonesList, volatileRate);
            pheromonesList = p.pheromonesUpdateCalculate(pheromonesList, bestAntRunedTownPath ,p.runDistance(distanceArray, bestAntRunedTownPath));
            //update wIJ
            wIJ = WijCalculation.getWij(p.pheromonesPowerAlpha(pheromonesList, alpha), alpha, distanceArray, beta);
        }
        preromonesExporter.dowrite();

        */
/*for(String key : pp.keySet()){
            System.out.println(key+"  count :  "+pp.get(key));
        }*//*

        System.out.println("===== Press enter to exit =====");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

}
*/
