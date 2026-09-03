package it.unicam.cs.mpgc.rpg125957.entity;

public class Stats {

    //Manages the character's main statistics
    private int maxHealth;
    private int health;
    private int attack;
    private int defense;
    private int level;
    private int experience;
    private int gold;

    //Creates the initial character statistics
    public Stats(int maxHealth, int attack, int defense) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.level = 1;
        this.experience = 0;
        this.gold = 0;
    }

    //Creates statistics from saved game data
    public Stats(
            int maxHealth,
            int health,
            int attack,
            int defense,
            int level,
            int experience,
            int gold
    ) {
        this.maxHealth = maxHealth;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.level = level;
        this.experience = experience;
        this.gold = gold;
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

    //Reduces health when the character takes damage
    public void takeDamage(int damage) {
        if (damage <= 0) {
            return;
        }

        health = Math.max(0, health - damage);
    }

    //Heals the character without exceeding maximum health
    public void heal(int amount) {
        if (amount <= 0) {
            return;
        }

        health = Math.min(
                maxHealth,
                health + amount
        );
    }

    //Checks whether the character is alive
    public boolean isAlive() {
        return health > 0;
    }

    //Adds experience and returns true if the character levels up
    public boolean addExperience(int amount) {
        if (amount <= 0) {
            return false;
        }

        experience += amount;

        if (experience >= level * 100) {
            levelUp();
            return true;
        }

        return false;
    }

    //Adds gold if the amount is positive
    public void addGold(int amount) {
        if (amount > 0) {
            gold += amount;
        }
    }

    //Spends gold only if enough is available
    public boolean spendGold(int amount) {

        if (amount <= 0 || gold < amount) {
            return false;
        }

        gold -= amount;
        return true;
    }

    //Improves the character's statistics after a level up
    private void levelUp() {
        level++;
        maxHealth += 10;
        attack += 2;
        defense += 1;
        health = maxHealth;
    }
}