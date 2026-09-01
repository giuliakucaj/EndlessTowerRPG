package it.unicam.cs.mpgc.rpg125957.model;

import it.unicam.cs.mpgc.rpg125957.entity.Player;
import it.unicam.cs.mpgc.rpg125957.tower.Floor;

//Represents the current state of the game
public class GameState {

    private final Player player;
    private Floor currentFloor;

    public GameState(Player player, Floor currentFloor) {
        this.player = player;
        this.currentFloor = currentFloor;
    }

    public Player getPlayer() {
        return player;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }
}
