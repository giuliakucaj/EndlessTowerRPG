package it.unicam.cs.mpgc.rpg125957.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

//Represents the graphical view of a game character
public class CharacterView extends VBox {

    private final Label nameLabel;
    private final Label levelLabel;

    private final ProgressBar healthBar;
    private final Label healthLabel;

    private final ProgressBar experienceBar;
    private final Label experienceLabel;

    private final ImageView spriteView;

    public CharacterView() {

        setSpacing(5);
        setAlignment(Pos.CENTER);

        nameLabel = new Label("Character");
        nameLabel.getStyleClass().add("character-name");

        levelLabel = new Label("Lv. 1");
        levelLabel.getStyleClass().add("character-level");

        healthBar = new ProgressBar(1.0);
        healthBar.setPrefWidth(175);
        healthBar.getStyleClass().add("health-bar");

        healthLabel = new Label("100 / 100");
        healthLabel.getStyleClass().add("health-label");

        experienceBar = new ProgressBar(0);
        experienceBar.setPrefWidth(175);
        experienceBar.getStyleClass().add("experience-bar");

        experienceLabel = new Label("XP 0");
        experienceLabel.getStyleClass().add("experience-label");

        spriteView = new ImageView();
        spriteView.setFitWidth(145);
        spriteView.setFitHeight(145);
        spriteView.setPreserveRatio(true);

        getChildren().addAll(
                nameLabel,
                levelLabel,
                healthBar,
                healthLabel,
                experienceBar,
                experienceLabel,
                spriteView
        );
    }

    //Updates the character's general information
    public void updateCharacter(
            String name,
            int level,
            int health,
            int maxHealth
    ) {

        nameLabel.setText(name.toUpperCase());
        levelLabel.setText("Lv. " + level);

        double healthProgress =
                maxHealth > 0
                        ? (double) health / maxHealth
                        : 0;

        healthBar.setProgress(healthProgress);

        healthLabel.setText(
                health + " / " + maxHealth
        );
    }

    //Updates the experience information
    public void updateExperience(
            int experience,
            int requiredExperience
    ) {

        double experienceProgress =
                requiredExperience > 0
                        ? (double) experience / requiredExperience
                        : 0;

        experienceBar.setProgress(
                Math.min(experienceProgress, 1.0)
        );

        experienceLabel.setText(
                "XP " + experience + " / " + requiredExperience
        );

        experienceBar.setVisible(true);
        experienceLabel.setVisible(true);

        experienceBar.setManaged(true);
        experienceLabel.setManaged(true);
    }

    //Hides experience information
    public void hideExperience() {

        experienceBar.setVisible(false);
        experienceLabel.setVisible(false);

        experienceBar.setManaged(false);
        experienceLabel.setManaged(false);
    }

    //Sets the character sprite
    public void setSprite(Image image) {
        spriteView.setImage(image);
    }

    public ImageView getSpriteView() {
        return spriteView;
    }
}