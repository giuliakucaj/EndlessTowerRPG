package it.unicam.cs.mpgc.rpg125957.controller;

import it.unicam.cs.mpgc.rpg125957.combat.CombatResult;
import it.unicam.cs.mpgc.rpg125957.model.GameEngine;
import it.unicam.cs.mpgc.rpg125957.view.GameView;

import java.io.IOException;

//Coordinates the interaction between the GUI and the game engine
public class GameController {

    private final GameEngine gameEngine;
    private final GameView gameView;

    public GameController(GameEngine gameEngine, GameView gameView) {
        this.gameEngine = gameEngine;
        this.gameView = gameView;

        configureActions();
        updateUI();
    }

    //Connects GUI buttons to game actions
    private void configureActions() {
        gameView.getAttackButton().setOnAction(event -> handleAttack());
        gameView.getPotionButton().setOnAction(event -> handlePotion());
        gameView.getSaveButton().setOnAction(event -> handleSave());
        gameView.getLoadButton().setOnAction(event -> handleLoad());
    }

    //Handles the attack action
    private void handleAttack() {
        CombatResult playerResult = gameEngine.playerAttack();

        gameView.getCombatLog()
                .appendText(playerResult.getMessage() + "\n");

        updateUI();

        if (!gameEngine.getCurrentEnemy().isAlive()) {
            handleVictory();
            return;
        }

        CombatResult enemyResult = gameEngine.enemyAttack();

        gameView.getCombatLog()
                .appendText(enemyResult.getMessage() + "\n");

        updateUI();

        if (!gameEngine.getGameState()
                .getPlayer()
                .isAlive()) {

            handleGameOver();
        }
    }

    //Handles victory on the current floor
    private void handleVictory() {
        gameView.getCombatLog()
                .appendText("Victory!\n");

        boolean levelUp = gameEngine.rewardPlayer();

        gameEngine.generateLoot().ifPresent(item ->
                gameView.getCombatLog()
                        .appendText(
                                "Loot found: "
                                        + item.getName()
                                        + "\n"
                        )
        );

        if (levelUp) {
            gameView.getCombatLog()
                    .appendText("LEVEL UP!\n");
        }

        try {
            gameEngine.saveGame();

            gameView.getCombatLog()
                    .appendText("Game saved.\n");

        } catch (IOException e) {
            gameView.getCombatLog()
                    .appendText("Save failed.\n");
        }

        gameEngine.nextFloor();

        gameView.getCombatLog()
                .appendText("Moving to next floor...\n");

        gameView.getCombatLog()
                .appendText("----------------------\n");

        updateUI();
    }

    //Handles player defeat
    private void handleGameOver() {
        gameView.getCombatLog()
                .appendText("Game Over!\n");

        gameView.getAttackButton().setDisable(true);
        gameView.getPotionButton().setDisable(true);
        gameView.getSaveButton().setDisable(true);
    }

    //Handles potion usage
    private void handlePotion() {
        boolean usedPotion = gameEngine.useUsableItem();

        if (usedPotion) {
            gameView.getCombatLog()
                    .appendText("Potion used!\n");
        } else {
            gameView.getCombatLog()
                    .appendText("No potion available.\n");
        }

        updateUI();
    }

    //Handles game saving
    private void handleSave() {
        try {
            gameEngine.saveGame();

            gameView.getCombatLog()
                    .appendText("Game saved.\n");

        } catch (IOException e) {
            gameView.getCombatLog()
                    .appendText("Save failed.\n");
        }
    }

    //Handles game loading
    private void handleLoad() {
        try {
            gameEngine.loadGame();

            gameView.getCombatLog().clear();

            gameView.getCombatLog()
                    .appendText("Game loaded.\n");

            enableGameControls();
            updateUI();

        } catch (IOException e) {
            gameView.getCombatLog()
                    .appendText("Load failed.\n");
        }
    }

    //Enables the main game controls
    private void enableGameControls() {
        gameView.getAttackButton().setDisable(false);
        gameView.getPotionButton().setDisable(false);
        gameView.getSaveButton().setDisable(false);
    }

    //Updates the information shown in the GUI
    private void updateUI() {
        gameView.getFloorLabel().setText(
                "Floor: "
                        + gameEngine.getGameState()
                        .getCurrentFloor()
                        .getFloorNumber()
        );

        gameView.getPlayerHpLabel().setText(
                "Player HP: "
                        + gameEngine.getGameState()
                        .getPlayer()
                        .getStats()
                        .getHealth()
        );

        gameView.getLevelLabel().setText(
                "Level: "
                        + gameEngine.getGameState()
                        .getPlayer()
                        .getStats()
                        .getLevel()
        );

        gameView.getXpLabel().setText(
                "XP: "
                        + gameEngine.getGameState()
                        .getPlayer()
                        .getStats()
                        .getExperience()
        );

        gameView.getGoldLabel().setText(
                "Gold: "
                        + gameEngine.getGameState()
                        .getPlayer()
                        .getStats()
                        .getGold()
        );

        gameView.getPotionsLabel().setText(
                "Potions: "
                        + gameEngine.getGameState()
                        .getPlayer()
                        .getInventory()
                        .countPotions()
        );

        gameView.getEnemyLabel().setText(
                "Enemy: "
                        + gameEngine.getCurrentEnemy()
                        .getName()
        );

        gameView.getEnemyHpLabel().setText(
                "Enemy HP: "
                        + gameEngine.getCurrentEnemy()
                        .getStats()
                        .getHealth()
        );
    }
}
