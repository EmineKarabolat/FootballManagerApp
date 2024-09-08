package com.eminekarabolat.databases;


import com.eminekarabolat.entities.Stats;
import com.eminekarabolat.utility.DatabaseManager;

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