import java.util.ArrayList;
import java.util.Scanner;

public class TeaMaker extends BeverageMaker {
    public TeaMaker() {
        this.condiments = new ArrayList<String>();
    }

    @Override
    public void addBeverage() {
        this.beverage = "tea";
        System.out.println("making tea...");
    }

    @Override
    public void addCondiments() {
        while (true) {
            System.out.println("Enter condiment's name.");
            System.out.println("Press 0 to stop!");
            Scanner sc = new Scanner(System.in);
            String c = sc.nextLine();
            if (c.equals("0"))
                break;
            this.condiments.add(c);
        }
    }
}
