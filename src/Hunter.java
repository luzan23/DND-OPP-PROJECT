public class Hunter extends Player {
    private int range;
    private int arrowsCount;
    private int ticksCount;

    public Hunter(String name, int healthPool, int attackPoints, int defencepoints, int range) {
        super(name, healthPool, attackPoints, defencepoints);
        this.range = range;
        this.arrowsCount = 10 * level;
        this.ticksCount = 0;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        this.arrowsCount += 10 * level;
        // TODO: להוסיף את הבונוסים הייחודיים של הצייד להתקפה והגנה
    }

    @Override
    public void castAbility() {
        if (arrowsCount <= 0) {
            // TODO: לשלוח הודעה שאין מספיק חיצים
            return;
        }
        arrowsCount--;
        // TODO: לוגיקת יריית חץ על האויב הקרוב ביותר בטווח
    }

    @Override
    public void onTick() {
        if (ticksCount == 10) {
            arrowsCount += level;
            ticksCount = 0;
        } else {
            ticksCount++;
        }
    }

    @Override
    public void accept(OccupantVisitor v) {

    }
}