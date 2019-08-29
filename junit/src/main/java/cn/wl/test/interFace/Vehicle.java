package cn.wl.test.interFace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class Vehicle {
    Logger logger = LoggerFactory.getLogger(Vehicle.class);
    private String color;

    public abstract Integer calWheelNumber();

    public abstract Double calVolume();

    public Vehicle() {
        logger.info("Vehichle class constructor function!" );
    }

    public Vehicle(String color) {
        logger.info("Vehichle class constructor function with one parameter!" );
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
