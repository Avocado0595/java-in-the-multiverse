package doublyll;

public class Student {
    public Student() {

    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, age: ", this.name, this.age);
    }
}
