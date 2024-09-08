package com.eminekarabolat.modules;


import com.eminekarabolat.entities.League;
import com.eminekarabolat.entities.Match;
import com.eminekarabolat.entities.Stats;
import com.eminekarabolat.models.DatabaseModel;
import com.eminekarabolat.utility.matchEngine.MatchEngine;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MatchModule {

    private static Scanner sc = new Scanner(System.in);
    private static DatabaseModel databaseModel = DatabaseModel.getInstance();

    public static int matchModule() {
//        databaseModel = dbModel;
        int opt = 0;
        opt = matchModuleMenuOptions(matchModuleMenu());
        return opt;
    }

    public static int matchModuleMenu() {
        while (true) {
            System.out.println("### Match Module MENU ### --  Game Date: " + databaseModel.leagueDB.findByID(1).get().getCurrentDate()); //TODO Geçici çözüm, burada da lig tanımlanmalı.
            System.out.println("1- Play match manually"); //TODO:Further improvements will be added
            System.out.println("2- Skip days");
            System.out.println("3- Play all unplayed matches");
            System.out.println("0- Return Main Menu...");
            System.out.print("selection: ");
            int opt;
            try {
                opt = sc.nextInt();
                return opt;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a numaric value!");
            } finally {
                sc.nextLine();
            }
        }

    }

    public static int matchModuleMenuOptions(int opt) {
        switch (opt) {
            case 1:
                playMatch();
                matchModuleMenuOptions(matchModuleMenu());
                break;
            case 2:
                advanceDateByDays();
                matchModuleMenuOptions(matchModuleMenu());
                break;
            case 3:
                playAllMatches();
                matchModuleMenuOptions(matchModuleMenu());
                break;


            case 0: {
                System.out.println("Returning to Main menu...");
                return opt;
            }
            default:
                System.out.println("Please enter a valid value!");
        }
        return opt;
    }


    private static void playMatch(Match match) {


        Stats homeTeam = databaseModel.statsDB.findByID(match.getHomeTeamId()).get();
        Stats awayTeam = databaseModel.statsDB.findByID(match.getAwayTeamId()).get();

        if (match.isPlayed()) {
            return;
//              rollbackMatchOutcome(match, homeTeam, awayTeam);
        }
        if (match.getHomeTeamId() == 20 || match.getAwayTeamId() == 20) {
            return; //Bay geçen takım maç yapmasın diye.
        }

        match.setHomeTeamScore(new Random().nextInt(0, 4));
        match.setAwayTeamScore(new Random().nextInt(0, 4));

        if (homeTeam.getId() == 5 || homeTeam.getId() == 1) {
            match.setHomeTeamScore(match.getHomeTeamScore() + 2);
        }
        if (awayTeam.getId() == 5 || awayTeam.getId() == 1) {
            match.setAwayTeamScore(match.getAwayTeamScore() + 2);
        }


        updatePoints(match, homeTeam, awayTeam);
        match.setPlayed(true);
    }


    private static void playMatch() {
        System.out.println("Enter match id: ");
        int matchId = sc.nextInt();
        Match match = databaseModel.matchDB.findByID(matchId).orElse(null);


        if (match != null) {
            if (match.isPlayed()) {
                System.out.println("This match is already been played.");
                return;
            }
            if (match.getHomeTeamId() == 20 || match.getAwayTeamId() == 20) {
                System.out.println("Choosen match includes BAY. Cannot be played...Wait for the next weeks");
                return; //Bay geçen takım maç yapmasın diye.
            }

            MatchEngine.simulateMatch(match);

            Stats homeTeam = databaseModel.statsDB.findByID(match.getHomeTeamId()).get();
            Stats awayTeam = databaseModel.statsDB.findByID(match.getAwayTeamId()).get();


            updatePoints(match, homeTeam, awayTeam);
            match.setPlayed(true);
        } else {
            System.out.println("Match couldn't be found");
        }

    }



    //TODO: further properties will be added
    private static void simulateMatch() {

    }


    //Bugün ve bugünden önceki
    public static void playMatchesBeforeNow() {
//        databaseModel = db;
        databaseModel.matchDB.findAll().stream()
                .filter(m -> m.getLeagueID().equals(1))
                .filter(m -> m.getHomeTeamId() != 20 && m.getAwayTeamId() != 20) //Bay geçen takım maç yapmasın diye
                .filter(m -> m.getMatchDate().isBefore(LocalDate.now().plusDays(1)))
                .forEach(MatchModule::playMatch);
    }

    private static void advanceDateByDays() {
        League league = databaseModel.leagueDB.findByID(1).get(); //TODO: geçici çözüm
        LocalDate currentDate = league.getCurrentDate();
        Integer daysToAdd = 0;
        System.out.print("How many days do you want to advance: ");
        try {
            daysToAdd = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Please enter valid input.");
        } finally {
            sc.nextLine();

        }

        if (daysToAdd < 0) {
            System.out.println("404 error");
            return;
        }
        league.setCurrentDate(currentDate.plusDays(daysToAdd));

        LocalDate fixedDate = league.getCurrentDate();

        System.out.println("-------------------------------------------------------");
        System.out.println("New currentDate is = " + fixedDate);
        System.out.println("-------------------------------------------------------");

        databaseModel.matchDB.findAll().stream()
                .filter(m -> m.getLeagueID().equals(1))
                .filter(m -> m.getHomeTeamId() != 20 && m.getAwayTeamId() != 20) //Bay geçen takım maç yapmasın diye
                .filter(m -> m.getMatchDate().isBefore(fixedDate.plusDays(1)))
                .forEach(MatchModule::playMatch);
    }

    private static void playAllMatches() {
        databaseModel.matchDB.findAll().stream()
                .filter(m -> m.getLeagueID().equals(1))
                .filter(m -> m.getHomeTeamId() != 20 && m.getAwayTeamId() != 20)
                .forEach(MatchModule::playMatch);
    }

    private static void updatePoints(Match match, Stats homeTeam, Stats awayTeam) {
        homeTeam.setGamesPlayed(homeTeam.getGamesPlayed() + 1);
        awayTeam.setGamesPlayed(awayTeam.getGamesPlayed() + 1);

        homeTeam.setGoalsFor(homeTeam.getGoalsFor() + match.getHomeTeamScore());
        awayTeam.setGoalsFor(awayTeam.getGoalsFor() + match.getAwayTeamScore());
        homeTeam.setGoalsAgainst(homeTeam.getGoalsAgainst() + match.getAwayTeamScore());
        awayTeam.setGoalsAgainst(awayTeam.getGoalsAgainst() + match.getHomeTeamScore());
        homeTeam.setGoalDifference(homeTeam.getGoalsFor() - homeTeam.getGoalsAgainst());
        awayTeam.setGoalDifference(awayTeam.getGoalsFor() - awayTeam.getGoalsAgainst());

        if (match.getHomeTeamScore() > match.getAwayTeamScore()) {
            homeTeam.setTotalWins(homeTeam.getTotalWins() + 1);
            awayTeam.setTotalLoses(awayTeam.getTotalLoses() + 1);
        } else if (match.getAwayTeamScore() > match.getHomeTeamScore()) {
            awayTeam.setTotalWins(awayTeam.getTotalWins() + 1);
            homeTeam.setTotalLoses(homeTeam.getTotalLoses() + 1);
        } else {
            homeTeam.setTotalDraws(homeTeam.getTotalDraws() + 1);
            awayTeam.setTotalDraws(awayTeam.getTotalDraws() + 1);
        }
        homeTeam.setPoints((homeTeam.getTotalWins() * 3) + (homeTeam.getTotalDraws()));
        awayTeam.setPoints((awayTeam.getTotalWins() * 3) + awayTeam.getTotalDraws());
    }


    //Oynanan maç tekrar oynamak istenirse, önceki maç sonucunun takım istatistikleri üzerindeki etkisi sıfırlanır.
    private static void rollbackMatchOutcome(Match match, Stats homeTeam, Stats awayTeam) {
        homeTeam.setGamesPlayed(homeTeam.getGamesPlayed() - 1);
        awayTeam.setGamesPlayed(awayTeam.getGamesPlayed() - 1);

        homeTeam.setGoalsFor(homeTeam.getGoalsFor() - match.getHomeTeamScore());
        awayTeam.setGoalsFor(awayTeam.getGoalsFor() - match.getAwayTeamScore());
        homeTeam.setGoalsAgainst(homeTeam.getGoalsAgainst() - match.getAwayTeamScore());
        awayTeam.setGoalsAgainst(awayTeam.getGoalsAgainst() - match.getHomeTeamScore());
        homeTeam.setGoalDifference(homeTeam.getGoalsFor() - homeTeam.getGoalsAgainst());
        awayTeam.setGoalDifference(awayTeam.getGoalsFor() - awayTeam.getGoalsAgainst());

        if (match.getHomeTeamScore() > match.getAwayTeamScore()) {
            homeTeam.setTotalWins(homeTeam.getTotalWins() - 1);
            awayTeam.setTotalLoses(awayTeam.getTotalLoses() - 1);
        } else if (match.getAwayTeamScore() > match.getHomeTeamScore()) {
            awayTeam.setTotalWins(awayTeam.getTotalWins() - 1);
            homeTeam.setTotalLoses(homeTeam.getTotalLoses() - 1);
        } else {
            homeTeam.setTotalDraws(homeTeam.getTotalDraws() - 1);
            awayTeam.setTotalDraws(awayTeam.getTotalDraws() - 1);
        }
        homeTeam.setPoints((homeTeam.getTotalWins() * 3) + (homeTeam.getTotalDraws()));
        awayTeam.setPoints((awayTeam.getTotalWins() * 3) + awayTeam.getTotalDraws());
    }


}