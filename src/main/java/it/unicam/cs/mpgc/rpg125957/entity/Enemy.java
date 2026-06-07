package it.unicam.cs.mpgc.rpg125957.entity;

//Classe che rappresenta un nemico
public class Enemy extends Entity {

    private final int rewardExperience;
    private final int rewardGold;

    public Enemy(String name, Stats stats, int rewardExperience, int rewardGold) {
        super(name, stats);
        this.rewardExperience = rewardExperience;
        this.rewardGold = rewardGold;
    }

    //Esperienza ottenuta sconfiggendo il nemico
    public int getRewardExperience() {
        return rewardExperience;
    }

    //Oro ottenuto sconfiggendo il nemico
    public int getRewardGold() {
        return rewardGold;
    }

    //Metodo per attaccare il giocatore
    public void attack(Entity target) {
        target.takeDamage(getAttackValue());
    }
}
