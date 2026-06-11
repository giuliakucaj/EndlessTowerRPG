package it.unicam.cs.mpgc.rpg125957.combat;
import it.unicam.cs.mpgc.rpg125957.entity.Entity;

//Classe responsabile del calcolo del danno
public class DamageCalculator {

    //Calcola il danno considerando attacco e difesa
    public int calculateDamage(Entity attacker, Entity defender) {
        int attack = attacker.getStats().getAttack();
        int defense = defender.getStats().getDefense();

        return Math.max(1, attack - defense);
    }
}

