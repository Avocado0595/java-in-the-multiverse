import doublyll.DoublyLL;
import doublyll.Student;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        DoublyLL<Integer> test0 = new DoublyLL<>();
        test0.append(9);
        test0.append(99);
        System.out.println();
        test0.printNodes();

        DoublyLL<Student> test1 = new DoublyLL<>();
        test1.append(new Student("duc 1", 20));
        test1.append(new Student("duc 2", 20));
        System.out.println();
        test1.printNodes();
    }
}
