package it.unicam.cs.mpgc.rpg125957.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//Implementazione JSON del sistema di persistenza
public class JsonSaveManager implements SaveManager {

    private static final String SAVE_FILE = "savegame.json";

    private final Gson gson;

    //Costruttore del manager JSON
    public JsonSaveManager() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    //Salva i dati della partita su file JSON
    @Override
    public void save(SaveData saveData) throws IOException {
        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            gson.toJson(saveData, writer);
        }
    }

    //Carica i dati della partita da file JSON
    @Override
    public SaveData load() throws IOException {
        try (FileReader reader = new FileReader(SAVE_FILE)) {
            return gson.fromJson(reader, SaveData.class);
        }
    }
}
