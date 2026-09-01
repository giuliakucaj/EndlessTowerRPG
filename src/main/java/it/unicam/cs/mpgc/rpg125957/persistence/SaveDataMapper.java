package it.unicam.cs.mpgc.rpg125957.persistence;

import it.unicam.cs.mpgc.rpg125957.entity.Player;
import it.unicam.cs.mpgc.rpg125957.entity.Stats;
import it.unicam.cs.mpgc.rpg125957.model.GameState;

//Converts game domain objects to and from persistence data
public class SaveDataMapper {

    //Converts the current game state into save data
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
                gameState.getCurrentFloor().getFloorNumber()
        );
    }

    //Reconstructs a player from saved data
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

        return new Player(
                saveData.getPlayerName(),
                stats
        );
    }
}

