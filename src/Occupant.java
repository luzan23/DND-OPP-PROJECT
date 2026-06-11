public interface Occupant {
    public Position getPosition();
    public  void setPosition(Position p);
    public void accept(OccupantVisitor o);
}
