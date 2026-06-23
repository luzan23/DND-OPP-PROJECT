public interface CellVisitor {
    void visit(Wall w);
    void visit(Floor f);
}
