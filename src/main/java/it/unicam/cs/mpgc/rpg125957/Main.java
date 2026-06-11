package it.unicam.cs.mpgc.rpg125957;

import it.unicam.cs.mpgc.rpg125957.combat.CombatResult;
import it.unicam.cs.mpgc.rpg125957.combat.TurnManager;
import it.unicam.cs.mpgc.rpg125957.entity.Enemy;
import it.unicam.cs.mpgc.rpg125957.entity.EnemyType;
import it.unicam.cs.mpgc.rpg125957.entity.Player;
import it.unicam.cs.mpgc.rpg125957.entity.Stats;

public class Main {

    public static void main(String[] args) {
        Player player = new Player("Hero", new Stats(100, 20, 5));
        Enemy goblin = new Enemy(
                "Goblin",
                EnemyType.GOBLIN,
                new Stats(50, 12, 2),
                30,
                10
        );

        TurnManager turnManager = new TurnManager();

        while (!turnManager.isCombatOver(player, goblin)) {
            CombatResult playerResult = turnManager.playerTurn(player, goblin);
            System.out.println(playerResult.getMessage());
            System.out.println("Goblin HP: " + goblin.getStats().getHealth());

            if (goblin.isAlive()) {
                CombatResult enemyResult = turnManager.enemyTurn(goblin, player);
                System.out.println(enemyResult.getMessage());
                System.out.println("Player HP: " + player.getStats().getHealth());
            }

            System.out.println("--------------------");
        }

        if (player.isAlive()) {
            player.getStats().addExperience(goblin.getRewardExperience());
            player.getStats().addGold(goblin.getRewardGold());

            System.out.println("Victory!");
            System.out.println("XP: " + player.getStats().getExperience());
            System.out.println("Gold: " + player.getStats().getGold());
        } else {
            System.out.println("Game Over!");
        }
    }
}
