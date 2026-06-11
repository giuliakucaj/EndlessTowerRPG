package it.unicam.cs.mpgc.rpg125957.entity;

//Classe base astratta per tutti i personaggi del gioco
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

    public boolean isAlive() {
        return stats.isAlive();
    }

    public void takeDamage(int damage) {
        stats.takeDamage(damage);
    }

    public int getAttackValue() {
        return stats.getAttack();
    }

    //Metodo per attaccare un altro personaggio
    public void attack(Entity target) {
        target.takeDamage(getAttackValue());
    }
}
