package it.unicam.cs.mpgc.rpg125957.inventory;

//Represents a generic item in the game
public class Item {

    private final String name;
    private final String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
