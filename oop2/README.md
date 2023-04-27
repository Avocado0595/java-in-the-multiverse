## 1. Bài tập mở đầu
>Thiết kế chương trình theo sơ đồ dưới đây
![shape UML](./shape-uml.png)

*Giải thích sơ đồ
- ``<<abstract>>``: ghi chú abstract class
- Dấu thăng (#): protected
- ``entends``: kế thừa, mở rộng từ abstract class, nếu từ interface thì dùng ``implements`` (vì triển khai hàm từ interface).

## 2. abstract class - interface
|abstract class|interface|
|--|--|
|có abstract method và non-abstract method|chỉ có abstract method|
|không cho phép đa kế thừa|cho phép đa kế thừa|
|có thể có thuộc tính bất kỳ|chỉ có thể có thuộc tính static và final|
|có thể kế thừa (mở rộng) từ 1 class khác và nhiều interface khác|chỉ có thể mở rộng từ các interface khác|
|có thể khai báo phạm vi private, public, protected|chỉ có mặc định default|
|dùng làm class base cho các class cùng quan hệ: như Person có Student, Teacher, Officer, Manager|dùng để cung cấp 1 nhóm các chức năng chung cho những class có thể ko liên quan nhau|


## 3. Tính chất của OOP
- 1. Kế thừa: class con sẽ kế thừa những gì class cha cho kế thừa (tham khảo [Access Modifiers](../oop1/README.md)). Java chỉ cho phép kế thừa từ 1 class cha, và nhiều interface (C# cũng thế; còn C++, python thì cho phép đa kế thừa - 1 class con kế thừa nhiều class cha). Trong bài tập thì ``Shape`` là 1 class cha, các class ``Circle``, ``Rectangle`` là các class con kế thừa lại ``Shape`` và sẽ có các phương thức, thuộc tính mà ``Shape`` có (trừ cái nào private). Trong code có sử dụng ``super``, ``super`` chính là việc gọi lại class cha.
- 2. Đa hình: cùng 1 tên hàm nhưng có thể có các thức xử lý bên trong khác nhau
    + Đa hình trong compile (overloading): ``Shape()`` không nhận tham số sẽ khác với ``Shape(color, filles)`` có nhận tham số dù là cùng 1 hàm. Truyền các tham số tương ứng sẽ giúp class nhận biết được nên gọi hàm nào. 
    + Đa hình trong runtime (overriding): với annotation ``@Override`` giúp cho hàm ``toString()`` ở class ``Circle`` sẽ khác với ``toString()`` ở class ``Rectangle``. Tùy instance tạo ra từ class nào mà hàm tương ứng được override trong class đó sẽ được gọi
- 3. Đóng gói: Để tránh các tác động không cần thiết đến các thuộc tính, chúng nên được để private hoặc protected và được truy cập thông qua các method riêng. Nhằm che giấu đi các thông tin không cần thiết cho người dùng. ``color`` và ``filled`` được để là protected, và được get, set thông qua method tương ứng (gọi là getter và setter).
- 4. Tính trừu tượng: ``interface``, ``abstract class`` là thể hiện cho sự trừ tượng. Không thể tạo instance từ ``interface`` hay ``abstract class``, mà 2 loại này chỉ được dùng như 1 cấu trúc chung mà các class con phải tuân theo. Trong bài tập: mỗi khi cần 1 loại hình mới, ví dụ: Eclip, ``class Eclip`` này phải kế thừa từ ``Shape``, và theo đó khi dev ``class Eclip`` chúng ta chỉ việc tuân theo những gì đã có trong ``Shape`` và thêm những thứ cần thiết.