package it.unicam.cs.mpgc.rpg125957;

import it.unicam.cs.mpgc.rpg125957.combat.TurnManager;
import it.unicam.cs.mpgc.rpg125957.controller.GameController;
import it.unicam.cs.mpgc.rpg125957.entity.Player;
import it.unicam.cs.mpgc.rpg125957.entity.Stats;
import it.unicam.cs.mpgc.rpg125957.inventory.LootGenerator;
import it.unicam.cs.mpgc.rpg125957.model.GameEngine;
import it.unicam.cs.mpgc.rpg125957.persistence.JsonSaveManager;
import it.unicam.cs.mpgc.rpg125957.persistence.SaveDataMapper;
import it.unicam.cs.mpgc.rpg125957.persistence.SaveManager;
import it.unicam.cs.mpgc.rpg125957.tower.FloorGenerator;
import it.unicam.cs.mpgc.rpg125957.tower.TowerManager;
import it.unicam.cs.mpgc.rpg125957.view.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Main class that starts and configures the application
public class Main extends Application {

    @Override
    public void start(Stage stage) {

        //Creates the initial player
        Player player = new Player(
                "Hero",
                new Stats(100, 25, 5)
        );

        //Creates the game dependencies
        FloorGenerator floorGenerator = new FloorGenerator();
        TowerManager towerManager = new TowerManager(floorGenerator);
        TurnManager turnManager = new TurnManager();
        LootGenerator lootGenerator = new LootGenerator();
        SaveManager saveManager = new JsonSaveManager();
        SaveDataMapper saveDataMapper = new SaveDataMapper();

        //Creates the game engine
        GameEngine gameEngine = new GameEngine(
                player,
                towerManager,
                turnManager,
                lootGenerator,
                saveManager,
                saveDataMapper
        );

        //Creates the graphical interface
        GameView gameView = new GameView();

        //Connects the view to the game engine
        new GameController(gameEngine, gameView);

        //Creates and shows the JavaFX scene
        Scene scene = new Scene(gameView, 600, 550);

        stage.setTitle("Endless Tower RPG");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}