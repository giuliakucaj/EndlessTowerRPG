package it.unicam.cs.mpgc.rpg125957.entity;

//Classe che rappresenta un nemico
public class Enemy extends Entity {

    private final EnemyType type;
    private final int rewardExperience;
    private final int rewardGold;

    public Enemy(String name, EnemyType type,Stats stats, int rewardExperience, int rewardGold) {
        super(name, stats);
        this.type = type;
        this.rewardExperience = rewardExperience;
        this.rewardGold = rewardGold;
    }

    //Tipo del nemico
    public EnemyType getType() {
        return type;
    }

    //Esperienza ottenuta sconfiggendo il nemico
    public int getRewardExperience() {
        return rewardExperience;
    }

    //Oro ottenuto sconfiggendo il nemico
    public int getRewardGold() {
        return rewardGold;
    }
}
