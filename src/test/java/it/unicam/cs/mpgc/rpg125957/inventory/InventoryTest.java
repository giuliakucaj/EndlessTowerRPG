package it.unicam.cs.mpgc.rpg125957.inventory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Test della classe Inventory
class InventoryTest {

    //Verifica che un oggetto venga aggiunto correttamente
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

    //Verifica che un oggetto venga rimosso correttamente
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

    //Verifica il conteggio delle pozioni
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
