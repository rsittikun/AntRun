package com.antrun.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by srattanakana on 5/28/2016 AD.
 */
public class Main {
    public static void main(String args[]) throws Exception{
        final int numberOfRoute = 5;
        final int numberOfLoop = 2;
        final int numberOfAnt = 3;
        final double initPheromones = 1d;
        final double volatileRate = 0.2d;
        final double alpha = 5d;
        final double beta = 5d;

        //TODO: Get from real
        double [][] wIJ = {
            {0.0000000000,0.0000100000,0.0000013200,0.0000305200,0.0009765700},
            {0.0000100000,0.0000000000,0.0000040200,0.0003200000,0.0000305200},
            {0.0000013200,0.0000040200,0.0000000000,0.0000018600,0.0000003200},
            {0.0000305200,0.0003200000,0.0000018600,0.0000000000,0.0000013200},
            {0.0009765700,0.0000305200,0.0000003200,0.0000013200,0.0000000000}
        };




        Calculate c = new Calculate();
        PheromonesCalculate p = new PheromonesCalculate();
        double [][] pheromonesList = p.pheromonesPowerAlpha(p.initPheromones(numberOfRoute, initPheromones), alpha);
        //TODO : cal wIJ;


        List<Integer> antRunedTownPath = new ArrayList<>();
        int town = c.getTown(numberOfRoute);
        antRunedTownPath.add(town);
        do{
            town = c.getTown(wIJ[town], antRunedTownPath);
            //TODO : cal wIJ
            antRunedTownPath.add(town);

        }while (antRunedTownPath.size() < numberOfRoute);

        System.out.println("");
        for(Integer i : antRunedTownPath) {
            int hu_i = i+1;
            System.out.print(hu_i+" -> ");
        }

    }

}
