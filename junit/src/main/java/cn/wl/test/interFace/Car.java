package cn.wl.test.interFace;

public class Car extends Vehicle {
    private Double length;
    private Double height;
    private Integer wheels;

    public Car(){
    }

    public Car(String color, Double length, Double height){
        super(color);
        this.length = length;
        this.height = height;
    }

    public Integer calWheelNumber() {
        return wheels * 1;
    }

    public Double calVolume() {
        return height * length;
    }
    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getWheels() {
        return wheels;
    }

    public void setWheels(Integer wheels) {
        this.wheels = wheels;
    }

    public Car withLength(Double length) {
        this.length = length;
        return this;
    }

    public Car withHeight(Double height) {
        this.height = height;
        return this;
    }
}
