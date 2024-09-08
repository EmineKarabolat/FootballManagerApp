package com.sacak.entities;


import com.sacak.databases.StatsDB;
import com.sacak.models.DatabaseModel;
import com.sacak.utility.FileIOWriter;
import com.sacak.utility.ILoad;

public class Stats extends BaseEntity implements ILoad {

    private Integer leagueID;
    private Integer teamID;
    private int gamesPlayed; //Oynanan mac sayisi
    private int totalWins;
    private int totalDraws; //Toplam beraberlik
    private int totalLoses;
    private int goalsFor; //Attığı gol
    private int goalsAgainst; //Yediği gol
    private int goalDifference; //Averaj
    private int points; //puan

    public Stats(StatsDB statsDB, Integer leagueID, Integer teamID) {
        super(statsDB);
        this.leagueID=leagueID;
        this.teamID=teamID;
        statsDB.save(this);
        FileIOWriter.addEntity(this);

    }

    public Integer getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(Integer leagueID) {
        this.leagueID = leagueID;
    }

    public Integer getTeamID() {
        return teamID;
    }

    public void setTeamID(Integer teamID) {
        this.teamID = teamID;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public int getTotalDraws() {
        return totalDraws;
    }

    public void setTotalDraws(int totalDraws) {
        this.totalDraws = totalDraws;
    }

    public int getTotalLoses() {
        return totalLoses;
    }

    public void setTotalLoses(int totalLoses) {
        this.totalLoses = totalLoses;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public void saveToDatabase(DatabaseModel databaseModel) {
        databaseModel.statsDB.findAll().add(this);
    }
}
