package explorer;

import visitor.Visitor;

public abstract class StorageElement {
    protected String name;
    protected double size;

    public StorageElement() {
        this.name = "";
        this.size = 0;
    }

    public StorageElement(String name, double size) {
        this.name = name;
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void accept(Visitor v);
}
