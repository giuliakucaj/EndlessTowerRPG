package it.unicam.cs.mpgc.rpg125957.model;

import it.unicam.cs.mpgc.rpg125957.combat.CombatResult;
import it.unicam.cs.mpgc.rpg125957.combat.TurnManager;
import it.unicam.cs.mpgc.rpg125957.entity.Enemy;
import it.unicam.cs.mpgc.rpg125957.entity.Player;
import it.unicam.cs.mpgc.rpg125957.entity.Stats;
import it.unicam.cs.mpgc.rpg125957.inventory.Item;
import it.unicam.cs.mpgc.rpg125957.inventory.LootGenerator;
import it.unicam.cs.mpgc.rpg125957.inventory.Potion;
import it.unicam.cs.mpgc.rpg125957.persistence.JsonSaveManager;
import it.unicam.cs.mpgc.rpg125957.persistence.SaveData;
import it.unicam.cs.mpgc.rpg125957.persistence.SaveManager;
import it.unicam.cs.mpgc.rpg125957.tower.Floor;
import it.unicam.cs.mpgc.rpg125957.tower.TowerManager;

import java.io.IOException;
import java.util.Optional;

public class GameEngine {

    private final TowerManager towerManager;
    private final TurnManager turnManager;
    private final LootGenerator lootGenerator;
    private final SaveManager saveManager;
    private GameState gameState;

    public GameEngine(Player player) {
        this.towerManager = new TowerManager();
        this.turnManager = new TurnManager();
        this.lootGenerator = new LootGenerator();
        this.saveManager = new JsonSaveManager();

        Floor firstFloor = towerManager.generateCurrentFloor();
        this.gameState = new GameState(player, firstFloor);
    }

    public GameState getGameState() {
        return gameState;
    }

    public Enemy getCurrentEnemy() {
        return gameState.getCurrentFloor().getEnemy();
    }

    public CombatResult playerAttack() {
        return turnManager.playerTurn(gameState.getPlayer(), getCurrentEnemy());
    }

    public CombatResult enemyAttack() {
        return turnManager.enemyTurn(getCurrentEnemy(), gameState.getPlayer());
    }

    public boolean isCombatOver() {
        return turnManager.isCombatOver(gameState.getPlayer(), getCurrentEnemy());
    }

    public boolean rewardPlayer() {
        boolean levelUp = gameState.getPlayer()
                .getStats()
                .addExperience(getCurrentEnemy().getRewardExperience());

        gameState.getPlayer()
                .getStats()
                .addGold(getCurrentEnemy().getRewardGold());

        return levelUp;
    }

    public Optional<Item> generateLoot() {
        Optional<Item> loot = lootGenerator.generateLoot();

        loot.ifPresent(item ->
                gameState.getPlayer()
                        .getInventory()
                        .addItem(item)
        );

        return loot;
    }

    public boolean usePotion() {
        Potion potion = gameState.getPlayer()
                .getInventory()
                .getFirstPotion();

        if (potion == null) {
            return false;
        }

        potion.use(gameState.getPlayer());
        gameState.getPlayer().getInventory().removeItem(potion);

        return true;
    }

    public void saveGame() throws IOException {
        Player player = gameState.getPlayer();

        SaveData saveData = new SaveData(
                player.getName(),
                player.getStats().getMaxHealth(),
                player.getStats().getHealth(),
                player.getStats().getAttack(),
                player.getStats().getDefense(),
                player.getStats().getLevel(),
                player.getStats().getExperience(),
                player.getStats().getGold(),
                gameState.getCurrentFloor().getFloorNumber()
        );

        saveManager.save(saveData);
    }

    public void loadGame() throws IOException {
        SaveData saveData = saveManager.load();

        Stats loadedStats = new Stats(
                saveData.getMaxHealth(),
                saveData.getHealth(),
                saveData.getAttack(),
                saveData.getDefense(),
                saveData.getLevel(),
                saveData.getExperience(),
                saveData.getGold()
        );

        Player loadedPlayer = new Player(saveData.getPlayerName(), loadedStats);

        towerManager.setCurrentFloor(saveData.getCurrentFloor());

        Floor loadedFloor = towerManager.generateCurrentFloor();

        this.gameState = new GameState(loadedPlayer, loadedFloor);
    }

    public void nextFloor() {
        towerManager.nextFloor();
        Floor newFloor = towerManager.generateCurrentFloor();
        gameState.setCurrentFloor(newFloor);
    }
}