public abstract class Unit implements Occupant, CellVisitor, OccupantVisitor {
    public String name;
    private int healthPool;
    private int healthAmount;
    private int attackPoints;
    private int defencepoints;
    protected Position position; // הוספנו את השדה כדי שהיחידה תזכור את המיקום שלה

    protected Unit(String name, int healthPool, int attackPoints, int defencepoints) {
        this.name = name;
        this.healthPool = healthPool;
        this.healthAmount = healthPool;
        this.attackPoints = attackPoints;
        this.defencepoints = defencepoints;
    }

    public void attack(Unit defender){
    }

    public int defend(){
        return 0;
    }

    public void death(){
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    public String getName() {
        return name;
    }
    public abstract void onTick();

    @Override public void visit(Wall w) {}
    @Override public void visit(Enemy e) {}
    @Override public void visit(Floor f) {
        if (f.getOccupant() != null) {
            f.getOccupant().accept(this);
        } else {}
    }
    @Override public void visit(Player p) {}
    @Override public void setPosition() {}
}