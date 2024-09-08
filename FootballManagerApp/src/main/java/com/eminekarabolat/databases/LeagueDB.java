package com.eminekarabolat.databases;


import com.eminekarabolat.entities.League;
import com.eminekarabolat.utility.DatabaseManager;

import java.util.List;

public class LeagueDB extends DatabaseManager<League> {

    private static LeagueDB instance;
    private LeagueDB(){

    }
    public static LeagueDB getInstance(){
        if(instance==null){
            instance=new LeagueDB();
        }
        return instance;
    }


    public List<League> getLeagues() {
        return findAll();
    }


}