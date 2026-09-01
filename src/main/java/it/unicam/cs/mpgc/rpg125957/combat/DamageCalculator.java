package it.unicam.cs.mpgc.rpg125957.combat;
import it.unicam.cs.mpgc.rpg125957.entity.Entity;

//Calculates damage during combat
public class DamageCalculator {

    //Calculates damage based on attack and defense values
    public int calculateDamage(Entity attacker, Entity defender) {
        int attack = attacker.getStats().getAttack();
        int defense = defender.getStats().getDefense();

        return Math.max(1, attack - defense);
    }
}

