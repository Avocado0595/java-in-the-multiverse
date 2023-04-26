import java.util.ArrayList;
import java.util.List;

import explorer.MyFile;
import explorer.MyFolder;
import explorer.StorageElement;
import visitor.GetSizeVisitor;
import visitor.Visitor;

public class App {
    public static void main(String[] args) throws Exception {
        List<StorageElement> test = new ArrayList<>() {
            {
                add(new MyFile("abc", 10));
                add(new MyFolder("eee", 20));
                add(new MyFolder("fff", 20));
            }
        };
        Visitor getSizeVisitor = new GetSizeVisitor();
        for (StorageElement s : test) {
            s.accept(getSizeVisitor);
        }
    }
}
