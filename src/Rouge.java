public class Rouge extends Player implements HeroicUnit{

    private int cost;
    private int currentEnergy;

    public Rouge(int cost){
        this.cost=cost;
        this.currentEnergy=100;
    }
    @Override
    public void castAbility() {

    }

    @Override
    public void onTick() {

    }

    @Override
    protected void levelUp() {

    }

    @Override
    public void death() {

    }

    @Override
    public void accept(Occupant o) {

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
