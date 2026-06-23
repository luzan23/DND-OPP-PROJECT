public class Rouge extends Player implements HeroicUnit {

    private int cost;
    private int currentEnergy;

    public Rouge(int cost, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints, int abilityCoolDown) {
        super(name, healthPool, healthAmount, attackPoints, defensePoints);
        this.cost = cost;
        this.currentEnergy = 100;
    }

    @Override
    public void castAbility() {
        this.currentEnergy -= cost;
        //deal damage
    }

    @Override
    public void onTick() {
        this.currentEnergy = Math.min(this.currentEnergy + 10, 100);
    }

    @Override
    protected void levelUp() {
        super.levelUp();
        this.currentEnergy = 100;
        this.attackPoints += 3 * this.level;
    }


    @Override
    public void death(Player p) {

    }
}
