package it.unicam.cs.mpgc.rpg125957.tower;

import it.unicam.cs.mpgc.rpg125957.entity.Enemy;
import it.unicam.cs.mpgc.rpg125957.entity.EnemyType;
import it.unicam.cs.mpgc.rpg125957.entity.Stats;

import java.util.Random;

//Classe che gestisce la progressione della torre
public class TowerManager {

    //Piano corrente della torre
    private int currentFloor;

    //Generatore di casualità per i nemici
    private final Random random;

    public TowerManager() {
        this.currentFloor = 1;
        this.random = new Random();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    //Genera la stanza del piano attuale
    public Room generateCurrentRoom() {

        //Controlla se il piano contiene un boss
        boolean bossRoom = isBossFloor(currentFloor);

        //Genera boss oppure nemico normale
        Enemy enemy = bossRoom
                ? generateBoss(currentFloor)
                : generateEnemy(currentFloor);

        //Crea e restituisce la stanza
        return new Room(currentFloor, enemy, bossRoom);
    }

    //Fa avanzare al piano successivo
    public void nextFloor() {
        currentFloor++;
    }

    //Controlla se il piano è un boss floor
    private boolean isBossFloor(int floor) {
        return floor % 5 == 0;
    }

    //Genera un nemico normale
    private Enemy generateEnemy(int floor) {

        //Sceglie un tipo di nemico casuale
        EnemyType type = randomEnemyType();

        //Aumenta difficoltà col salire dei piani
        int health = 40 + floor * 5;
        int attack = 8 + floor * 2;
        int defense = 2 + floor;

        return new Enemy(
                type.name(),
                type,
                new Stats(health, attack, defense),
                20 + floor * 5,
                10 + floor * 3
        );
    }

    //Genera un boss ogni 5 piani
    private Enemy generateBoss(int floor) {

        return new Enemy(
                "Tower Guardian",
                EnemyType.BOSS,
                new Stats(
                        120 + floor * 10,
                        18 + floor * 3,
                        8 + floor
                ),
                100 + floor * 10,
                50 + floor * 5
        );
    }

    //Restituisce un tipo di nemico casuale
    private EnemyType randomEnemyType() {

        EnemyType[] types = {
                EnemyType.GOBLIN,
                EnemyType.SKELETON,
                EnemyType.ORC
        };

        return types[random.nextInt(types.length)];
    }
}
