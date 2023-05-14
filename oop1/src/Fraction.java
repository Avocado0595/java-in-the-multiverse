public class Fraction {
    private int numerator;
    private int dominator;

    public Fraction() {
    };

    public Fraction(int numerator, int dominator) throws Exception {
        this.numerator = numerator;
        if (dominator == 0)
            throw new Exception("Invalid dominator: =0");
        this.dominator = dominator;
    };

    public int getNumerator() {
        return this.numerator;
    }

    public int getDominator() {
        return this.dominator;
    }

    @Override
    public String toString() {
        if (this.dominator == 1)
            return this.numerator + "";
        if (this.numerator == 0)
            return "0";
        return String.format("%d/%d", this.numerator, this.dominator);
    };

    public Fraction add(Fraction f) throws Exception {
        return new Fraction(this.numerator * f.getDominator() + f.getNumerator() * this.dominator,
                this.dominator * f.getDominator()).simplify();
    }

    public Fraction sub(Fraction f) throws Exception {
        return new Fraction(this.numerator * f.getDominator() - f.getNumerator() * this.dominator,
                this.dominator * f.getDominator()).simplify();
    }

    public Fraction mul(Fraction f) throws Exception {
        return new Fraction(this.numerator * f.getNumerator(), this.dominator * f.getDominator()).simplify();
    }

    public Fraction div(Fraction f) throws Exception {
        return new Fraction(this.numerator * f.getDominator(), this.dominator * f.getNumerator()).simplify();
    }

    public Fraction simplify() throws Exception {
        int gcd = MyMath.GCD(this.numerator, this.dominator);
        return new Fraction(this.numerator / gcd, this.dominator / gcd);
    }

    public Fraction op(String op, Fraction f) throws Exception {
        switch (op) {
            case "+":
                return this.add(f);
            case "-":
                return this.sub(f);
            case "*":
                return this.mul(f);
            case "/":
                return this.div(f);
            default:
                throw new Exception("Invalid op");

        }
    }
}
