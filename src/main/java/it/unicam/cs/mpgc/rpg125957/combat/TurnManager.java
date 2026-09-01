package it.unicam.cs.mpgc.rpg125957.combat;

import it.unicam.cs.mpgc.rpg125957.entity.Enemy;
import it.unicam.cs.mpgc.rpg125957.entity.Player;

//Handles turn management during combat
public class TurnManager {

    private final CombatManager combatManager;

    public TurnManager() {
        this.combatManager = new CombatManager();
    }

    //Executes the player's turn
    public CombatResult playerTurn(Player player, Enemy enemy) {
        return combatManager.attack(player, enemy);
    }

    //Executes the enemy's turn
    public CombatResult enemyTurn(Enemy enemy, Player player) {
        return combatManager.attack(enemy, player);
    }

    //Check whether the combat is over
    public boolean isCombatOver(Player player, Enemy enemy) {
        return !player.isAlive() || !enemy.isAlive();
    }
}
