public class Rouge extends Player {
    private int energyPool;
    private int energyAmount;
    private int energyCost;

    public Rouge(String name, int healthPool, int attackPoints, int defencepoints, int energyCost) {
        super(name, healthPool, attackPoints, defencepoints);
        this.energyPool = 100; // מקסימום אנרגיה קבוע של נוכל
        this.energyAmount = energyPool;
        this.energyCost = energyCost;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        this.energyAmount = energyPool;
        // TODO: להוסיף את שאר הבונוסים הייחודיים של הנוכל להתקפה
    }

    @Override
    public void castAbility() {
        if (energyAmount < energyCost) {
            // TODO: לשלוח הודעה שאין מספיק אנרגיה
            return;
        }
        energyAmount -= energyCost;
        // TODO: לוגיקת תקיפת כל האויבים בטווח 1
    }

    @Override
    public void onTick() {
        energyAmount = Math.min(energyAmount + 10, energyPool);
    }

    @Override
    public void accept(OccupantVisitor v) {

    }
}