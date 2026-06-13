package it.unicam.cs.mpgc.rpg125957.model;

import it.unicam.cs.mpgc.rpg125957.entity.Player;
import it.unicam.cs.mpgc.rpg125957.tower.Room;

//Classe che rappresenta lo stato corrente della partita
public class GameState {

    private final Player player;
    private Room currentRoom;

    public GameState(Player player, Room currentRoom) {
        this.player = player;
        this.currentRoom = currentRoom;
    }

    //Restituisce il giocatore
    public Player getPlayer() {
        return player;
    }

    //Restituisce la stanza corrente
    public Room getCurrentRoom() {
        return currentRoom;
    }

    //Aggiorna la stanza corrente
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
