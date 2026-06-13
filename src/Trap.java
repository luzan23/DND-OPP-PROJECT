public class Trap extends Enemy {
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Trap(String name, int healthPool, int attackPoints, int defencepoints, int visibilityTime, int invisibilityTime, int experienceValue) {
        super(name,healthPool, attackPoints,defencepoints);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
    }

    @Override
    public void onTick() {
        ticksCount++;
        if (visible) {
            if (ticksCount == visibilityTime) {
                visible = false;
                ticksCount = 0;
            }
        } else {
            if (ticksCount == invisibilityTime) {
                visible = true;
                ticksCount = 0;
            }
        }
        // TODO: לבדוק אם השחקן עומד באותו מיקום כמו המלכודת (טווח 0), ואם כן - לתקוף אותו
    }

    @Override
    public String toString() {
        return "not yet";
    }

    @Override
    public void accept(OccupantVisitor v) {

    }
}