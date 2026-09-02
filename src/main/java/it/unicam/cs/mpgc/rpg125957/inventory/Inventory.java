package it.unicam.cs.mpgc.rpg125957.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    //Returns the first usable item, if any
    public Optional<UsableItem> getFirstUsableItem() {
        return items.stream()
                .filter(item -> item instanceof UsableItem)
                .map(item -> (UsableItem) item)
                .findFirst();
    }

    //Counts the number of potions in the inventory
    public long countPotions() {
        return items.stream()
                .filter(item -> item instanceof Potion)
                .count();
    }
}