package it.unicam.cs.mpgc.rpg125957;

import it.unicam.cs.mpgc.rpg125957.entity.Enemy;
import it.unicam.cs.mpgc.rpg125957.tower.Room;
import it.unicam.cs.mpgc.rpg125957.tower.TowerManager;

public class Main {

    public static void main(String[] args) {
        TowerManager towerManager = new TowerManager();

        for (int i = 0; i < 10; i++) {
            Room room = towerManager.generateCurrentRoom();
            Enemy enemy = room.getEnemy();

            System.out.println("Floor: " + room.getFloorNumber());
            System.out.println("Enemy: " + enemy.getName());
            System.out.println("Type: " + enemy.getType());
            System.out.println("HP: " + enemy.getStats().getHealth());
            System.out.println("Attack: " + enemy.getStats().getAttack());
            System.out.println("Defense: " + enemy.getStats().getDefense());
            System.out.println("Boss room: " + room.isBossRoom());
            System.out.println("----------------------");

            towerManager.nextFloor();
        }
    }
}
