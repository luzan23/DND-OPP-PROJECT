public abstract class Player extends Unit {
    protected int experience;
    protected int level;

    public abstract void onTick();

    @Override
    public void accept(OccupantVisitor v) {
        v.visit(this);
    }
    public void visit(Player p){
        //remains empty
    }
    public void visit(Enemy e){
        this.attack(e);
    }

    public void addExperience(int amount){
        this.experience+=amount;
    }

    protected abstract void levelUp();
}
