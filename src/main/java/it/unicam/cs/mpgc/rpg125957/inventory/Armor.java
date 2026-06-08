package it.unicam.cs.mpgc.rpg125957.inventory;

//Oggetto equipaggiabile che aumenta la difesa
public class Armor extends Item {

    private final int defenseBonus;

    public Armor(String name, String description, int defenseBonus) {
        super(name, description);
        this.defenseBonus = defenseBonus;
    }

    //Bonus di difesa fornita dall'armatura
    public int getDefenseBonus() {
        return defenseBonus;
    }
}
