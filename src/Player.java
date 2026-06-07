public abstract class Player extends Unit {
    protected int experience;
    protected int level;

    public abstract void visit();
    public abstract void onTick();
    public abstract void addExperience();
    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    public void addExperience(int amount){
        throw new UnsupportedOperationException("not implemented");
    }
}
