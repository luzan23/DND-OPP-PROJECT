public class Rouge extends Player implements HeroicUnit{

    private int cost;
    private int currentEnergy;

    public Rouge(int cost){
        this.cost=cost;
        this.currentEnergy=100;
    }
    @Override
    public void castAbility() {
        this.currentEnergy-=cost;
        //deal damage
    }

    @Override
    public void onTick() {
        this.currentEnergy=Math.min(this.currentEnergy +10, 100);
    }

    @Override
    protected void levelUp() {
        this.currentEnergy=100;
        this.attackPoints+=3*this.level;
    }

    @Override
    public void death() {

    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
