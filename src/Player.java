public abstract class Player extends Unit implements HeroicUnit {
    protected int experience;
    protected int level;

    protected Player(String name, int healthPool, int attackPoints, int defencepoints) {
        super(name, healthPool, attackPoints, defencepoints);
    }
        public void addExperience(int amount) {

        }

        public void levelUp() {
            this.level++;
        }

        public abstract void castAbility();

        @Override
        public void onTick() {
        }

        @Override
        public void death() {
        }
}
