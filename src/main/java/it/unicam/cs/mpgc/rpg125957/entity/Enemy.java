package it.unicam.cs.mpgc.rpg125957.entity;

//Represents an enemy
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

    //Type of enemy
    public EnemyType getType() {
        return type;
    }

    //Experience awarded for defeating the enemy
    public int getRewardExperience() {
        return rewardExperience;
    }

    //Gold gained by defeating the enemy
    public int getRewardGold() {
        return rewardGold;
    }
}
