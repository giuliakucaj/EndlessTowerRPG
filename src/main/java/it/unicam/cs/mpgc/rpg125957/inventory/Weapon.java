package it.unicam.cs.mpgc.rpg125957.inventory;

//Oggetto equipaggiabile che aumenta l'attacco
public class Weapon extends Item {

    private final int attackBonus;

    public Weapon(String name, String description, int attackBonus) {
        super(name, description);
        this.attackBonus = attackBonus;
    }

    //Bonus di attaco fornito dall'arma
    public int getAttackBonus() {
        return attackBonus;
    }
}
