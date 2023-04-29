a) Yêu cầu
Viết 1 chương trình pha chế đồ uống với các yêu cầu sau:
- Có 2 loại đồ uống Coffee và Tea
- Mỗi loại đồ uống có cách pha chế khác nhau,mô phỏng bằng hàm makeBeverage(), gồm 3 bước: 
  - thêm đồ uống: addBeverage(beverage)
  - thêm topping: addCondiments(condiment)
  - phục vụ: serveBeverage()

b) Phân tích
- Với 2 loại đồ uống => có 2 class tương ứng: CoffeeMaker, TeaMaker
- các phương thức chung với nhau nên sẽ cần 1 class abstract, còn có 1 prop lưu list condiment.
- method makeBeverage() dùng chung, các method addBeverage(), addCondiments(), serveBeverage() sẽ được override lại theo từng class con.
- 



> Tạo chương trình pha chế đồ uống bằng  Template. Chương trình nên có các lớp sau:
> 
> • BeverageMaker: một lớp trừu tượng xác định phương thức mẫu makeBeverage(), chịu trách nhiệm pha chế đồ uống. Lớp này cũng có hai phương thức trừu tượng, addBeverage() và addCondiments(), nên được thực hiện bởi các lớp con cụ thể.
>
> • CoffeeMaker: một lớp cụ thể của BeverageMaker triển khai các phương thức addBeverage() và addCondiments() cụ thể để pha cà phê.
> 
> • TeaMaker: một lớp cụ thể của BeverageMaker triển khai các phương thức addBeverage() và addCondiments() cụ thể để pha trà.
> 
> Chương trình sẽ trình bày cách hoạt động của phương thức template bằng cách tạo các thể hiện của các lớp CoffeeMaker và TeaMaker và gọi phương thức makeBeverage() trên mỗi thể hiện (instance)

b) UML

*sửa sau*

