package com;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Apple {
    private static final Logger logger = LoggerFactory.getLogger(Apple.class);

    public void logTest1() {
        Fruit fruit= new Fruit();
        fruit.logTest();
        logger.info("apple info !!!");
        logger.error("apple errpr!!!");
    }
}
