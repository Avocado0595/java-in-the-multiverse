package explorer;

import visitor.Visitor;

public class MyFile extends StorageElement {
    public MyFile(String name, double size) {
        super(name, size);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
