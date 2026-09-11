package it.unicam.cs.mpgc.rpg125957.controller;

import it.unicam.cs.mpgc.rpg125957.combat.CombatResult;
import it.unicam.cs.mpgc.rpg125957.entity.Enemy;
import it.unicam.cs.mpgc.rpg125957.entity.Player;
import it.unicam.cs.mpgc.rpg125957.inventory.Potion;
import it.unicam.cs.mpgc.rpg125957.model.GameEngine;
import it.unicam.cs.mpgc.rpg125957.view.CharacterView;
import it.unicam.cs.mpgc.rpg125957.view.GameView;
import it.unicam.cs.mpgc.rpg125957.view.SpriteManager;

import java.io.IOException;

//Coordinates the interaction between the GUI and the game engine
public class GameController {

    private final GameEngine gameEngine;
    private final GameView gameView;

    public GameController(
            GameEngine gameEngine,
            GameView gameView
    ) {
        this.gameEngine = gameEngine;
        this.gameView = gameView;

        configureActions();
        updateUI();
    }

    private void configureActions() {

        gameView.getAttackButton()
                .setOnAction(event -> handleAttack());

        gameView.getPotionButton()
                .setOnAction(event -> handlePotion());

        gameView.getBuyPotionButton()
                .setOnAction(event -> handleBuyPotion());

        gameView.getSaveButton()
                .setOnAction(event -> handleSave());

        gameView.getLoadButton()
                .setOnAction(event -> handleLoad());
    }

    private void handleAttack() {

        disableGameControls();

        CombatResult playerResult =
                gameEngine.playerAttack();

        gameView.getCombatLog()
                .appendText(
                        playerResult.getMessage() + "\n"
                );

        gameView.getBattleView()
                .animatePlayerAttack(
                        playerResult.getDamage(),
                        this::updateUI,
                        () -> {

                            if (!gameEngine
                                    .getCurrentEnemy()
                                    .isAlive()) {

                                gameView.getBattleView()
                                        .animateEnemyDefeat(
                                                this::handleVictory
                                        );

                                return;
                            }

                            handleEnemyAttack();
                        }
                );
    }

    private void handleEnemyAttack() {

        CombatResult enemyResult =
                gameEngine.enemyAttack();

        gameView.getCombatLog()
                .appendText(
                        enemyResult.getMessage() + "\n"
                );

        gameView.getBattleView()
                .animateEnemyAttack(
                        enemyResult.getDamage(),
                        this::updateUI,
                        () -> {

                            if (!gameEngine
                                    .getGameState()
                                    .getPlayer()
                                    .isAlive()) {

                                handleGameOver();
                                return;
                            }

                            enableGameControls();
                        }
                );
    }

    private void handleVictory() {

        gameView.getCombatLog()
                .appendText("Victory!\n");

        boolean levelUp =
                gameEngine.rewardPlayer();

        gameEngine.generateLoot()
                .ifPresent(item -> {

                    gameView.getCombatLog()
                            .appendText(
                                    "Loot found: "
                                            + item.getName()
                                            + "\n"
                            );

                    if (item instanceof Potion) {
                        gameView.getBattleView()
                                .showPotionLoot();
                    }
                });

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

        int newFloor =
                gameEngine.getGameState()
                        .getCurrentFloor()
                        .getFloorNumber();

        gameView.getCombatLog()
                .appendText(
                        "Moving to next floor...\n"
                );

        gameView.getCombatLog()
                .appendText(
                        "----------------------\n"
                );

        gameView.getBattleView()
                .hideDamage();

        gameView.getBattleView()
                .showFloorTransition(
                        newFloor,
                        () -> {
                            updateUI();
                            enableGameControls();
                        }
                );
    }

    private void handleGameOver() {

        gameView.getCombatLog()
                .appendText("Game Over!\n");

        gameView.getBattleView()
                .showGameOver();

        gameView.getAttackButton()
                .setDisable(true);

        gameView.getPotionButton()
                .setDisable(true);

        gameView.getBuyPotionButton()
                .setDisable(true);

        gameView.getSaveButton()
                .setDisable(true);

        gameView.getLoadButton()
                .setDisable(false);
    }

    private void handlePotion() {

        boolean usedPotion =
                gameEngine.useUsableItem();

        if (usedPotion) {

            gameView.getCombatLog()
                    .appendText(
                            "Potion used!\n"
                    );

            gameView.getBattleView()
                    .showPotionUsed();

        } else {

            gameView.getCombatLog()
                    .appendText(
                            "No potion available.\n"
                    );
        }

        updateUI();
    }

    private void handleBuyPotion() {

        boolean boughtPotion =
                gameEngine.buyPotion();

        if (boughtPotion) {

            gameView.getCombatLog()
                    .appendText(
                            "Potion purchased for 20 gold!\n"
                    );

            gameView.getBattleView()
                    .showPotionLoot();

        } else {

            gameView.getCombatLog()
                    .appendText(
                            "Not enough gold!\n"
                    );

            gameView.getBattleView()
                    .showNotEnoughGold();
        }

        updateUI();
    }

    private void handleSave() {

        try {

            gameEngine.saveGame();

            gameView.getCombatLog()
                    .appendText(
                            "Game saved.\n"
                    );

        } catch (IOException e) {

            gameView.getCombatLog()
                    .appendText(
                            "Save failed.\n"
                    );
        }
    }

    private void handleLoad() {

        try {

            gameEngine.loadGame();

            gameView.getCombatLog()
                    .clear();

            gameView.getCombatLog()
                    .appendText(
                            "Game loaded.\n"
                    );

            gameView.getBattleView()
                    .hideDamage();

            gameView.getBattleView()
                    .hideGameOver();

            enableGameControls();
            updateUI();

        } catch (IOException e) {

            gameView.getCombatLog()
                    .appendText(
                            "Load failed.\n"
                    );
        }
    }

    private void disableGameControls() {

        gameView.getAttackButton()
                .setDisable(true);

        gameView.getPotionButton()
                .setDisable(true);

        gameView.getBuyPotionButton()
                .setDisable(true);

        gameView.getSaveButton()
                .setDisable(true);

        gameView.getLoadButton()
                .setDisable(true);
    }

    private void enableGameControls() {

        gameView.getAttackButton()
                .setDisable(false);

        gameView.getPotionButton()
                .setDisable(false);

        gameView.getBuyPotionButton()
                .setDisable(false);

        gameView.getSaveButton()
                .setDisable(false);

        gameView.getLoadButton()
                .setDisable(false);
    }

    private void updateUI() {

        Player player =
                gameEngine.getGameState()
                        .getPlayer();

        Enemy enemy =
                gameEngine.getCurrentEnemy();

        CharacterView playerView =
                gameView.getBattleView()
                        .getPlayerView();

        CharacterView enemyView =
                gameView.getBattleView()
                        .getEnemyView();

        gameView.getFloorLabel()
                .setText(
                        "Floor: "
                                + gameEngine
                                .getGameState()
                                .getCurrentFloor()
                                .getFloorNumber()
                );

        playerView.updateCharacter(
                player.getName(),
                player.getStats().getLevel(),
                player.getStats().getHealth(),
                player.getStats().getMaxHealth()
        );

        int requiredExperience =
                player.getStats().getRequiredExperience();

        playerView.updateExperience(
                player.getStats().getExperience(),
                requiredExperience
        );

        playerView.setSprite(
                SpriteManager.getPlayerSprite()
        );

        enemyView.updateCharacter(
                enemy.getName(),
                enemy.getStats().getLevel(),
                enemy.getStats().getHealth(),
                enemy.getStats().getMaxHealth()
        );

        enemyView.hideExperience();

        enemyView.setSprite(
                SpriteManager.getEnemySprite(
                        enemy.getType()
                )
        );

        gameView.getGoldLabel()
                .setText(
                        "Gold: "
                                + player.getStats()
                                .getGold()
                );

        gameView.getPotionsLabel()
                .setText(
                        "Potions: "
                                + player.getInventory().
                                countItems(Potion.class)
                );
    }
}