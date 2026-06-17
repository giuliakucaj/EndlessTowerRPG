package it.unicam.cs.mpgc.rpg125957.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Classe che gestisce gli oggetti posseduti dal giocatore
public class Inventory {

    private final List<Item> items;

    //Costruttore dell'inventario
    public Inventory() {
        this.items = new ArrayList<>();
    }

    //Aggiunge un oggetto all'inventario
    public void addItem(Item item) {
        if (item != null) {
            items.add(item);
        }
    }

    //Rimuove un oggetto dall'inventario
    public void removeItem(Item item) {
        items.remove(item);
    }

    //Restituisce una lista non modificabile degli oggetti
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    //Controlla se l'inventario contiene almeno una pozione
    public boolean hasPotion() {
        return items.stream()
                .anyMatch(item -> item instanceof Potion);
    }

    //Restituisce la prima pozione disponibile, se presente
    public Potion getFirstPotion() {
        return items.stream()
                .filter(item -> item instanceof Potion)
                .map(item -> (Potion) item)
                .findFirst()
                .orElse(null);
    }

    //Conta quante pozioni sono presenti nell'inventario
    public long countPotions() {
        return items.stream()
                .filter(item -> item instanceof Potion)
                .count();
    }
}