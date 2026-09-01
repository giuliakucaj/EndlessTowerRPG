package it.unicam.cs.mpgc.rpg125957.inventory;

import java.util.Optional;
import java.util.Random;

//Generates random loot after a victory
public class LootGenerator {

    private final Random random;

    public LootGenerator() {
        this.random = new Random();
    }

    //Generates random loot, currently only potions
    public Optional<Item> generateLoot() {
        int chance = random.nextInt(100);

        //50% chance to find a potion
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