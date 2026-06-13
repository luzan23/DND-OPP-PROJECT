public class Boss extends Monster implements HeroicUnit {
    private int abilityFrequency;
    private int combatTicks;

    public Boss(String name, char character, int healthPool, int attackPoints, int defencepoints, int visionRange, int experienceValue, int abilityFrequency) {
        super(name, character, healthPool, attackPoints, defencepoints, visionRange, experienceValue);
        this.abilityFrequency = abilityFrequency;
        this.combatTicks = 0;
    }

    @Override
    public void onTick() {
        // בוס יורש את תנועת המפלצת הרגילה, ובנוסף טוען את היכולת המיוחדת שלו
        super.onTick();
        // TODO: לוגיקת בדיקה אם הוא במצב קרב, קידום combatTicks והפעלת יכולת הבוס כשהתדר מתאים
    }

    @Override
    public void castAbility() {

    }
}