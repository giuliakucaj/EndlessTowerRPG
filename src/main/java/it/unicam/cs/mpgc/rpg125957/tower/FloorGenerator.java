package it.unicam.cs.mpgc.rpg125957.tower;

import it.unicam.cs.mpgc.rpg125957.entity.Enemy;
import it.unicam.cs.mpgc.rpg125957.entity.EnemyType;
import it.unicam.cs.mpgc.rpg125957.entity.Stats;

import java.util.Random;

//Generates tower floors and enemies
public class FloorGenerator {

    private final Random random;

    public FloorGenerator() {
        this.random = new Random();
    }

    //Generates a floor based on its number
    public Floor generate(int floorNumber) {
        boolean bossFloor = isBossFloor(floorNumber);

        Enemy enemy = bossFloor
                ? generateBoss(floorNumber)
                : generateEnemy(floorNumber);

        return new Floor(floorNumber, enemy, bossFloor);
    }

    //Checks whether the floor is a boss floor
    private boolean isBossFloor(int floorNumber) {
        return floorNumber % 5 == 0;
    }

    //Generates a regular enemy
    private Enemy generateEnemy(int floorNumber) {
        EnemyType type = randomEnemyType();

        int health = 40 + floorNumber * 5;
        int attack = 8 + floorNumber * 2;
        int defense = 2 + floorNumber;

        return new Enemy(
                type.name(),
                type,
                new Stats(health, attack, defense),
                20 + floorNumber * 5,
                10 + floorNumber * 3
        );
    }

    //Generates a boss enemy
    private Enemy generateBoss(int floorNumber) {
        return new Enemy(
                "Tower Guardian",
                EnemyType.BOSS,
                new Stats(
                        120 + floorNumber * 10,
                        18 + floorNumber * 3,
                        8 + floorNumber
                ),
                100 + floorNumber * 10,
                50 + floorNumber * 5
        );
    }

    //Returns a random regular enemy type
    private EnemyType randomEnemyType() {
        EnemyType[] types = {
                EnemyType.GOBLIN,
                EnemyType.SKELETON,
                EnemyType.ORC
        };

        return types[random.nextInt(types.length)];
    }
}