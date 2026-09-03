package it.unicam.cs.mpgc.rpg125957.persistence;

//Contains the data needed to save and load a game
public class SaveData {

    private String playerName;
    private int maxHealth;
    private int health;
    private int attack;
    private int defense;
    private int level;
    private int experience;
    private int gold;
    private int currentFloor;
    private int potionCount;

    public SaveData() {
    }

    public SaveData(
            String playerName,
            int maxHealth,
            int health,
            int attack,
            int defense,
            int level,
            int experience,
            int gold,
            int currentFloor,
            int potionCount
    ) {
        this.playerName = playerName;
        this.maxHealth = maxHealth;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.level = level;
        this.experience = experience;
        this.gold = gold;
        this.currentFloor = currentFloor;
        this.potionCount = potionCount;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getGold() {
        return gold;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getPotionCount() {
        return potionCount;
    }
}