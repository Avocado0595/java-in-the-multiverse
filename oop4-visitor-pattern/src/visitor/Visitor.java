package visitor;

import explorer.MyFile;
import explorer.MyFolder;

public interface Visitor {
    public void visit(MyFile file);

    public void visit(MyFolder folder);
}
