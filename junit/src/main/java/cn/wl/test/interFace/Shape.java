package cn.wl.test.interFace;

public abstract class Shape {
    {
        System.out.println("implement shape's initial stack");
    }

    public Shape() {

    }

    public Shape(String color) {
        System.out.println("implement shape constructor");
        this.color = color;
    }

    private String color;

    public abstract double calPerimeter();

    public abstract String getType();

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
