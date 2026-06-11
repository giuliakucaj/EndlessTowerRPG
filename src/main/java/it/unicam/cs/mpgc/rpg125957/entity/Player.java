package it.unicam.cs.mpgc.rpg125957.entity;

import it.unicam.cs.mpgc.rpg125957.inventory.Inventory;

//Classe che rappresenta il giocatore
public class Player extends Entity {

    private final Inventory inventory;

    public Player(String name, Stats stats) {
        super(name, stats);
        this.inventory = new Inventory();
    }

    //Restituisce l'inventario del giocatore
    public Inventory getInventory() {
        return inventory;
    }

}
