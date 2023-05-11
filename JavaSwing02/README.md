### Project Product Manager Simple

 Java + JDBC + MySQL + MVC


*Hướng dẫn fix lỗi jdbc.DRiver class notfound*
- thêm dependency này

```xml
    <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
```

- click phải project -> open module setting -> chọn Library -> chọn lại path cho mysql -> sửa ``Class.forName("com.mysql.cj.jdbc.Driver")`` nếu cần