package it.unicam.cs.mpgc.rpg125957.inventory;

import java.util.Optional;
import java.util.Random;

//Classe che genera oggetti casuali dopo una vittoria
public class LootGenerator {

    private final Random random;

    public LootGenerator() {
        this.random = new Random();
    }

    //Genera un loot casuale: per ora solo pozioni
    public Optional<Item> generateLoot() {
        int chance = random.nextInt(100);

        //50% di probabilità di trovare una pozione
        if (chance < 50) {
            return Optional.of(
                    new Potion(
                            "Small Potion",
                            "Restores 25 HP",
                            25
                    )
            );
        }

        return Optional.empty();
    }
}