package com.sacak.utility;


import com.sacak.models.DatabaseModel;


import java.io.*;
import java.util.*;


public class FileIOWriter {

    private static final String
            DIRECTORY = "D:\\Java15Full\\FootballManagerApp\\FootballManagerApp\\src\\main\\java\\com\\sacak\\FileIO";

    private static final File ENTITY_FILE = new File(DIRECTORY, "entities.bin");
    private static DatabaseModel databaseModel = DatabaseModel.getInstance();



    static {
        File directoryFile = new File(DIRECTORY);
        if (!directoryFile.exists()) {
            directoryFile.mkdirs();
        }
    }

    public static List<ILoad> getCurrentEntityList(){
        List<ILoad> entityList = new ArrayList<>();
        entityList.addAll(databaseModel.playerDB.findAll());
        entityList.addAll(databaseModel.leagueDB.findAll());
        entityList.addAll(databaseModel.teamDB.findAll());
        entityList.addAll(databaseModel.managerDB.findAll());
        entityList.addAll(databaseModel.stadiumDB.findAll());
        entityList.addAll(databaseModel.statsDB.findAll());
        entityList.addAll(databaseModel.matchDB.findAll());

        return entityList;
    }

public static void addEntity(ILoad entity){
    List<ILoad> entities = getCurrentEntityList();
    entities.add(entity);
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ENTITY_FILE))) {
            for(ILoad e : entities){
                oos.writeObject(e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Dosya kaydedilirken bir hata olustu...",e);
        }
}

    public static void saveAllEntities() {
        List<ILoad> entityList = getCurrentEntityList();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ENTITY_FILE))) {
            for (ILoad entity : entityList) {
                oos.writeObject(entity);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save entities", e);
        }
    }

}


