package it.unicam.cs.mpgc.rpg125957.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Manages the player's inventory
public class Inventory {

    private final List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    //Adds an item to the inventory
    public void addItem(Item item) {
        if (item != null) {
            items.add(item);
        }
    }

    //Removes an item from the inventory
    public void removeItem(Item item) {
        items.remove(item);
    }

    //Returns an unmodifiable list of items
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    //Checks whether the inventory contains at least one potion
    public boolean hasPotion() {
        return items.stream()
                .anyMatch(item -> item instanceof Potion);
    }

    //Returns the first available potion, if any
    public Potion getFirstPotion() {
        return items.stream()
                .filter(item -> item instanceof Potion)
                .map(item -> (Potion) item)
                .findFirst()
                .orElse(null);
    }

    //Counts the number of potions in the inventory
    public long countPotions() {
        return items.stream()
                .filter(item -> item instanceof Potion)
                .count();
    }
}