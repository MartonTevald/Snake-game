package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;

import java.util.Collections;
import java.util.List;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public Display display;
    public Game game;
    public Snake snake;
    public Snake snake2;


    private GameLoop gameLoop;
    private Resources resources;


    public static Globals getInstance() {
        if (instance == null) instance = new Globals();
        return instance;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("snake_headBLUE.png"));
        resources.addImage("SnakeBody", new Image("snake_bodyBLUE.png"));
        resources.addImage("Bouncer_enemy", new Image("worm_enemy.png"));
        resources.addImage("Enemy2", new Image("enemy_created_first.png"));
        resources.addImage("PowerUpBerry", new Image("powerup_berry.png"));
        resources.addImage("PowerUpNut", new Image("powerup_nut.png"));
        resources.addImage("PowerUpSpeed", new Image("powerup_speed.png"));
        resources.addImage("Beam", new Image("laserc.gif"));

    }

    public Image getImage(String name) {
        return resources.getImage(name);
    }

    public void startGame() {
        gameLoop.start();
    }

    public void stopGame() {
        gameLoop.stop();
    }

    public static List<GameEntity> gameObjects;

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}
