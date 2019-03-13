package cn.wl.test;

//import org.junit.*;
import cn.wl.test.Calculator;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by zjjwu on 2017/3/17.
 */
public class CalculatorTest {
    private static Calculator mcalculater = new Calculator();
    @Before
    public void setUp() throws Exception {
        mcalculater.clear();
    }

    @Test
    public void add() throws Exception {
        mcalculater.add(10);
        mcalculater.add(20);
        assertEquals(30,mcalculater.getResult());
    }

    @Test(expected = ArithmeticException.class)
    public void subtract() throws Exception {
        mcalculater.add(10);
        mcalculater.subtract(0);
        assertEquals(8,mcalculater.getResult());
    }

    @Ignore//("multiply()Not yet implemented")
    public void multiply() throws Exception {

    }

    @Test
    public void divide() throws Exception {
        mcalculater.add(8);
        mcalculater.divide(0);
        assertEquals(4,mcalculater.getResult());
    }

    @Test(timeout = 1000)
    public void squareRoot() throws Exception{
        mcalculater.squareRoot(4);
        assertEquals(2,mcalculater.getResult());
    }

}