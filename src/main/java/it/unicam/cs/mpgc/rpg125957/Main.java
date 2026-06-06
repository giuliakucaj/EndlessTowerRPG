package it.unicam.cs.mpgc.rpg125957;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("Endless Tower RPG - JavaFX OK");
        Scene scene = new Scene(label, 400, 200);

        stage.setTitle("Endless Tower RPG");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
