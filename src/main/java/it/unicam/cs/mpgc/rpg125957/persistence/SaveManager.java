package it.unicam.cs.mpgc.rpg125957.persistence;

import java.io.IOException;

//Interface for managing game persistence
public interface SaveManager {

    //Saves the game data
    void save(SaveData saveData) throws IOException;

    //Load the game data
    SaveData load() throws IOException;
}
