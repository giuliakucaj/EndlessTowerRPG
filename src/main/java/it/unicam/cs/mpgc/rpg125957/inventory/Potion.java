package it.unicam.cs.mpgc.rpg125957.inventory;

import it.unicam.cs.mpgc.rpg125957.entity.Player;

//Oggetto consumabile che cura il giocatore
public class Potion extends Item {

    private final int healAmount;

    public Potion(String name, String description, int healAmount) {
        super(name, description);
        this.healAmount = healAmount;
    }

    //Quantità di vita recuperata
    public int getHealAmount() {
        return healAmount;
    }

    //Usa la posizione sul giocatore
    public void use(Player player) {
        player.getStats().heal(healAmount);
    }
}
