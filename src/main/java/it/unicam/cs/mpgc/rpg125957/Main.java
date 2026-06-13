package it.unicam.cs.mpgc.rpg125957;

import it.unicam.cs.mpgc.rpg125957.combat.CombatResult;
import it.unicam.cs.mpgc.rpg125957.entity.Player;
import it.unicam.cs.mpgc.rpg125957.entity.Stats;
import it.unicam.cs.mpgc.rpg125957.model.GameEngine;

public class Main {

    public static void main(String[] args) {
        Player player = new Player("Hero", new Stats(100, 25, 5));
        GameEngine gameEngine = new GameEngine(player);

        for (int floor = 0; floor < 3; floor++) {
            System.out.println("FLOOR "
                    + gameEngine.getGameState().getCurrentRoom().getFloorNumber());

            System.out.println("Enemy: "
                    + gameEngine.getCurrentEnemy().getName());

            while (!gameEngine.isCombatOver()) {
                CombatResult playerResult = gameEngine.playerAttack();
                System.out.println(playerResult.getMessage());

                if (!gameEngine.getCurrentEnemy().isAlive()) {
                    break;
                }

                CombatResult enemyResult = gameEngine.enemyAttack();
                System.out.println(enemyResult.getMessage());
            }

            if (!player.isAlive()) {
                System.out.println("Game Over!");
                return;
            }

            System.out.println("Victory!");

            boolean levelUp = gameEngine.rewardPlayer();

            System.out.println("XP: " + player.getStats().getExperience());
            System.out.println("Gold: " + player.getStats().getGold());

            if (levelUp) {
                System.out.println("LEVEL UP!");
                System.out.println("Level: " + player.getStats().getLevel());
            }

            System.out.println("----------------------");

            gameEngine.nextFloor();
        }
    }
}
