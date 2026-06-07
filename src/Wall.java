public class Wall extends Cell{
    @Override
    public void accept(CellVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return "";
    }

}
