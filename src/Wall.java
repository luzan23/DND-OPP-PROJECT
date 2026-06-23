public class Wall extends Cell {

    public Wall(Position p) {
        super(p, '#');
    }

    @Override
    public void accept(CellVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return "#";
    }
}
