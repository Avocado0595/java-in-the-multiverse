
import java.util.List;

public abstract class BeverageMaker {

    protected List<String> condiments;
    protected String beverage;

    public void makeBeverage() {
        this.addBeverage();
        this.addCondiments();
        this.serveBeverage();
    }

    public void serveBeverage() {
        System.out.println(this.beverage);
        System.out.print("Your condiments: ");
        for (String i : condiments) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    abstract void addBeverage();

    abstract void addCondiments();
}
