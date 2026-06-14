package it.unicam.cs.mpgc.rpg125957.model;

import it.unicam.cs.mpgc.rpg125957.combat.CombatResult;
import it.unicam.cs.mpgc.rpg125957.combat.TurnManager;
import it.unicam.cs.mpgc.rpg125957.entity.Enemy;
import it.unicam.cs.mpgc.rpg125957.entity.Player;
import it.unicam.cs.mpgc.rpg125957.inventory.Item;
import it.unicam.cs.mpgc.rpg125957.inventory.LootGenerator;
import it.unicam.cs.mpgc.rpg125957.inventory.Potion;
import it.unicam.cs.mpgc.rpg125957.tower.Room;
import it.unicam.cs.mpgc.rpg125957.tower.TowerManager;

import java.util.Optional;

//Classe che gestisce la logica principale del gioco
public class GameEngine {

    private final TowerManager towerManager;
    private final TurnManager turnManager;
    private final LootGenerator lootGenerator;
    private final GameState gameState;

    public GameEngine(Player player) {
        this.towerManager = new TowerManager();
        this.turnManager = new TurnManager();
        this.lootGenerator = new LootGenerator();

        Room firstRoom = towerManager.generateCurrentRoom();
        this.gameState = new GameState(player, firstRoom);
    }

    public GameState getGameState() {
        return gameState;
    }

    public Enemy getCurrentEnemy() {
        return gameState.getCurrentRoom().getEnemy();
    }

    public CombatResult playerAttack() {
        return turnManager.playerTurn(
                gameState.getPlayer(),
                getCurrentEnemy()
        );
    }

    public CombatResult enemyAttack() {
        return turnManager.enemyTurn(
                getCurrentEnemy(),
                gameState.getPlayer()
        );
    }

    public boolean isCombatOver() {
        return turnManager.isCombatOver(
                gameState.getPlayer(),
                getCurrentEnemy()
        );
    }

    //Ricompense ottenute dopo una vittoria
    public boolean rewardPlayer() {
        boolean levelUp = gameState.getPlayer()
                .getStats()
                .addExperience(getCurrentEnemy().getRewardExperience());

        gameState.getPlayer()
                .getStats()
                .addGold(getCurrentEnemy().getRewardGold());

        return levelUp;
    }

    //Genera un oggetto dopo la vittoria e lo aggiunge all'inventario
    public Optional<Item> generateLoot() {
        Optional<Item> loot = lootGenerator.generateLoot();

        loot.ifPresent(item ->
                gameState.getPlayer()
                        .getInventory()
                        .addItem(item)
        );

        return loot;
    }

    //Usa la prima pozione disponibile nell'inventario
    public boolean usePotion() {
        Potion potion = gameState.getPlayer()
                .getInventory()
                .getFirstPotion();

        if (potion == null) {
            return false;
        }

        potion.use(gameState.getPlayer());

        gameState.getPlayer()
                .getInventory()
                .removeItem(potion);

        return true;
    }

    public void nextFloor() {
        towerManager.nextFloor();

        Room newRoom = towerManager.generateCurrentRoom();

        gameState.setCurrentRoom(newRoom);
    }
}
