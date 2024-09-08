package com.sacak.entities;


import com.sacak.databases.MatchStatsDB;

public class MatchStats extends BaseEntity{


    private int matchId;
    private int homeTeam_Passes;
    private int awayTeam_Passes;

    private int homeTeam_Shots;
    private int homeTeam_Saves;
    private int awayTeam_Shots;
    private int awayTeam_Saves;

    private int homeTeam_fouls;
    private int awayTeam_fouls;

    private int homeTeamBallPercantage;
    private int awayTeamBallPercantage;


    public MatchStats(MatchStatsDB matchStatsDB, int matchId) {
        super(matchStatsDB);
        this.matchId=matchId;
        matchStatsDB.save(this);
//        FileIOWriter.writeMatchStatsToBin(databaseModel);
    }

    public int getHomeTeamBallPercantage() {
        return homeTeamBallPercantage;
    }

    public void setHomeTeamBallPercantage(int homeTeamBallPercantage) {
        this.homeTeamBallPercantage = homeTeamBallPercantage;
    }

    public int getAwayTeamBallPercantage() {
        return awayTeamBallPercantage;
    }

    public void setAwayTeamBallPercantage(int awayTeamBallPercantage) {
        this.awayTeamBallPercantage = awayTeamBallPercantage;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getHomeTeam_Passes() {
        return homeTeam_Passes;
    }

    public void setHomeTeam_Passes(int homeTeam_Passes) {
        this.homeTeam_Passes = homeTeam_Passes;
    }

    public int getAwayTeam_Passes() {
        return awayTeam_Passes;
    }

    public void setAwayTeam_Passes(int awayTeam_Passes) {
        this.awayTeam_Passes = awayTeam_Passes;
    }

    public int getHomeTeam_Shots() {
        return homeTeam_Shots;
    }

    public void setHomeTeam_Shots(int homeTeam_Shots) {
        this.homeTeam_Shots = homeTeam_Shots;
    }

    public int getHomeTeam_Saves() {
        return homeTeam_Saves;
    }

    public void setHomeTeam_Saves(int homeTeam_Saves) {
        this.homeTeam_Saves = homeTeam_Saves;
    }

    public int getAwayTeam_Shots() {
        return awayTeam_Shots;
    }

    public void setAwayTeam_Shots(int awayTeam_Shots) {
        this.awayTeam_Shots = awayTeam_Shots;
    }

    public int getAwayTeam_Saves() {
        return awayTeam_Saves;
    }

    public void setAwayTeam_Saves(int awayTeam_Saves) {
        this.awayTeam_Saves = awayTeam_Saves;
    }

    public int getHomeTeam_fouls() {
        return homeTeam_fouls;
    }

    public void setHomeTeam_fouls(int homeTeam_fouls) {
        this.homeTeam_fouls = homeTeam_fouls;
    }

    public int getAwayTeam_fouls() {
        return awayTeam_fouls;
    }

    public void setAwayTeam_fouls(int awayTeam_fouls) {
        this.awayTeam_fouls = awayTeam_fouls;
    }
}
