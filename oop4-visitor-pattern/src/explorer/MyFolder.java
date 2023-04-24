package explorer;

import visitor.Visitor;

public class MyFolder extends StorageElement {
    public MyFolder(String name, double size) {
        super(name, size);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
