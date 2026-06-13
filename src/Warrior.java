public class Warrior extends Player {
    private int cooldownPool;
    private int cooldownRemaining;

    public Warrior(String name, int healthPool, int attackPoints, int defencepoints, int cooldownPool) {
        super(name, healthPool, attackPoints, defencepoints);
        this.cooldownPool = cooldownPool;
        this.cooldownRemaining = 0;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        this.cooldownRemaining = 0;
        // TODO: להוסיף את שאר הבונוסים הייחודיים של הלוחם לחיים, התקפה והגנה
    }

    @Override
    public void castAbility() {
        if (cooldownRemaining > 0) {
            // TODO: לשלוח הודעה שהיכולת בציקלוני עדיין בטעינה
            return;
        }
        cooldownRemaining = cooldownPool;
        // TODO: לוגיקת הריפוי וההתקפה על אויבים בטווח 3
    }

    @Override
    public void onTick() {
        if (cooldownRemaining > 0) {
            cooldownRemaining--;
        }
    }

    @Override
    public void accept(OccupantVisitor v) {

    }
}