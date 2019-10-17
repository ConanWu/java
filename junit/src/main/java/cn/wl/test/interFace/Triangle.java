package cn.wl.test.interFace;

public class Triangle extends Shape{

    private double a;
    private double b;
    private double c;
    public Triangle(String color, double a, double b, double c) {
        super(color);
        this.setSides(a, b, c);

    }

    public void setSides(double a, double b, double c) {
        if (a + b <= c || a + c <= b || b + c <= a) {
            System.out.println("these three lines cannot get a triangle ");
            return;
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calPerimeter() {
        return a + b + c;
    }

    @Override
    public String getType() {
        return "triangle";
    }

}
