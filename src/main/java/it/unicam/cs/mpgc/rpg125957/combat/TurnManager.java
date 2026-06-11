package it.unicam.cs.mpgc.rpg125957.combat;

import it.unicam.cs.mpgc.rpg125957.entity.Enemy;
import it.unicam.cs.mpgc.rpg125957.entity.Player;

//Classe che gestisce i turni del combattimento
public class TurnManager {

    private final CombatManager combatManager;

    public TurnManager() {
        this.combatManager = new CombatManager();
    }

    //Esegue il turno del giocatore
    public CombatResult playerTurn(Player player, Enemy enemy) {
        return combatManager.attack(player, enemy);
    }

    //Esegue il turno del nemico
    public CombatResult enemyTurn(Enemy enemy, Player player) {
        return combatManager.attack(enemy, player);
    }

    //Controlla se il combattimento è finito
    public boolean isCombatOver(Player player, Enemy enemy) {
        return !player.isAlive() || !enemy.isAlive();
    }
}
