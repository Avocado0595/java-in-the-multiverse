import java.util.ArrayList;
import java.util.Scanner;

public class CoffeeMaker extends BeverageMaker {
    public CoffeeMaker() {
        this.condiments = new ArrayList<String>();
    }

    @Override
    public void addBeverage() {
        this.beverage = "coffee";
    }

    @Override
    public void addCondiments() {
        while (true) {
            System.out.println("Press 0 to stop!");
            Scanner sc = new Scanner(System.in);
            String c = sc.nextLine();
            if (c.equals("0"))
                break;
            this.condiments.add(c);
        }
    }
}
