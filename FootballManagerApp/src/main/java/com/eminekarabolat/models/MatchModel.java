package com.eminekarabolat.models;



import com.eminekarabolat.entities.*;


import java.time.LocalDate;

public class MatchModel {

    private int matchId;
    private DatabaseModel databaseModel;
    private League league;
    private String homeTeamName;
    private String awayTeamName;
    private Integer homeTeamScore;
    private Integer awayTeamScore;
    private Stadium stadium;
    private LocalDate matchDate;
    private boolean isPlayed;




    public MatchModel(DatabaseModel databaseModel, Match match) {
        this.matchId=match.getId();
        this.databaseModel = databaseModel;
        this.league = databaseModel.leagueDB.findByID(match.getLeagueID()).get();
        this.homeTeamName = databaseModel.teamDB.findByID(match.getHomeTeamId()).get().getTeamName();
        this.awayTeamName = databaseModel.teamDB.findByID(match.getAwayTeamId()).get().getTeamName();
            this.stadium = databaseModel.stadiumDB.findByID(match.getHomeTeamId()).orElse(null);
        this.matchDate = match.getMatchDate();
        this.homeTeamScore=match.getHomeTeamScore();
        this.awayTeamScore = match.getAwayTeamScore();
        this.isPlayed= match.isPlayed();

    }


    public void displayMatchInfo() {
        System.out.println("Match ID:           : " + matchId);
        System.out.println("Stadium             : " + (stadium!=null ? stadium.getName() : "N/A"));
        System.out.println("Home Team           : " + homeTeamName);
        System.out.println("Away Team           : " + awayTeamName);
        System.out.println("Home Team Score     : " + (isPlayed? homeTeamScore : "N/A"));
        System.out.println("Away Team Score     : " + (isPlayed? awayTeamScore : "N/A"));
        System.out.println("Match Date          : " + matchDate);
        System.out.println("--------------------------------------------------");
    }

    public  void displayMatchStats(MatchStats matchStats){
        System.out.println("Match ID:           : " + matchId);
        System.out.println(homeTeamName + " " + homeTeamScore + "-" + awayTeamScore + " " + awayTeamName + " statistics");
        System.out.println("--------------------------------------------");
        System.out.printf("%-10s %-20s %-10s\n", "%" + matchStats.getHomeTeamBallPercantage(), "Ball Possession", "%" + matchStats.getAwayTeamBallPercantage());
        System.out.printf("%-10d %-20s %-10d\n", matchStats.getHomeTeam_Passes(), "Total Passes", matchStats.getAwayTeam_Passes());
        System.out.printf("%-10d %-20s %-10d\n", matchStats.getHomeTeam_Shots(), "Total Shots", matchStats.getAwayTeam_Shots());
        System.out.printf("%-10d %-20s %-10d\n", matchStats.getHomeTeam_fouls(), "Total Fouls", matchStats.getAwayTeam_fouls());
        System.out.printf("%-10d %-20s %-10d\n", matchStats.getHomeTeam_Saves(), "Total Saves", matchStats.getAwayTeam_Saves());






    }

//    System.out.println(homeTeamName+" "+homeTeamScore+"-"+awayTeamScore+" "+awayTeamName+" statistics");
//        System.out.println("--------------------------------------------");
//        System.out.println("%"+matchStats.getHomeTeamBallPercantage()+" Ball Posession %"+matchStats.getAwayTeamBallPercantage());
//        System.out.println(matchStats.getHomeTeam_Passes()+" Total Passes "+matchStats.getAwayTeam_Passes());
//        System.out.println(matchStats.getHomeTeam_Shots()+" Total Shots "+matchStats.getAwayTeam_Shots());
//        System.out.println(matchStats.getHomeTeam_fouls()+" Total Faults "+matchStats.getAwayTeam_fouls());
//        System.out.println(matchStats.getAwayTeam_Saves()+" Total Saves "+matchStats.getHomeTeam_Saves());









}