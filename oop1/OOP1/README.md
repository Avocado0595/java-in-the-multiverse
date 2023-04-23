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