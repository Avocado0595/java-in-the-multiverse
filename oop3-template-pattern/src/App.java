import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        System.out.println("Beverage program.");
        List<BeverageMaker> beverage = new ArrayList<>() {
            {
                add(new CoffeeMaker());
                add(new TeaMaker());
            }
        };
        for (BeverageMaker bv : beverage) {
            bv.makeBeverage();
        }

    }
}
