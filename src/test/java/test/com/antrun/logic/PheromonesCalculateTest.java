package test.com.antrun.logic;

import com.antrun.logic.PheromonesCalculate;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by srattanakana on 5/28/2016 AD.
 */
public class PheromonesCalculateTest {
    @Test
    public void getCumpopListTest(){
        PheromonesCalculate p = new PheromonesCalculate();
        p.initPheromones(5);
        double[][] expected = {
                {0,1,1,1,1},
                {1,0,1,1,1},
                {1,1,0,1,1},
                {1,1,1,0,1},
                {1,1,1,1,0}
                };

        assertArrayEquals(expected, p.initPheromones(5));
    }
    @Test
    public void pheromonesVolatileCalculateTest(){
        PheromonesCalculate p = new PheromonesCalculate();
        p.initPheromones(5);
        double[][] expected = {
                {0,0.98,0.98,0.98,0.98},
                {0.98,0,0.98,0.98,0.98},
                {0.98,0.98,0,0.98,0.98},
                {0.98,0.98,0.98,0,0.98},
                {0.98,0.98,0.98,0.98,0}
        };

        assertArrayEquals(expected, p.pheromonesVolatileCalculate(p.initPheromones(5), 0.02d));
    }
}
