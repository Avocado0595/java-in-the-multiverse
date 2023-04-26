import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        List<Shape> s = new ArrayList<Shape>() {
            {
                add(new Circle());
                add(new Rectangle(1, 2, "blue", false));
                add(new Square(5, "violet", true));
            }
        };

        for (Shape i : s) {
            System.out.println(i);
        }
    }
}
