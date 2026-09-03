package it.unicam.cs.mpgc.rpg125957.entity;

import it.unicam.cs.mpgc.rpg125957.inventory.Inventory;

//Represents the player character
public class Player extends Entity {

    private final Inventory inventory;

    public Player(
            String name,
            Stats stats
    ) {
        super(name, stats);
        this.inventory = new Inventory();
    }

    //Returns the player's inventory
    public Inventory getInventory() {
        return inventory;
    }

    //Heals the player
    public void heal(int amount) {
        getStats().heal(amount);
    }

    //Adds experience and returns true if the player levels up
    public boolean addExperience(int amount) {
        return getStats().addExperience(amount);
    }

    //Adds gold to the player
    public void addGold(int amount) {
        getStats().addGold(amount);
    }

    //Spends gold if the player has enough
    public boolean spendGold(int amount) {
        return getStats().spendGold(amount);
    }
}