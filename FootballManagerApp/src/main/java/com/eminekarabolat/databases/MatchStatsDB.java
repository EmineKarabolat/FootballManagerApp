package com.eminekarabolat.databases;



import com.eminekarabolat.entities.MatchStats;
import com.eminekarabolat.utility.DatabaseManager;

import java.util.Optional;

public class MatchStatsDB extends DatabaseManager<MatchStats> {

    private static MatchStatsDB instance;

    private MatchStatsDB(){

    }

    public static MatchStatsDB getInstance(){
        if(instance==null){
            instance=new MatchStatsDB();
        }
        return instance;
    }

    public Optional<MatchStats> findbyMatchId(int matchId){
        return veriListesi.stream()
                .filter(m->m.getMatchId()==matchId).findFirst();
    }


}