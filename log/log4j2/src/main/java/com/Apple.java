package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Apple {
    private static final Logger logger = LogManager.getLogger(Apple.class);

    public void logTest1() {
        Fruit fruit= new Fruit();
        fruit.logTest();
        logger.info("apple info !!!");
        logger.error("apple errpr!!!");
    }
}
