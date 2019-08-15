package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Fruit {
    private String color;
    private String age;
    private static final Logger logger = LogManager.getLogger(Fruit.class);

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void logTest() {
        logger.info("info!!!");
        logger.error("error!!!");
    }
}
