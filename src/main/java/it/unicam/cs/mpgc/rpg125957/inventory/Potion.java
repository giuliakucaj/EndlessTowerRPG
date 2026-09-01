package it.unicam.cs.mpgc.rpg125957.inventory;

import it.unicam.cs.mpgc.rpg125957.entity.Player;

//represents a consumable item that heals the player
public class Potion extends Item {

    private final int healAmount;

    public Potion(String name, String description, int healAmount) {
        super(name, description);
        this.healAmount = healAmount;
    }

    //Amount of health restored
    public int getHealAmount() {
        return healAmount;
    }

    //Uses the potion on the player
    public void use(Player player) {
        player.getStats().heal(healAmount);
    }
}
