package it.unicam.cs.mpgc.rpg125957.view;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.Objects;

//Represents the combat area of the game
public class BattleView extends StackPane {

    private final CharacterView playerView;
    private final CharacterView enemyView;

    private final Label playerDamageLabel;
    private final Label enemyDamageLabel;
    private final Label floorTransitionLabel;
    private final Label gameOverLabel;
    private final Label messageLabel;

    private final ImageView backgroundView;

    private final ImageView potionView;
    private final Label potionEffectLabel;

    public BattleView() {

        setPrefSize(900, 420);
        setMinHeight(420);
        setMaxHeight(420);

        Image backgroundImage =
                new Image(
                        Objects.requireNonNull(
                                getClass().getResourceAsStream(
                                        "/images/dungeon.png"
                                )
                        )
                );

        backgroundView =
                new ImageView(backgroundImage);

        backgroundView.setFitWidth(900);
        backgroundView.setFitHeight(420);
        backgroundView.setPreserveRatio(false);

        playerView = new CharacterView();
        enemyView = new CharacterView();

        HBox charactersBox =
                new HBox(240);

        charactersBox.setAlignment(
                Pos.BOTTOM_CENTER
        );

        charactersBox.setPadding(
                new Insets(
                        25,
                        60,
                        25,
                        60
                )
        );

        charactersBox.getChildren().addAll(
                playerView,
                enemyView
        );

        playerDamageLabel =
                createDamageLabel();

        StackPane.setAlignment(
                playerDamageLabel,
                Pos.CENTER_LEFT
        );

        StackPane.setMargin(
                playerDamageLabel,
                new Insets(
                        0,
                        0,
                        0,
                        250
                )
        );

        enemyDamageLabel =
                createDamageLabel();

        StackPane.setAlignment(
                enemyDamageLabel,
                Pos.CENTER_RIGHT
        );

        StackPane.setMargin(
                enemyDamageLabel,
                new Insets(
                        0,
                        250,
                        0,
                        0
                )
        );

        floorTransitionLabel =
                new Label("");

        floorTransitionLabel.getStyleClass()
                .add("floor-transition-label");

        floorTransitionLabel.setVisible(false);

        StackPane.setAlignment(
                floorTransitionLabel,
                Pos.CENTER
        );

        gameOverLabel =
                new Label("GAME OVER!");

        gameOverLabel.getStyleClass()
                .add("game-over-label");

        gameOverLabel.setVisible(false);

        StackPane.setAlignment(
                gameOverLabel,
                Pos.CENTER
        );

        messageLabel =
                new Label("");

        messageLabel.getStyleClass()
                .add("screen-message-label");

        messageLabel.setVisible(false);

        StackPane.setAlignment(
                messageLabel,
                Pos.CENTER
        );

        Image potionImage =
                new Image(
                        Objects.requireNonNull(
                                getClass().getResourceAsStream(
                                        "/images/potion.png"
                                )
                        )
                );

        potionView =
                new ImageView(potionImage);

        potionView.setFitWidth(48);
        potionView.setFitHeight(48);
        potionView.setPreserveRatio(true);
        potionView.setVisible(false);

        StackPane.setAlignment(
                potionView,
                Pos.CENTER_LEFT
        );

        StackPane.setMargin(
                potionView,
                new Insets(
                        0,
                        0,
                        40,
                        275
                )
        );

        potionEffectLabel =
                new Label("");

        potionEffectLabel.getStyleClass()
                .add("potion-effect-label");

        potionEffectLabel.setVisible(false);

        StackPane.setAlignment(
                potionEffectLabel,
                Pos.CENTER_LEFT
        );

        StackPane.setMargin(
                potionEffectLabel,
                new Insets(
                        0,
                        0,
                        105,
                        245
                )
        );

        getChildren().addAll(
                backgroundView,
                charactersBox,
                playerDamageLabel,
                enemyDamageLabel,
                floorTransitionLabel,
                gameOverLabel,
                potionView,
                potionEffectLabel,
                messageLabel
        );

        getStyleClass().add(
                "battle-view"
        );
    }

    private Label createDamageLabel() {

        Label label =
                new Label("");

        label.getStyleClass()
                .add("damage-label");

        label.setVisible(false);

        return label;
    }

    public CharacterView getPlayerView() {
        return playerView;
    }

    public CharacterView getEnemyView() {
        return enemyView;
    }

    public void animatePlayerAttack(
            int damage,
            Runnable onImpact,
            Runnable onFinished
    ) {

        ImageView playerSprite =
                playerView.getSpriteView();

        playerSprite.setTranslateX(0);

        TranslateTransition moveForward =
                new TranslateTransition(
                        Duration.millis(180),
                        playerSprite
                );

        moveForward.setToX(130);

        moveForward.setOnFinished(event -> {

            animateHit(
                    enemyView.getSpriteView()
            );

            showDamage(
                    enemyDamageLabel,
                    damage
            );

            if (onImpact != null) {
                onImpact.run();
            }
        });

        PauseTransition pause =
                new PauseTransition(
                        Duration.millis(100)
                );

        TranslateTransition moveBack =
                new TranslateTransition(
                        Duration.millis(180),
                        playerSprite
                );

        moveBack.setToX(0);

        SequentialTransition animation =
                new SequentialTransition(
                        moveForward,
                        pause,
                        moveBack
                );

        animation.setOnFinished(event -> {

            if (onFinished != null) {
                onFinished.run();
            }
        });

        animation.play();
    }

    public void animateEnemyAttack(
            int damage,
            Runnable onImpact,
            Runnable onFinished
    ) {

        ImageView enemySprite =
                enemyView.getSpriteView();

        enemySprite.setTranslateX(0);

        TranslateTransition moveForward =
                new TranslateTransition(
                        Duration.millis(180),
                        enemySprite
                );

        moveForward.setToX(-130);

        moveForward.setOnFinished(event -> {

            animateHit(
                    playerView.getSpriteView()
            );

            showDamage(
                    playerDamageLabel,
                    damage
            );

            if (onImpact != null) {
                onImpact.run();
            }
        });

        PauseTransition pause =
                new PauseTransition(
                        Duration.millis(100)
                );

        TranslateTransition moveBack =
                new TranslateTransition(
                        Duration.millis(180),
                        enemySprite
                );

        moveBack.setToX(0);

        SequentialTransition animation =
                new SequentialTransition(
                        moveForward,
                        pause,
                        moveBack
                );

        animation.setOnFinished(event -> {

            if (onFinished != null) {
                onFinished.run();
            }
        });

        animation.play();
    }

    public void animateEnemyDefeat(
            Runnable onFinished
    ) {

        ImageView enemySprite =
                enemyView.getSpriteView();

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(500),
                        enemySprite
                );

        fade.setFromValue(1.0);
        fade.setToValue(0.0);

        fade.setOnFinished(event -> {

            if (onFinished != null) {
                onFinished.run();
            }

            enemySprite.setOpacity(1.0);
        });

        fade.play();
    }

    public void showFloorTransition(
            int floorNumber,
            Runnable onFinished
    ) {

        floorTransitionLabel.setText(
                "FLOOR " + floorNumber
        );

        floorTransitionLabel.setOpacity(0.0);
        floorTransitionLabel.setVisible(true);

        FadeTransition fadeIn =
                new FadeTransition(
                        Duration.millis(350),
                        floorTransitionLabel
                );

        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        PauseTransition pause =
                new PauseTransition(
                        Duration.millis(700)
                );

        FadeTransition fadeOut =
                new FadeTransition(
                        Duration.millis(350),
                        floorTransitionLabel
                );

        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        SequentialTransition transition =
                new SequentialTransition(
                        fadeIn,
                        pause,
                        fadeOut
                );

        transition.setOnFinished(event -> {

            floorTransitionLabel.setVisible(false);
            floorTransitionLabel.setOpacity(1.0);

            if (onFinished != null) {
                onFinished.run();
            }
        });

        transition.play();
    }

    public void showGameOver() {

        gameOverLabel.setOpacity(0.0);
        gameOverLabel.setVisible(true);

        FadeTransition fadeIn =
                new FadeTransition(
                        Duration.millis(600),
                        gameOverLabel
                );

        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        fadeIn.play();
    }

    public void hideGameOver() {
        gameOverLabel.setVisible(false);
        gameOverLabel.setOpacity(1.0);
    }

    public void showNotEnoughGold() {
        showScreenMessage("NOT ENOUGH GOLD!");
    }

    private void showScreenMessage(
            String message
    ) {

        messageLabel.setText(message);
        messageLabel.setOpacity(0.0);
        messageLabel.setVisible(true);

        FadeTransition fadeIn =
                new FadeTransition(
                        Duration.millis(200),
                        messageLabel
                );

        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        PauseTransition pause =
                new PauseTransition(
                        Duration.millis(800)
                );

        FadeTransition fadeOut =
                new FadeTransition(
                        Duration.millis(300),
                        messageLabel
                );

        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        SequentialTransition animation =
                new SequentialTransition(
                        fadeIn,
                        pause,
                        fadeOut
                );

        animation.setOnFinished(event -> {
            messageLabel.setVisible(false);
            messageLabel.setOpacity(1.0);
        });

        animation.play();
    }

    public void showPotionLoot() {
        animatePotionEffect("+ POTION");
    }

    public void showPotionUsed() {
        animatePotionEffect("+ HP");
    }

    private void animatePotionEffect(
            String message
    ) {

        potionView.setOpacity(1.0);
        potionView.setTranslateY(0);
        potionView.setVisible(true);

        potionEffectLabel.setText(message);
        potionEffectLabel.setOpacity(1.0);
        potionEffectLabel.setTranslateY(0);
        potionEffectLabel.setVisible(true);

        TranslateTransition potionMove =
                new TranslateTransition(
                        Duration.millis(800),
                        potionView
                );

        potionMove.setByY(-40);

        FadeTransition potionFade =
                new FadeTransition(
                        Duration.millis(800),
                        potionView
                );

        potionFade.setFromValue(1.0);
        potionFade.setToValue(0.0);

        TranslateTransition labelMove =
                new TranslateTransition(
                        Duration.millis(800),
                        potionEffectLabel
                );

        labelMove.setByY(-40);

        FadeTransition labelFade =
                new FadeTransition(
                        Duration.millis(800),
                        potionEffectLabel
                );

        labelFade.setFromValue(1.0);
        labelFade.setToValue(0.0);

        ParallelTransition animation =
                new ParallelTransition(
                        potionMove,
                        potionFade,
                        labelMove,
                        labelFade
                );

        animation.setOnFinished(event -> {

            potionView.setVisible(false);
            potionView.setOpacity(1.0);
            potionView.setTranslateY(0);

            potionEffectLabel.setVisible(false);
            potionEffectLabel.setOpacity(1.0);
            potionEffectLabel.setTranslateY(0);
        });

        animation.play();
    }

    private void animateHit(
            ImageView target
    ) {

        TranslateTransition shakeRight =
                new TranslateTransition(
                        Duration.millis(55),
                        target
                );

        shakeRight.setByX(10);

        TranslateTransition shakeLeft =
                new TranslateTransition(
                        Duration.millis(55),
                        target
                );

        shakeLeft.setByX(-20);

        TranslateTransition shakeCenter =
                new TranslateTransition(
                        Duration.millis(55),
                        target
                );

        shakeCenter.setByX(10);

        SequentialTransition shake =
                new SequentialTransition(
                        shakeRight,
                        shakeLeft,
                        shakeCenter
                );

        shake.play();
    }

    private void showDamage(
            Label label,
            int damage
    ) {

        label.setText("-" + damage);

        label.setOpacity(1.0);
        label.setTranslateY(0);
        label.setVisible(true);

        TranslateTransition moveUp =
                new TranslateTransition(
                        Duration.millis(700),
                        label
                );

        moveUp.setByY(-45);

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(700),
                        label
                );

        fade.setFromValue(1.0);
        fade.setToValue(0.0);

        ParallelTransition animation =
                new ParallelTransition(
                        moveUp,
                        fade
                );

        animation.setOnFinished(event -> {

            label.setVisible(false);
            label.setOpacity(1.0);
            label.setTranslateY(0);
        });

        animation.play();
    }

    public void hideDamage() {

        resetDamageLabel(
                playerDamageLabel
        );

        resetDamageLabel(
                enemyDamageLabel
        );
    }

    private void resetDamageLabel(
            Label label
    ) {

        label.setVisible(false);
        label.setOpacity(1.0);
        label.setTranslateY(0);
    }
}