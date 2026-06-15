package it.unicam.cs.mpgc.rpg125957.persistence;

import java.io.IOException;

//Interfaccia per la gestione della persistenza
public interface SaveManager {

    //Salva i dati della partita
    void save(SaveData saveData) throws IOException;

    //Carica i dati della partita
    SaveData load() throws IOException;
}
