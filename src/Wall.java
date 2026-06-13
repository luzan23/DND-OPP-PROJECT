public class Wall extends Cell {

    public Wall(Position position) {
        super(position);
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