package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.powerups.HealthPowerUp;
import com.codecool.snake.entities.powerups.LengthPowerUp;
import com.codecool.snake.entities.powerups.CombinedPowerUp;

import com.codecool.snake.entities.weapons.Beam;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;


public class SnakeHead extends GameEntity implements Interactable {
    private static final float turnRate = 2;
    private Snake snake;

    public SnakeHead(Snake snake, Vec2d position) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeHead"));
        setPosition(position);
    }
    public Snake getSnake(){
        return snake;
    }

    public void updateRotation(SnakeControl turnDirection, float speed) {
        double headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            headRotation = headRotation - turnRate;
        }
        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            headRotation = headRotation + turnRate;
        }

        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof Enemy) {
            System.out.println(getMessage());
            snake.changeLives(((Enemy) entity).getDamage());
        }
        if (entity instanceof LengthPowerUp) {
            System.out.println(getMessage());
            snake.addPart(4);
        }
        if (entity instanceof HealthPowerUp) {
            System.out.println(getMessage());
            if (snake.getLives() < 4) {
                snake.changeLives(1);
            }
        }
        if (entity instanceof CombinedPowerUp) {
            System.out.println(getMessage());
            if (snake.getLives() < 4) {
                snake.changeLives(1);
            }
            snake.addPart(4);
        }
//        if (entity instanceof Beam) {
//            if (this.equals(entity)) {
//                snake.changeLives(-1);
////                entity.changeLives(-1);
//
//            }
//        }

        if (entity instanceof SnakeHead) {
            try {
                snake.gameOverPopUp();
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public String getMessage() {
        return "IMMA SNAEK HED! SPITTIN' MAH WENOM! SPITJU-SPITJU!";
    }
}
