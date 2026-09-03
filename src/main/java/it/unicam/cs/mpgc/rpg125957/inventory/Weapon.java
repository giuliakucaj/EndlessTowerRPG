package it.unicam.cs.mpgc.rpg125957.inventory;

//Represents an equippable item that increases defense
public class Weapon extends Item {

    private final int attackBonus;

    public Weapon(String name, String description, int attackBonus) {
        super(name, description);
        this.attackBonus = attackBonus;
    }

    //Attack bonus provided by the weapon
    public int getAttackBonus() {
        return attackBonus;
    }
}
