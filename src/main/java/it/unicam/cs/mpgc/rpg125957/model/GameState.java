package it.unicam.cs.mpgc.rpg125957.model;

import it.unicam.cs.mpgc.rpg125957.entity.Player;
import it.unicam.cs.mpgc.rpg125957.tower.Floor;

//Classe che rappresenta lo stato corrente della partita
public class GameState {

    private final Player player;
    private Floor currentFloor;

    public GameState(Player player, Floor currentFloor) {
        this.player = player;
        this.currentFloor = currentFloor;
    }

    //Restituisce il giocatore
    public Player getPlayer() {
        return player;
    }

    //Restituisce il piano corrente
    public Floor getCurrentFloor() {
        return currentFloor;
    }

    //Aggiorna il piano corrente
    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }
}
