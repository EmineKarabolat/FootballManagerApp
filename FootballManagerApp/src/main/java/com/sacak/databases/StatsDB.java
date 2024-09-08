package com.sacak.databases;


import com.sacak.entities.Stats;
import com.sacak.utility.DatabaseManager;

public class StatsDB extends DatabaseManager<Stats> {

    private static StatsDB instance;

    private StatsDB(){

    }

    public static StatsDB getInstance(){
        if(instance==null){
            instance=new StatsDB();
        }
        return instance;
    }


}
