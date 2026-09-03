package it.unicam.cs.mpgc.rpg125957.view;

import it.unicam.cs.mpgc.rpg125957.entity.EnemyType;
import javafx.scene.image.Image;

import java.util.Objects;

//Provides the graphical sprites used by the game
public class SpriteManager {

    private static final Image HERO =
            loadImage("/images/hero.png");

    private static final Image GOBLIN =
            loadImage("/images/goblin.png");

    private static final Image SKELETON =
            loadImage("/images/skeleton.png");

    private static final Image ORC =
            loadImage("/images/orc.png");

    private static final Image BOSS =
            loadImage("/images/boss.png");

    private SpriteManager() {
        //Utility class
    }

    //Returns the player's sprite
    public static Image getPlayerSprite() {
        return HERO;
    }

    //Returns the sprite associated with an enemy type
    public static Image getEnemySprite(EnemyType enemyType) {

        return switch (enemyType) {
            case GOBLIN -> GOBLIN;
            case SKELETON -> SKELETON;
            case ORC -> ORC;
            case BOSS -> BOSS;
        };
    }

    //Loads an image from the resources folder
    private static Image loadImage(String path) {

        return new Image(
                Objects.requireNonNull(
                        SpriteManager.class.getResourceAsStream(path),
                        "Sprite not found: " + path
                )
        );
    }
}
