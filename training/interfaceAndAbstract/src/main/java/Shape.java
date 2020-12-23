public abstract class Shape {

    private String color;

    public Shape(){
        System.out.println("this is the construct of the Shape abstract class");
    }

    public Shape(String color){
        System.out.println("this is the construct of the Shape abstract class for set color");
        this.color = color;
    }

    public abstract double callPerimeter();

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
