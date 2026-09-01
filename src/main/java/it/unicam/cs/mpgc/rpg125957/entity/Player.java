package it.unicam.cs.mpgc.rpg125957.entity;

import it.unicam.cs.mpgc.rpg125957.inventory.Inventory;

//Represents the player character
public class Player extends Entity {

    private final Inventory inventory;

    public Player(String name, Stats stats) {
        super(name, stats);
        this.inventory = new Inventory();
    }

    //Returns the player's inventory
    public Inventory getInventory() {
        return inventory;
    }

}
