package com.sacak.databases;


import com.sacak.entities.Match;
import com.sacak.utility.DatabaseManager;

public class MatchDB extends DatabaseManager<Match> {
    private static MatchDB instance;

    private MatchDB(){

    }
    public static MatchDB getInstance(){
        if(instance==null){
            instance=new MatchDB();
        }
        return instance;
    }

}
