package com.antrun.logic;

import java.util.Arrays;

/**
 * Created by srattanakana on 5/29/2016 AD.
 */
public class ArrayHelper {
    public static double[][] deepCopy(double[][] input) {
        double[][] target = new double[input.length][];
        for (int i=0; i <input.length; i++) {
            target[i] = Arrays.copyOf(input[i], input[i].length);
        }
        return target;
    }
    public static double[] deepCopy(double[] input) {
        double[] target = Arrays.copyOf(input, input.length);
        return target;
    }
}
