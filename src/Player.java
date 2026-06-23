public abstract class Player extends Unit implements HeroicUnit {
    protected int experience;
    protected int level;

    public Player(String name, int healthPool, int attackPoints, int defencePoints) {
        super('@', null, name, healthPool, attackPoints, defencePoints);
        this.experience = 0;
        this.level = 1;
    }

    public int getLevel() { return level; }
    public int getExperience() { return experience; }

    public abstract void onTick();

    @Override
    public void accept(OccupantVisitor v) {
        v.visit(this);
    }

    // Player tries to move into a Player cell — doesn't happen
    @Override
    public void visit(Player p) { }

    // Player moves into Enemy cell — combat
    @Override
    public void visit(Enemy e) {
        boolean died = e.defend(this);
        if (died) {
            e.death(this);
        } else {
            // enemy counter-attacks
            boolean playerDied = this.defend(e);
            if (playerDied) {
                this.title = 'X';
                notify(this.name + " was defeated!");
            }
        }
    }

    public void addExperience(int amount) {
        this.experience += amount;
        while (this.experience >= 50 * this.level) {
            levelUp();
        }
    }

    protected void levelUp() {
        this.experience -= (50 * this.level);
        this.level += 1;
        int hp = 10 * this.level;
        int atk = 4 * this.level;
        int def = this.level;
        this.healthPool += hp;
        this.healthAmount = this.healthPool;
        this.attackPoints += atk;
        this.defencePoints += def;
        notify(this.name + " reached level " + this.level + ": +" + hp + " Health, +" + atk + " Attack, +" + def + " Defense");
    }

    @Override
    public void death(Player p) {
        // player's death is handled in visit(Enemy)
    }

    @Override
    public abstract String description();

    public abstract void castAbility();
}
