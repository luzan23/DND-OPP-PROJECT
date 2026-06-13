public interface Occupant {
    public Position getPosition();
    public  void setPosition();
    void accept(OccupantVisitor v);

}
