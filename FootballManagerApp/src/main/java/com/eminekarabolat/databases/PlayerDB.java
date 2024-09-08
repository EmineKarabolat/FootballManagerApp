package com.eminekarabolat.databases;


import com.eminekarabolat.entities.Player;
import com.eminekarabolat.utility.DatabaseManager;

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