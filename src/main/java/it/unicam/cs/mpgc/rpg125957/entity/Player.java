package it.unicam.cs.mpgc.rpg125957.entity;

//Classe che rappresenta il giocatore
public class Player extends Entity {

    public Player(String name, Stats stats) {
        super(name, stats);
    }

    //Metodo per attaccare un nemico
    public void attack(Entity target) {
        target.takeDamage(getAttackValue());
    }
}
