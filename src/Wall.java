public class Wall extends Cell{

    public Wall(Position p, char title) {
        super(p, title);
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
