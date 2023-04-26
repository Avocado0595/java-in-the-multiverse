public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(4, 6);
        System.out.println((f2.add(f1)).toString());
    }
}
