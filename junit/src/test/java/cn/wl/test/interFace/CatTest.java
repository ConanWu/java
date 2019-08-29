package cn.wl.test.interFace;

import org.junit.Test;

public class CatTest extends Car{

    @Test
    public void abstractTest() {
        Vehicle car = new Car("Red", 2.0, 4.0);
        logger.info(car.getColor());
        logger.info(car.calVolume().toString());

    }
}
