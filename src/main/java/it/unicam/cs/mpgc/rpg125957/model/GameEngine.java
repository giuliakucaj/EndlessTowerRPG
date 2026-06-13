package it.unicam.cs.mpgc.rpg125957.model;

import it.unicam.cs.mpgc.rpg125957.combat.CombatResult;
import it.unicam.cs.mpgc.rpg125957.combat.TurnManager;
import it.unicam.cs.mpgc.rpg125957.entity.Enemy;
import it.unicam.cs.mpgc.rpg125957.entity.Player;
import it.unicam.cs.mpgc.rpg125957.tower.Room;
import it.unicam.cs.mpgc.rpg125957.tower.TowerManager;

//Classe che gestisce la logica principale del gioco
public class GameEngine {

    //Gestore della torre
    private final TowerManager towerManager;

    //Gestore dei turni di combattimento
    private final TurnManager turnManager;

    //Stato attuale della partita
    private final GameState gameState;

    //Costruttore del motore di gioco
    public GameEngine(Player player) {

        this.towerManager = new TowerManager();
        this.turnManager = new TurnManager();

        //Genera la prima stanza della torre
        Room firstRoom = towerManager.generateCurrentRoom();

        //Inizializza lo stato del gioco
        this.gameState = new GameState(player, firstRoom);
    }

    //Restituisce lo stato attuale della partita
    public GameState getGameState() {
        return gameState;
    }

    //Restituisce il nemico della stanza corrente
    public Enemy getCurrentEnemy() {
        return gameState.getCurrentRoom().getEnemy();
    }

    //Turno del giocatore
    public CombatResult playerAttack() {

        return turnManager.playerTurn(
                gameState.getPlayer(),
                getCurrentEnemy()
        );
    }

    //Turno del nemico
    public CombatResult enemyAttack() {

        return turnManager.enemyTurn(
                getCurrentEnemy(),
                gameState.getPlayer()
        );
    }

    //Controlla se il combattimento è terminato
    public boolean isCombatOver() {

        return turnManager.isCombatOver(gameState.getPlayer(), getCurrentEnemy()
        );
    }

    //Passa al piano successivo
    public void nextFloor() {

        //Avanza nella torre
        towerManager.nextFloor();

        //Genera una nuova stanza
        Room newRoom = towerManager.generateCurrentRoom();

        //Aggiorna lo stato del gioco
        gameState.setCurrentRoom(newRoom);
    }

    //Ricompense ottenute dopo la vittoria
    public boolean rewardPlayer() {
        boolean levelUp = gameState.getPlayer()
                .getStats()
                .addExperience(getCurrentEnemy().getRewardExperience());

        gameState.getPlayer()
                .getStats()
                .addGold(getCurrentEnemy().getRewardGold());

        return levelUp;
    }
}
