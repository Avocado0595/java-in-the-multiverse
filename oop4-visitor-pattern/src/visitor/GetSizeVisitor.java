package visitor;

import explorer.MyFile;
import explorer.MyFolder;

public class GetSizeVisitor implements Visitor {
    @Override
    public void visit(MyFile file) {
        System.out.println("file size: " + file.getSize());
    };

    @Override
    public void visit(MyFolder folder) {
        System.out.println("folder size: " + folder.getSize());
    };
}