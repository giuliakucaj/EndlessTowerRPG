package it.unicam.cs.mpgc.rpg125957.entity;

//Abstract class for all entities in the game
public abstract class Entity {

    private final String name;
    private final Stats stats;

    public Entity(String name, Stats stats) {
        this.name = name;
        this.stats = stats;
    }
    public String getName() {
        return name;
    }

    public Stats getStats() {
        return stats;
    }

    //Checks whether the entity is alive
    public boolean isAlive() {
        return stats.isAlive();
    }

    //Applies damage to the entity
    public void takeDamage(int damage) {
        stats.takeDamage(damage);
    }

    //Returns the attack value of the entity
    public int getAttackValue() {
        return stats.getAttack();
    }

    //Attacks another entity
    public void attack(Entity target) {
        target.takeDamage(getAttackValue());
    }
}
