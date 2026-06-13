package it.unicam.cs.mpgc.rpg125957.tower;

import it.unicam.cs.mpgc.rpg125957.entity.Enemy;

//Classe che rappresenta una stanza della torre
public class Room {

    private final int floorNumber;
    private final Enemy enemy;
    private final boolean bossRoom;

    public Room(int floorNumber, Enemy enemy, boolean bossRoom) {
        this.floorNumber = floorNumber;
        this.enemy = enemy;
        this.bossRoom = bossRoom;
    }

    //Numero del piano
    public int getFloorNumber() {
        return floorNumber;
    }

    //Nemico presente nella stanza
    public Enemy getEnemy() {
        return enemy;
    }

    //Indica se la stanza contiene un boss
    public boolean isBossRoom() {
        return bossRoom;
    }


}
