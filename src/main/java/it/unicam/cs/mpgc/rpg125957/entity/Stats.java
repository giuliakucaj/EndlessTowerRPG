package it.unicam.cs.mpgc.rpg125957.entity;

public class Stats {

    //Statistiche principali del personaggio
    private int maxHealth;
    private int health;
    private int attack;
    private int defense;
    private int level;
    private int experience;
    private int gold;

    //Costruttore: crea statistiche iniziali
    public Stats(int maxHealth, int attack, int defense) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.level = 1;
        this.experience = 0;
        this.gold = 0;
    }
    // Getter per leggere le statistiche
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

    //Riduce la vita quando il personaggio subisce danno già calcolato
    public void takeDamage(int damage) {
        if (damage <= 0) {
            return;
        }

        health = Math.max(0, health - damage);
    }

    //Cura il personaggio senza superare la vita massima
    public void heal(int amount) {
        if(amount <= 0) {
            return;
        }
        health = Math.min(maxHealth, health + amount);
    }

    //Controllla se il personaggio è ancora vivo
    public boolean isAlive() {
        return health > 0;
    }

    //Aggiunge esperienza e restituisce true se il personaggio sale di livello
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

    //Aggiunge oro
    public void addGold(int amount){
        if(amount > 0) {
            gold += amount;
        }
    }

    //Migliora statistiche quando si sale di livello
    private void levelUp() {
        level++;
        maxHealth += 10;
        attack += 2;
        defense += 1;
        health = maxHealth;
    }
}
