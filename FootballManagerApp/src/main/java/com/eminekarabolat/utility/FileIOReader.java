package com.eminekarabolat.utility;


import com.eminekarabolat.models.DatabaseModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIOReader {

    private static final String
            DIRECTORY = "C:\\java_15\\Football_Manager_App\\FootballManagerApp\\FootballManagerApp\\src\\main\\java\\com\\eminekarabolat\\FileIO";
    private static final File ENTITY_FILE = new File(DIRECTORY, "entities.bin");

    static {
        File directoryFile = new File(DIRECTORY);
        if (!directoryFile.exists()) {
            directoryFile.mkdirs();
        }
    }

    public static boolean loadData(DatabaseModel databaseModel) {
        if (!ENTITY_FILE.exists()) {
            DataGenerator.generateData(databaseModel);
            System.out.println("###########################################");
            System.out.println("Kayıtlı veri bulunamadı, generator çalıştı.");
            System.out.println("###########################################");
            return true;
        }

        List<ILoad> entities = readEntitiesFromFile();

        for(ILoad entity : entities){
            entity.saveToDatabase(databaseModel);
        }

        System.out.println("Loaded " + entities.size() + " entities.");
        return true;
    }

    private static List<ILoad> readEntitiesFromFile() {
        List<ILoad> entities = new ArrayList<>();
        if (ENTITY_FILE.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ENTITY_FILE))) {
                while (true) {
                    try {
                        ILoad entity = (ILoad) ois.readObject();
                        entities.add(entity);
                    } catch (EOFException e) {
                        break; // End of file reached
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException("Class not found while reading entity data", e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to read entity data", e);
            }
        }
        return entities;
    }
}