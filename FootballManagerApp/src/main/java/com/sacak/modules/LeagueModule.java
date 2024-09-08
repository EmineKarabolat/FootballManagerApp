package com.sacak.modules;


import com.sacak.entities.*;
import com.sacak.models.*;

import java.util.*;

public class LeagueModule {

    private static Scanner sc = new Scanner(System.in);
    private static DatabaseModel databaseModel;
    private static League league;
    private static LeagueModel leagueModel;

    public static int leagueModule(DatabaseModel dbModel) {
        databaseModel = dbModel;
        int opt = leagueModuleMenuOptions(leagueModuleMenu());



        return opt;
    }

    //TODO: Bir ligle devam edilsin, bir daha menu icinde lig aramakla ugrasmayalım. Kullanıcı baska ligle devam etmek isterse cıkıs
    // yapıp tekrar geri giris yapabilir.
    public static void loginLeague() {

            Optional<League> optionalLeague = Optional.empty();
            try {
                System.out.print("Enter league id: ");
                int choice = sc.nextInt();
                optionalLeague = databaseModel.leagueDB.findByID(choice);
                league = optionalLeague.orElse(null);

            } catch (Exception e) {
                System.out.println("Wrong input, please try again later.");

            } finally {
                sc.nextLine();
            }

    }

    public static int leagueModuleMenu() {
        if(league==null){
            loginLeague();
        }
            leagueModel = new LeagueModel(databaseModel, league);
            while (true) {
                System.out.println("\nLeague Module Menu, current chosen League: "+league.getLeaugeName()+" -- Game Date: "+league.getCurrentDate());
                System.out.println("1- Show all season fixture");
                System.out.println("2- Show weekly fixture");
                System.out.println("3- Show standings");
                System.out.println("4- Show Played Matches");
                System.out.println("5- Show match statistics");
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

    public static int leagueModuleMenuOptions(int opt) {
        switch (opt) {
            case 1: {
                leagueModel.displayLeagueInfo();
                leagueModel.displayFixture();
                leagueModuleMenuOptions(leagueModuleMenu());
                break;
            }
            case 2:
                int weekNumber = askForWeekNumber();
                leagueModel.displayWeeklyFixture(weekNumber);
                leagueModuleMenuOptions(leagueModuleMenu());
                break;
            case 3: // -> Puan durumu
                leagueModel.displayStandings();
                leagueModuleMenuOptions(leagueModuleMenu());
                break;
            case 4://Oynanmis maclari goster
                leagueModel.displayPlayedMatches();
                leagueModuleMenuOptions(leagueModuleMenu());
                break;
            case 5: showMatchStatistic();
            leagueModuleMenuOptions(leagueModuleMenu());
            break;
            case 0:
                System.out.println("Returning to main menu...");
                return opt;

            default:
                System.out.println("Please enter a valid value!");
                leagueModuleMenuOptions(leagueModuleMenu());
                return opt;
        }
        return opt;
    }

    private static void showMatchStatistic() {
        System.out.println("Enter match id: ");
        int matchId = sc.nextInt();
        sc.nextLine();
        Match match = databaseModel.matchDB.findByID(matchId).orElse(null);
        MatchStats matchStats = databaseModel.matchStatsDB.findbyMatchId(matchId).orElse(null);
        if(matchStats!=null && match!=null){
            new MatchModel(databaseModel, match).displayMatchStats(matchStats);
        }
        else{
            System.out.println("404 ERROR ");
        }


    }


    private static int askForWeekNumber() {
        while (true) {
            System.out.print("Enter week: ");
            int week;
            try {
                week = sc.nextInt();
                if (week < 1 || week > (league.getTeamIdList().size() - 1) * 2) {
                    System.out.println("Input you've entered for week is beyond our league fixture weekly list.");
                    System.out.println("Returning to Main Menu");
                    leagueModuleMenuOptions(leagueModuleMenu());
                }
                return week;
            } catch (Exception e) {
                System.out.println("Please enter valid input.");
            } finally {
                sc.nextLine();
            }
        }
    }

    private static void listLeagues() {
        LeagueModel leagueModel;
        List<League> leagues = databaseModel.leagueDB.getLeagues();
        System.out.println("List of Leagues: ");
        for (League league : databaseModel.leagueDB.getLeagues()) {
            leagueModel = new LeagueModel(databaseModel, league);
            leagueModel.displayLeagueInfo();
        }
    }
}
