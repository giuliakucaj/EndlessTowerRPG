package it.unicam.cs.mpgc.rpg125957.persistence;

//Classe dati usata per salvare/caricare la partita in JSON
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

    //Costruttore vuoto richiesto da Gson
    public SaveData() {
    }

    //Costruttore completo
    public SaveData(String playerName,
                    int maxHealth,
                    int health,
                    int attack,
                    int defense,
                    int level,
                    int experience,
                    int gold,
                    int currentFloor) {

        this.playerName = playerName;
        this.maxHealth = maxHealth;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.level = level;
        this.experience = experience;
        this.gold = gold;
        this.currentFloor = currentFloor;
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
}
