package it.unicam.cs.mpgc.rpg125957.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//JSON implementation of the persistence system
public class JsonSaveManager implements SaveManager {

    private static final String SAVE_FILE = "savegame.json";

    private final Gson gson;

    //Creates a JSON save manager
    public JsonSaveManager() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    //Save the game data to a JSON file
    @Override
    public void save(SaveData saveData) throws IOException {
        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            gson.toJson(saveData, writer);
        }
    }

    //Loads the game data form a JSON file
    @Override
    public SaveData load() throws IOException {
        try (FileReader reader = new FileReader(SAVE_FILE)) {
            return gson.fromJson(reader, SaveData.class);
        }
    }
}
