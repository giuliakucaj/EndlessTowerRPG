package it.unicam.cs.mpgc.rpg125957.combat;

import it.unicam.cs.mpgc.rpg125957.entity.Entity;

//Handles combat actions
public class CombatManager {

    private final DamageCalculator damageCalculator;

    public CombatManager(DamageCalculator damageCalculator) {
        this.damageCalculator = damageCalculator;
    }

    //Executes an attack between two entities
    public CombatResult attack(Entity attacker, Entity defender) {
        int damage = damageCalculator.calculateDamage(
                attacker,
                defender
        );

        defender.takeDamage(damage);

        String message = attacker.getName()
                + " attacks "
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