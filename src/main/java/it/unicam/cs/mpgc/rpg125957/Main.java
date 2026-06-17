package it.unicam.cs.mpgc.rpg125957;

import it.unicam.cs.mpgc.rpg125957.combat.CombatResult;
import it.unicam.cs.mpgc.rpg125957.entity.Player;
import it.unicam.cs.mpgc.rpg125957.entity.Stats;
import it.unicam.cs.mpgc.rpg125957.model.GameEngine;
import it.unicam.cs.mpgc.rpg125957.view.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Classe principale che avvia il gioco
public class Main extends Application {

    @Override
    public void start(Stage stage) {

        //Creazione del giocatore iniziale
        Player player = new Player(
                "Hero",
                new Stats(100, 25, 5)
        );

        //Motore del gioco
        GameEngine gameEngine = new GameEngine(player);

        //Interfaccia grafica
        GameView gameView = new GameView();

        //Aggiorna le informazioni mostrate nella GUI
        Runnable updateUI = () -> {

            gameView.getFloorLabel().setText(
                    "Floor: " + gameEngine.getGameState()
                            .getCurrentRoom()
                            .getFloorNumber()
            );

            gameView.getPlayerHpLabel().setText(
                    "Player HP: " + gameEngine.getGameState()
                            .getPlayer()
                            .getStats()
                            .getHealth()
            );

            gameView.getLevelLabel().setText(
                    "Level: " + gameEngine.getGameState()
                            .getPlayer()
                            .getStats()
                            .getLevel()
            );

            gameView.getXpLabel().setText(
                    "XP: " + gameEngine.getGameState()
                            .getPlayer()
                            .getStats()
                            .getExperience()
            );

            gameView.getGoldLabel().setText(
                    "Gold: " + gameEngine.getGameState()
                            .getPlayer()
                            .getStats()
                            .getGold()
            );

            gameView.getPotionsLabel().setText(
                    "Potions: " + gameEngine.getGameState()
                            .getPlayer()
                            .getInventory()
                            .countPotions()
            );

            gameView.getEnemyLabel().setText(
                    "Enemy: " + gameEngine.getCurrentEnemy()
                            .getName()
            );
        };

        updateUI.run();

        //Pulsante Attack
        gameView.getAttackButton().setOnAction(event -> {

            CombatResult playerResult = gameEngine.playerAttack();
            gameView.getCombatLog().appendText(playerResult.getMessage() + "\n");

            if (!gameEngine.getCurrentEnemy().isAlive()) {

                gameView.getCombatLog().appendText("Victory!\n");

                boolean levelUp = gameEngine.rewardPlayer();

                gameEngine.generateLoot().ifPresent(item ->
                        gameView.getCombatLog()
                                .appendText("Loot found: " + item.getName() + "\n")
                );

                if (levelUp) {
                    gameView.getCombatLog().appendText("LEVEL UP!\n");
                }

                try {
                    gameEngine.saveGame();
                    gameView.getCombatLog().appendText("Game saved.\n");
                } catch (Exception e) {
                    gameView.getCombatLog().appendText("Save failed.\n");
                }

                gameEngine.nextFloor();

                gameView.getCombatLog().appendText("Moving to next floor...\n");
                gameView.getCombatLog().appendText("----------------------\n");

                updateUI.run();
                return;
            }

            CombatResult enemyResult = gameEngine.enemyAttack();
            gameView.getCombatLog().appendText(enemyResult.getMessage() + "\n");

            updateUI.run();

            if (!gameEngine.getGameState().getPlayer().isAlive()) {
                gameView.getCombatLog().appendText("Game Over!\n");
                gameView.getAttackButton().setDisable(true);
            }
        });

        //Pulsante Use Potion
        gameView.getPotionButton().setOnAction(event -> {

            boolean usedPotion = gameEngine.usePotion();

            if (usedPotion) {
                gameView.getCombatLog().appendText("Potion used!\n");
            } else {
                gameView.getCombatLog().appendText("No potion available.\n");
            }

            updateUI.run();
        });

        //Pulsante Save
        gameView.getSaveButton().setOnAction(event -> {

            try {
                gameEngine.saveGame();
                gameView.getCombatLog().appendText("Game saved.\n");
            } catch (Exception e) {
                gameView.getCombatLog().appendText("Save failed.\n");
            }
        });

        //Pulsante Load
        gameView.getLoadButton().setOnAction(event -> {

            try {
                gameEngine.loadGame();

                gameView.getCombatLog().clear();
                gameView.getCombatLog().appendText("Game loaded.\n");

                updateUI.run();

                gameView.getAttackButton().setDisable(false);

            } catch (Exception e) {
                gameView.getCombatLog().appendText("Load failed.\n");
            }
        });

        Scene scene = new Scene(gameView, 600, 500);

        stage.setTitle("Endless Tower RPG");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}