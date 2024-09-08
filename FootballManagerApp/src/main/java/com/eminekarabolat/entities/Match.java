package com.eminekarabolat.entities;


import com.eminekarabolat.databases.MatchDB;
import com.eminekarabolat.models.DatabaseModel;
import com.eminekarabolat.utility.FileIOWriter;
import com.eminekarabolat.utility.ILoad;

import java.time.LocalDate;

public class Match extends BaseEntity implements ILoad {

    static Integer matchIdCounter = 0; // ???? silinebilir
    private Integer homeTeamId;
    private Integer awayTeamId;
    private int homeTeamScore;
    private int awayTeamScore;
    private LocalDate matchDate;
    private Integer leagueID;



    private boolean isPlayed; // mac oynandi mi ? yeni eklendi.

    public Match(MatchDB matchDB) {
        super(matchDB);
        this.leagueID=1; //todo: geçici çözüm
        matchDB.save(this);
//        FileIOWriter.writeMatchToBin(databaseModel);
        isPlayed=false;

    }

    public Match(MatchDB matchDB,Integer homeTeamId, Integer awayTeamId, int homeTeamScore, int awayTeamScore, Integer leagueID) {
        super(matchDB);
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.leagueID = leagueID;
        matchDB.save(this);
        FileIOWriter.addEntity(this);
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }



    public static Integer getMatchIdCounter() {
        return matchIdCounter;
    }

    public static void setMatchIdCounter(Integer matchIdCounter) {
        Match.matchIdCounter = matchIdCounter;
    }

    public Integer getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(Integer leagueID) {
        this.leagueID = leagueID;
    }


    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public Integer getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Integer homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Integer getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Integer awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }


    @Override
    public String toString() {
        return "Match{" +
                "id=" + getId() +
                ", homeTeamId=" + getHomeTeamId() +
                ", awayTeamId=" + getAwayTeamId() +
                ", matchDate=" + getMatchDate() +
                '}';
    }

    @Override
    public void saveToDatabase(DatabaseModel databaseModel) {
        databaseModel.matchDB.findAll().add(this);
    }
}