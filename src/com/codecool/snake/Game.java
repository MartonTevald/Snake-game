package com.codecool.snake;

import com.codecool.snake.entities.enemies.Bouncer_enemy;
import com.codecool.snake.entities.enemies.Homecoming_missile;
import com.codecool.snake.entities.powerups.HealthPowerUp;
import com.codecool.snake.entities.powerups.LengthPowerUp;
import com.codecool.snake.entities.powerups.SpeedPowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHealth;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.Random;


public class Game extends Pane {
    private Snake snake1 = null;
    private Snake snake2 = null;
    private GameTimer gameTimer = new GameTimer();
    public boolean alreadyExecuted = false;
    public SnakeHealth SnakeHealth1 = new SnakeHealth();


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();


        init();
    }

    public void init() {
        spawnSnake(250, 500);
        spawnSnake2(750, 500);
        spawnEnemies(4);
        spawnPowerUps(2);

        GameLoop gameLoop = new GameLoop(snake1, snake2);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake(int x, int y) {
        snake1 = new Snake(new Vec2d(x, y), "Player1");
    }

    private void spawnSnake2(int x, int y) {
        snake2 = new Snake(new Vec2d(x, y), "Player2");
    }

    private void spawnEnemies(int numberOfEnemies) {
        for (int i = 0; i < numberOfEnemies; ++i) new Bouncer_enemy();
        for (int i = 0; i < numberOfEnemies; ++i) new Homecoming_missile();

    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for (int i = 0; i < numberOfPowerUps; ++i) new LengthPowerUp();
        for (int i = 0; i < numberOfPowerUps; ++i) new HealthPowerUp();
        for (int i = 0; i < numberOfPowerUps; ++i) new HealthPowerUp();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }

    public void spawnPowerUps() {
        Random rand = new Random();
        int n = rand.nextInt(500);
        if (n == 33) {
            new LengthPowerUp();
        } else if (n == 66) {
            new HealthPowerUp();
        } else if (n == 99) {
            new SpeedPowerUp();
        }
    }
}

