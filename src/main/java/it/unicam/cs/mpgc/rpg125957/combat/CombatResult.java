package it.unicam.cs.mpgc.rpg125957.combat;

//Represents the result of a single combat action
public class CombatResult {

    private final String message;
    private final int damage;
    private final boolean defenderDefeated;

    public CombatResult(String message, int damage, boolean defenderDefeated) {
        this.message = message;
        this.damage = damage;
        this.defenderDefeated = defenderDefeated;
    }
    public String getMessage() {
        return message;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isDefenderDefeated() {
        return defenderDefeated;
    }
}
