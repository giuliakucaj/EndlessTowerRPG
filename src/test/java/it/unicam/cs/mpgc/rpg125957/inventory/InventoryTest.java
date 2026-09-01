package it.unicam.cs.mpgc.rpg125957.inventory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Tests the inventory class
class InventoryTest {

    //Verifies that an item is added correctly
    @Test
    void shouldAddPotionToInventory() {

        Inventory inventory = new Inventory();

        Potion potion = new Potion(
                "Small Potion",
                "Restores 25 HP",
                25
        );

        inventory.addItem(potion);

        assertEquals(1, inventory.getItems().size());
        assertTrue(inventory.hasPotion());
    }

    //Verifies that an item is removed correctly
    @Test
    void shouldRemovePotionFromInventory() {

        Inventory inventory = new Inventory();

        Potion potion = new Potion(
                "Small Potion",
                "Restores 25 HP",
                25
        );

        inventory.addItem(potion);
        inventory.removeItem(potion);

        assertEquals(0, inventory.getItems().size());
        assertFalse(inventory.hasPotion());
    }

    //Verifies the potion count
    @Test
    void shouldCountPotionsCorrectly() {

        Inventory inventory = new Inventory();

        inventory.addItem(
                new Potion(
                        "Small Potion",
                        "Restores 25 HP",
                        25
                )
        );

        inventory.addItem(
                new Potion(
                        "Medium Potion",
                        "Restores 50 HP",
                        50
                )
        );

        assertEquals(2, inventory.countPotions());
    }
}
