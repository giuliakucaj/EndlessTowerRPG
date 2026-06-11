package it.unicam.cs.mpgc.rpg125957.combat;

import it.unicam.cs.mpgc.rpg125957.entity.Entity;

//Classe che gestisce un'azione di combattimento
public class CombatManager {

    private final DamageCalculator damageCalculator;

    public CombatManager() {
        this.damageCalculator = new DamageCalculator();
    }

    //Esegue un attacco tra due entità
    public String attack(Entity attacker, Entity defender) {
        int damage = damageCalculator.calculateDamage(attacker, defender);
        defender.takeDamage(damage);

        return attacker.getName() + " attacks " + defender.getName()
                + " and deals " + damage + " damage.";
    }
}
