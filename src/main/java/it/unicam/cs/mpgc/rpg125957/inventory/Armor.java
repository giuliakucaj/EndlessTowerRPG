package it.unicam.cs.mpgc.rpg125957.inventory;

//Represents an equippable item that increases defense
public class Armor extends Item {

    private final int defenseBonus;

    public Armor(String name, String description, int defenseBonus) {
        super(name, description);
        this.defenseBonus = defenseBonus;
    }

    //Defense bonus provided by the armor
    public int getDefenseBonus() {
        return defenseBonus;
    }
}
