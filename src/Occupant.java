public abstract class Occupant {
    protected char title;
    protected Position position;

    public Occupant(char title, Position pos){
        this.title=title;
        this.position=pos;
    }

    public Position getPosition(){
        return position;
    }
    public void setPosition(Position p){
        this.position=p;
    }

    public String toString(){
        return String.valueOf(this.title);
    }
    public abstract void accept(OccupantVisitor o);
}
