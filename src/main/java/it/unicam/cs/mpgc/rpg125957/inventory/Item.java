package it.unicam.cs.mpgc.rpg125957.inventory;

//Classe base astratta per tutti gli oggetti del gioco
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
