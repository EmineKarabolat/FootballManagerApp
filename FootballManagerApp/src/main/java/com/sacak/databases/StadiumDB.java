package com.sacak.databases;


import com.sacak.entities.Stadium;
import com.sacak.utility.DatabaseManager;

public class StadiumDB extends DatabaseManager<Stadium> {

    private static StadiumDB instance;

    private StadiumDB(){

    }

    public static StadiumDB getInstance(){
        if(instance==null){
            instance=new StadiumDB();
        }
        return instance;
    }
}
