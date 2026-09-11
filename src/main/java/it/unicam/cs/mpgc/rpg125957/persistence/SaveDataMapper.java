package it.unicam.cs.mpgc.rpg125957.persistence;

import it.unicam.cs.mpgc.rpg125957.entity.Player;
import it.unicam.cs.mpgc.rpg125957.entity.Stats;
import it.unicam.cs.mpgc.rpg125957.inventory.Potion;
import it.unicam.cs.mpgc.rpg125957.model.GameState;

//Converts game objects to save data and vice versa
public class SaveDataMapper {

    public SaveData toSaveData(GameState gameState) {

        Player player = gameState.getPlayer();
        Stats stats = player.getStats();

        return new SaveData(
                player.getName(),
                stats.getMaxHealth(),
                stats.getHealth(),
                stats.getAttack(),
                stats.getDefense(),
                stats.getLevel(),
                stats.getExperience(),
                stats.getGold(),
                gameState.getCurrentFloor().getFloorNumber(),
                (int) player.getInventory().countItems(Potion.class)
        );
    }

    public Player toPlayer(SaveData saveData) {

        Stats stats = new Stats(
                saveData.getMaxHealth(),
                saveData.getHealth(),
                saveData.getAttack(),
                saveData.getDefense(),
                saveData.getLevel(),
                saveData.getExperience(),
                saveData.getGold()
        );

        Player player = new Player(
                saveData.getPlayerName(),
                stats
        );

        for (int i = 0; i < saveData.getPotionCount(); i++) {
            player.getInventory().addItem(
                    new Potion(
                            "Small Potion",
                            "Restores 25 HP",
                            25
                    )
            );
        }

        return player;
    }
}