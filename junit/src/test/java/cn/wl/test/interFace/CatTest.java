package cn.wl.test.interFace;

import org.junit.Test;

public class CatTest extends Car{

    @Test
    public void abstractTest() {
        Vehicle car = new Car("Red", 2.0, 4.0);
        logger.info(car.getColor());
        logger.info(car.calVolume().toString());

    }

    @Test
    public void abstractTest1() {
        Shape s1 = new Triangle("red", 2, 3, 4);
        logger.info(s1.getType());
        logger.info(String.valueOf(s1.calPerimeter()));
    }

    @Test
    public void interfaceTest() {
        logger.info(String.valueOf(Output.MAX_CACHE_LINE));
        logger.info(String.valueOf(Output.PROP));
        logger.info(String.valueOf(Output.PROP_B));
    }

    @Test
    public void interfaceInheritTest() {
        Output o = new Printer();
        o.getData("hello java world!");
        o.getData("hello crazy java!");
        o.out();
        o.getData("hello world!");
        o.getData("hello earth!");
        o.out();
    }


}
