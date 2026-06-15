package it.unicam.cs.mpgc.rpg125957;

import it.unicam.cs.mpgc.rpg125957.combat.CombatResult;
import it.unicam.cs.mpgc.rpg125957.entity.Player;
import it.unicam.cs.mpgc.rpg125957.entity.Stats;
import it.unicam.cs.mpgc.rpg125957.model.GameEngine;

public class Main {

    public static void main(String[] args) {

        Player player = new Player(
                "Hero",
                new Stats(100, 25, 5)
        );

        GameEngine gameEngine =
                new GameEngine(player);

        //Carica il salvataggio se esiste
        try {
            gameEngine.loadGame();
            System.out.println("Game loaded!");
        } catch (Exception e) {
            System.out.println("No save found. Starting new game.");
        }

        for (int floor = 0; floor < 5; floor++) {

            System.out.println(
                    "FLOOR "
                            + gameEngine.getGameState()
                            .getCurrentRoom()
                            .getFloorNumber()
            );

            System.out.println(
                    "Enemy: "
                            + gameEngine.getCurrentEnemy()
                            .getName()
            );

            while (!gameEngine.isCombatOver()) {

                CombatResult playerResult =
                        gameEngine.playerAttack();

                System.out.println(
                        playerResult.getMessage()
                );

                if (!gameEngine.getCurrentEnemy()
                        .isAlive()) {
                    break;
                }

                CombatResult enemyResult =
                        gameEngine.enemyAttack();

                System.out.println(
                        enemyResult.getMessage()
                );

                //Usa automaticamente una pozione
                if (player.getStats()
                        .getHealth() <= 50) {

                    boolean usedPotion =
                            gameEngine.usePotion();

                    if (usedPotion) {
                        System.out.println(
                                "Potion used!"
                        );

                        System.out.println(
                                "Player HP: "
                                        + player.getStats()
                                        .getHealth()
                        );
                    }
                }
            }

            if (!gameEngine.getGameState()
                    .getPlayer()
                    .isAlive()) {

                System.out.println(
                        "Game Over!"
                );

                return;
            }

            System.out.println(
                    "Victory!"
            );

            boolean levelUp =
                    gameEngine.rewardPlayer();

            gameEngine.generateLoot()
                    .ifPresent(item ->
                            System.out.println(
                                    "Loot found: "
                                            + item.getName()
                            )
                    );

            System.out.println(
                    "XP: "
                            + gameEngine.getGameState()
                            .getPlayer()
                            .getStats()
                            .getExperience()
            );

            System.out.println(
                    "Gold: "
                            + gameEngine.getGameState()
                            .getPlayer()
                            .getStats()
                            .getGold()
            );

            if (levelUp) {
                System.out.println(
                        "LEVEL UP!"
                );

                System.out.println(
                        "Level: "
                                + gameEngine.getGameState()
                                .getPlayer()
                                .getStats()
                                .getLevel()
                );
            }

            //Salvataggio automatico
            try {
                gameEngine.saveGame();
                System.out.println(
                        "Game saved."
                );
            } catch (Exception e) {
                System.out.println(
                        "Save failed."
                );
            }

            System.out.println(
                    "----------------------"
            );

            gameEngine.nextFloor();
        }
    }
}