public abstract class Player extends Unit implements HeroicUnit{
    protected int experience;
    protected int level;

    public Player(String name, int healthPool, int healthAmount, int attackPoints, int defensePoints){
        super(name, healthPool, healthAmount, attackPoints, defensePoints);
        this.experience=0;
        this.level=1;
    }
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

    protected void levelUp(){
        addExperience(50*this.level);
        this.level+=1;
        this.healthPool+=(10*this.level);
        this.healthAmount=this.healthPool;
        this.attackPoints+=(4*this.level);
        this.defencePoints+=this.level;
    }
}
