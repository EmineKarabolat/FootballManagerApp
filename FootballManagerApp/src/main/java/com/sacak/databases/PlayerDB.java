package com.sacak.databases;


import com.sacak.entities.Player;
import com.sacak.utility.DatabaseManager;

public class PlayerDB extends DatabaseManager<Player> {

    private static PlayerDB instance;

    private PlayerDB() {

    }

    public static PlayerDB getInstance(){
        if(instance==null){
            instance=new PlayerDB();
        }
        return instance;
    }
}
