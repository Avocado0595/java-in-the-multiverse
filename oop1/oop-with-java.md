# OOP (Object-oriented programming - Lập trình hướng đối tượng)

## 1. Class
a) Yêu cầu
> Thiết kế class phân số với các chức năng
> - Khởi tạo phân số, kiểm tra phân số hợp lệ (mẫu khác 0)
> - In phân số
> - Cộng, trừ, nhân, chia phân số
> - Rút gọn phân số

b) Phân tích: class Fraction bao gồm
+ Tử số (numerator) - type int (private)
+ Mẫu số (denominator) - type int (private)
+ Constructor (public): Fraction() - khởi tạo phân số mặc định (0), Fraction(numerator,denominator) - khởi tạo phân số với tử số và mẫu số
+ getter, setter cho tử và mẫu (tính đóng gói) (public)
+ override toString() để in phân số (tính đa hình)
+ các phương thức: cộng, trừ, nhân, chia, rút gọn

c) Vẽ UML

![fraction-uml](./fraction-uml.png)

d) code
```java
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
}

```