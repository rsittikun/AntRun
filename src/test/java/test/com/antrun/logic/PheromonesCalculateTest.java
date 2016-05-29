package test.com.antrun.logic;

import com.antrun.logic.PheromonesCalculate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by srattanakana on 5/28/2016 AD.
 */
public class PheromonesCalculateTest {
    @Test
    public void getCumpopListTest(){
        PheromonesCalculate p = new PheromonesCalculate();
        p.initPheromones(5,1);
        double[][] expected = {
                {0,1,1,1,1},
                {1,0,1,1,1},
                {1,1,0,1,1},
                {1,1,1,0,1},
                {1,1,1,1,0}
                };

        assertArrayEquals(expected, p.initPheromones(5,1));
    }
    @Test
    public void pheromonesVolatileCalculateTest(){
        PheromonesCalculate p = new PheromonesCalculate();
        p.initPheromones(5,1);
        double[][] expected = {
                {0,0.98,0.98,0.98,0.98},
                {0.98,0,0.98,0.98,0.98},
                {0.98,0.98,0,0.98,0.98},
                {0.98,0.98,0.98,0,0.98},
                {0.98,0.98,0.98,0.98,0}
        };

        assertArrayEquals(expected, p.pheromonesVolatileCalculate(p.initPheromones(5,1), 0.02d));
    }

    @Test
    public void pheromonesUpdateCalculateTest(){
        PheromonesCalculate p = new PheromonesCalculate();
        p.initPheromones(5,1);
        List<Integer> hu_towns = new ArrayList(Arrays.asList(3,4,2,5,1));
        List<Integer> towns = hu_towns.stream().map(x-> x-1).collect(Collectors.toList());
        double[][] list_0_98 = p.pheromonesVolatileCalculate(p.initPheromones(5,1), 0.02d);

        double[][] expected = {
                {0,0.98,0.98,0.98,0.98},
                {0.98,0,0.98,0.98,1.012258064516129},
                {0.98,0.98,0,1.012258064516129,0.98},
                {0.98,1.012258064516129,0.98,0,0.98},
                {1.012258064516129,0.98,0.98,0.98,0}
        };

        assertArrayEquals(expected, p.pheromonesUpdateCalculate(list_0_98, towns, 31));
    }
}
