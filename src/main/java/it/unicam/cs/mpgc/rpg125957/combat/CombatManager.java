package it.unicam.cs.mpgc.rpg125957.combat;

import it.unicam.cs.mpgc.rpg125957.entity.Entity;

//Classe che gestisce un'azione di combattimento
public class CombatManager {

    private final DamageCalculator damageCalculator;

    public CombatManager() {
        this.damageCalculator = new DamageCalculator();
    }

    //Esegue un attacco tra due entità
    public CombatResult attack(Entity attacker, Entity defender) {
        int damage = damageCalculator.calculateDamage(attacker, defender);
        defender.takeDamage(damage);

        String message = attacker.getName() + " attacks "
                + defender.getName()
                + " and deals "
                + damage
                + " damage.";

        return new CombatResult(
                message,
                damage,
                !defender.isAlive()
        );
    }
}
