public class Rectangle extends Shape {
    protected double width;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    protected double length;

    public Rectangle() {
        this.width = 1.0;
        this.length = 1.0;
    }

    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    public Rectangle(double width, double length, String color, Boolean filled) {
        super(color, filled);
        this.width = width;
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    double getArea() {
        return this.length * this.width;
    }

    @Override
    double getPerimeter() {
        return 2 * (this.length + this.width);
    }

    @Override
    public String toString() {
        return String.format("Rectangle[%s,width=%.2f, length=%.2f]", super.toString(), this.width, this.length);
    }

}
