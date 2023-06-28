import java.util.Scanner;

public class App {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Fraction f1 = null;
        Fraction f2 = null;
        Fraction f3 = null;
        Fraction result = null;
        boolean stop = false;
        String op1 = "", op2 = "";
        while (!stop) {
            System.out.println("input fraction: ");
            Fraction f = inputNewFraction();
            result = f;
            if (f1 == null) {
                f1 = f;
            } else {
                if (f2 == null) {
                    f2 = f;
                } else {
                    f3 = f;
                }
            }
            scan.nextLine();
            System.out.print("input operation:");
            String s = scan.nextLine();
            // System.out.print(s.equals("+"));
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                if (f2 == null) {
                    op1 = s;
                } else {
                    if (f3 == null) {
                        op2 = s;
                    } else if (f3 != null) {
                        if (op1 == "+" || op1 == "-") {
                            f1 = f1.op(op1, f2);
                            f2 = f3;
                            op1 = op2;

                        } else {
                            f2 = f2.op(op2, f3);

                        }
                        f3 = null;
                        op2 = s;

                    }
                }
            } else {
                stop = true;

            }
            System.out.println("f1: " + f1.toString());
            if (f2 != null)
                System.out.println("f2: " + f2.toString());
            if (f3 != null)
                System.out.println("f3: " + f3.toString());
            if (f2 != null) {
                result = f1.op(op1, f2);
                System.out.println("result: " + result.toString());
            }
        }
    }

    static Fraction inputNewFraction() throws Exception {
        System.out.print("nhap tu:");
        int a = scan.nextInt();
        System.out.print("nhap mau:");
        int b = scan.nextInt();
        return new Fraction(a, b);
    }

    public int sum(int n) {
        if (n <= 1)
            return n;
        return n + sum(n - 1);
    }
}
