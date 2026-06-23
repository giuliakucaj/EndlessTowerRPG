package it.unicam.cs.mpgc.rpg125957.tower;

import it.unicam.cs.mpgc.rpg125957.entity.Enemy;

//Classe che rappresenta un piano della torre
public class Floor {

    private final int floorNumber;
    private final Enemy enemy;
    private final boolean bossFloor;

    public Floor(int floorNumber, Enemy enemy, boolean bossFloor) {
        this.floorNumber = floorNumber;
        this.enemy = enemy;
        this.bossFloor = bossFloor;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public boolean isBossFloor() {
        return bossFloor;
    }
}