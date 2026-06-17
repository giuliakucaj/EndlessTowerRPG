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

    //Informazioni sulla torre
    private final Label floorLabel;

    //Statistiche del giocatore
    private final Label playerHpLabel;
    private final Label levelLabel;
    private final Label xpLabel;
    private final Label goldLabel;

    //Informazioni sul nemico corrente
    private final Label enemyLabel;

    //Pulsanti di gioco
    private final Button attackButton;
    private final Button potionButton;

    //Pulsanti per la persistenza
    private final Button saveButton;
    private final Button loadButton;

    //Area che mostra gli eventi del combattimento
    private final TextArea combatLog;

    //Costruttore della GUI
    public GameView() {

        //VBox = disposizione verticale degli elementi
        setSpacing(10);

        //Margini interni della finestra
        setPadding(new Insets(20));

        //Informazioni della torre
        floorLabel = new Label("Floor: 1");

        //Statistiche del giocatore
        playerHpLabel = new Label("Player HP: 100");
        levelLabel = new Label("Level: 1");
        xpLabel = new Label("XP: 0");
        goldLabel = new Label("Gold: 0");

        //Informazioni sul nemico
        enemyLabel = new Label("Enemy: Unknown");

        //Azioni disponibili durante il combattimento
        attackButton = new Button("Attack");
        potionButton = new Button("Use Potion");

        //Azioni di persistenza
        saveButton = new Button("Save");
        loadButton = new Button("Load");

        //Contenitore orizzontale per i pulsanti di gioco
        HBox actionButtons = new HBox(10);

        actionButtons.getChildren().addAll(
                attackButton,
                potionButton
        );

        //Contenitore orizzontale per i pulsanti di salvataggio
        HBox persistenceButtons = new HBox(10);

        persistenceButtons.getChildren().addAll(
                saveButton,
                loadButton
        );

        //Area che mostra i messaggi del gioco
        combatLog = new TextArea();

        //L'utente non può modificare il log
        combatLog.setEditable(false);

        //Altezza iniziale del log
        combatLog.setPrefHeight(250);

        //Aggiunge tutti gli elementi alla finestra
        getChildren().addAll(
                floorLabel,

                playerHpLabel,
                levelLabel,
                xpLabel,
                goldLabel,

                enemyLabel,

                actionButtons,
                persistenceButtons,

                combatLog
        );
    }

    //Getter usati dal Main per aggiornare la GUI

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

    public Label getEnemyLabel() {
        return enemyLabel;
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
