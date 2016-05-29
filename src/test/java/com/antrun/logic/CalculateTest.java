package com.antrun.logic;

import com.antrun.logic.Calculate;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by srattanakana on 5/28/2016 AD.
 */
public class CalculateTest {
    @Test
    public void getCumpopListTest(){
        double[] wTownList = new double[5];
        wTownList[0] = 0.0000013200;
        wTownList[1] = 0.00000402;
        wTownList[2] = 0d;
        wTownList[3] = 0.00000186;
        wTownList[4] = 0.00000320;


        Calculate c = new Calculate();
        double[] result = c.getCumppopList(wTownList, new ArrayList<>());
        Assert.assertEquals(0.12692307692307694, result[0]);
        Assert.assertEquals(0.5134615384615385, result[1]);
        Assert.assertEquals(0.0, result[2]);
        Assert.assertEquals(0.6923076923076924, result[3]);
        Assert.assertEquals(1.0, result[4]);
    }

    @Test
    public void getTownTest() throws Exception{
        Calculate c = mock(Calculate.class);
        when(c.getTown(any(),anyList())).thenCallRealMethod();
        when(c.getCumppopList(any(), anyList())).thenCallRealMethod();
        when(c.random()).thenReturn(0.678d);

        double[] wTownList = new double[5];
        wTownList[0] = 0.0000013200;
        wTownList[1] = 0.00000402;
        wTownList[2] = 0d;
        wTownList[3] = 0.00000186;
        wTownList[4] = 0.00000320;

        int result = c.getTown(wTownList, new ArrayList<>());
        int hu_result = result + 1;
        Assert.assertEquals(3, result);
        Assert.assertEquals(4, hu_result);
    }

}
