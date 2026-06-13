public class Monster extends Enemy {
    protected int visionRange;

    public Monster(String name, char character, int healthPool, int attackPoints, int defencepoints, int visionRange, int experienceValue) {
        super(name,healthPool, attackPoints,defencepoints);
        this.visionRange = visionRange;
    }

    @Override
    public void onTick() {
        // TODO: לוגיקת תנועה מבוססת מרחק מהשחקן (אם בטווח הראייה - לרדוף, אחרת לזוז אקראית)
    }

    @Override
    public void accept(OccupantVisitor v) {

    }
}