package it.unicam.cs.mpgc.rpg125957.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

//Classe che rappresenta la GUI del gioco.
//Contiene solamente componenti grafici.
//La logica del gioco rimane nel GameEngine.
public class GameView extends VBox {

    private final Label floorLabel;

    private final Label playerTitleLabel;
    private final Label playerHpLabel;
    private final Label levelLabel;
    private final Label xpLabel;
    private final Label goldLabel;
    private final Label potionsLabel;

    private final Label enemyTitleLabel;
    private final Label enemyLabel;
    private final Label enemyHpLabel;

    private final Button attackButton;
    private final Button potionButton;
    private final Button saveButton;
    private final Button loadButton;

    private final TextArea combatLog;

    public GameView() {
        setSpacing(10);
        setPadding(new Insets(20));

        floorLabel = new Label("Floor: 1");

        playerTitleLabel = new Label("=== PLAYER ===");
        playerHpLabel = new Label("Player HP: 100");
        levelLabel = new Label("Level: 1");
        xpLabel = new Label("XP: 0");
        goldLabel = new Label("Gold: 0");
        potionsLabel = new Label("Potions: 0");

        enemyTitleLabel = new Label("=== ENEMY ===");
        enemyLabel = new Label("Enemy: Unknown");
        enemyHpLabel = new Label("Enemy HP: 0");

        attackButton = new Button("Attack");
        potionButton = new Button("Use Potion");

        saveButton = new Button("Save");
        loadButton = new Button("Load");

        HBox actionButtons = new HBox(10);
        actionButtons.getChildren().addAll(
                attackButton,
                potionButton
        );

        HBox persistenceButtons = new HBox(10);
        persistenceButtons.getChildren().addAll(
                saveButton,
                loadButton
        );

        combatLog = new TextArea();
        combatLog.setEditable(false);
        combatLog.setPrefHeight(250);

        getChildren().addAll(
                floorLabel,

                playerTitleLabel,
                playerHpLabel,
                levelLabel,
                xpLabel,
                goldLabel,
                potionsLabel,

                enemyTitleLabel,
                enemyLabel,
                enemyHpLabel,

                actionButtons,
                persistenceButtons,

                combatLog
        );
    }

    public Label getFloorLabel() {
        return floorLabel;
    }

    public Label getPlayerHpLabel() {
        return playerHpLabel;
    }

    public Label getLevelLabel() {
        return levelLabel;
    }

    public Label getXpLabel() {
        return xpLabel;
    }

    public Label getGoldLabel() {
        return goldLabel;
    }

    public Label getPotionsLabel() {
        return potionsLabel;
    }

    public Label getEnemyLabel() {
        return enemyLabel;
    }

    public Label getEnemyHpLabel() {
        return enemyHpLabel;
    }

    public Button getAttackButton() {
        return attackButton;
    }

    public Button getPotionButton() {
        return potionButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getLoadButton() {
        return loadButton;
    }

    public TextArea getCombatLog() {
        return combatLog;
    }
}