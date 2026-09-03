package it.unicam.cs.mpgc.rpg125957.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

//Represents the game's GUI.
//Contains only graphical components.
//The game logic remains in GameEngine.
public class GameView extends VBox {

    private final Label titleLabel;
    private final Label floorLabel;

    private final BattleView battleView;

    //Temporary labels kept for compatibility with GameController
    private final Label playerHpLabel;
    private final Label levelLabel;
    private final Label xpLabel;

    private final Label goldLabel;
    private final Label potionsLabel;

    //Temporary labels kept for compatibility with GameController
    private final Label enemyLabel;
    private final Label enemyHpLabel;

    private final Button attackButton;
    private final Button potionButton;
    private final Button buyPotionButton;
    private final Button saveButton;
    private final Button loadButton;

    private final TextArea combatLog;

    public GameView() {

        setSpacing(15);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_CENTER);

        //Title
        titleLabel = new Label("ENDLESS TOWER");

        //Current floor
        floorLabel = new Label("Floor: 1");

        //Battle area
        battleView = new BattleView();

        //Temporary compatibility labels
        playerHpLabel = new Label();
        levelLabel = new Label();
        xpLabel = new Label();

        enemyLabel = new Label();
        enemyHpLabel = new Label();

        playerHpLabel.setVisible(false);
        levelLabel.setVisible(false);
        xpLabel.setVisible(false);
        enemyLabel.setVisible(false);
        enemyHpLabel.setVisible(false);

        playerHpLabel.setManaged(false);
        levelLabel.setManaged(false);
        xpLabel.setManaged(false);
        enemyLabel.setManaged(false);
        enemyHpLabel.setManaged(false);

        //Player information
        goldLabel = new Label("Gold: 0");
        potionsLabel = new Label("Potions: 0");

        HBox playerInfo = new HBox(30);
        playerInfo.setAlignment(Pos.CENTER);
        playerInfo.getChildren().addAll(
                goldLabel,
                potionsLabel
        );

        //Game actions
        attackButton = new Button("⚔ Attack");
        potionButton = new Button("🧪 Potion");
        buyPotionButton = new Button("🧪 Buy Potion");
        saveButton = new Button("💾 Save");
        loadButton = new Button("📂 Load");

        HBox actionButtons = new HBox(15);
        actionButtons.setAlignment(Pos.CENTER);

        actionButtons.getChildren().addAll(
                attackButton,
                potionButton,
                buyPotionButton,
                saveButton,
                loadButton
        );

        //Combat log
        combatLog = new TextArea();
        combatLog.setEditable(false);
        combatLog.setPrefHeight(150);
        combatLog.setPrefWidth(500);

        getChildren().addAll(
                titleLabel,
                floorLabel,

                battleView,

                playerInfo,
                actionButtons,

                combatLog,

                //Hidden compatibility components
                playerHpLabel,
                levelLabel,
                xpLabel,
                enemyLabel,
                enemyHpLabel
        );
    }

    public Label getFloorLabel() {
        return floorLabel;
    }

    public BattleView getBattleView() {
        return battleView;
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

    public Button getBuyPotionButton() {
        return buyPotionButton;
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