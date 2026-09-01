package it.unicam.cs.mpgc.rpg125957;

import it.unicam.cs.mpgc.rpg125957.combat.CombatManager;
import it.unicam.cs.mpgc.rpg125957.combat.DamageCalculator;
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

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Player player = new Player(
                "Hero",
                new Stats(100, 25, 5)
        );

        FloorGenerator floorGenerator = new FloorGenerator();
        TowerManager towerManager = new TowerManager(floorGenerator);

        DamageCalculator damageCalculator = new DamageCalculator();
        CombatManager combatManager = new CombatManager(damageCalculator);
        TurnManager turnManager = new TurnManager(combatManager);

        LootGenerator lootGenerator = new LootGenerator();

        SaveManager saveManager = new JsonSaveManager();
        SaveDataMapper saveDataMapper = new SaveDataMapper();

        GameEngine gameEngine = new GameEngine(
                player,
                towerManager,
                turnManager,
                lootGenerator,
                saveManager,
                saveDataMapper
        );

        GameView gameView = new GameView();
        new GameController(gameEngine, gameView);

        Scene scene = new Scene(gameView, 600, 550);

        stage.setTitle("Endless Tower RPG");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}