package com.eminekarabolat.models;


import com.eminekarabolat.databases.*;

public class DatabaseModel {

    private static DatabaseModel instance;

    private DatabaseModel() {

    }

    public static DatabaseModel getInstance(){
        if(instance==null){
            instance = new DatabaseModel();
        }
        return instance;
    }

    public final LeagueDB leagueDB = LeagueDB.getInstance();
    public final TeamDB teamDB = TeamDB.getInstance();
    public final PlayerDB playerDB = PlayerDB.getInstance();
    public final ManagerDB managerDB = ManagerDB.getInstance();
    public final MatchDB matchDB = MatchDB.getInstance();
    public final StadiumDB stadiumDB = StadiumDB.getInstance();
    public final StatsDB statsDB = StatsDB.getInstance();
    public final MatchStatsDB matchStatsDB = MatchStatsDB.getInstance();
}