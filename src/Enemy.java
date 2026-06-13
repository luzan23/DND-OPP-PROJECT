public abstract class Enemy extends Unit {

    protected int experiencee;


    protected Enemy(String name, int healthPool, int attackPoints, int defencepoints) {
        super(name, healthPool, attackPoints, defencepoints);

    }

        @Override
        public void death() {
        }

}
