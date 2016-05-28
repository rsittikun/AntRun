package test.com.antrun.logic;

import com.antrun.logic.Calculate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by srattanakana on 5/28/2016 AD.
 */
public class calculateTest {
    @Test
    public void getCumpopListTest(){
        List<Double> wTownList = new ArrayList<Double>();
        wTownList.add(0.0000013200);
        wTownList.add(0.00000402);
        wTownList.add(0d);
        wTownList.add(0.00000186);
        wTownList.add(0.00000320);


        Calculate c = new Calculate();
        List<Double> result = c.getCumppopList(wTownList);
        assertEquals(Double.valueOf(0.12692307692307694), result.get(0));
        assertEquals(Double.valueOf(0.5134615384615385), result.get(1));
        assertEquals(Double.valueOf(0.0), result.get(2));
        assertEquals(Double.valueOf(0.6923076923076924), result.get(3));
        assertEquals(Double.valueOf(1.0), result.get(4));
    }

    @Test
    public void getTownTest() throws Exception{
        Calculate c = mock(Calculate.class);
        when(c.getTown(anyList())).thenCallRealMethod();
        when(c.getCumppopList(anyList())).thenCallRealMethod();
        when(c.random()).thenReturn(0.678d);

        List<Double> wTownList = new ArrayList<Double>();
        wTownList.add(0.0000013200);
        wTownList.add(0.00000402);
        wTownList.add(0d);
        wTownList.add(0.00000186);
        wTownList.add(0.00000320);

        int result = c.getTown(wTownList);
        int hu_result = result + 1;
        assertEquals(3, result);
        assertEquals(4, hu_result);
    }

}
