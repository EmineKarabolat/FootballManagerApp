package com.sacak.modules;



import com.sacak.entities.*;
import com.sacak.models.*;

import java.util.*;

public class TeamModule {
    private static Scanner sc = new Scanner(System.in);
    private static DatabaseModel databaseModel;

    public static int teamModule(DatabaseModel db) {
        databaseModel = db;
        int opt = 0;
        opt = teamModuleMenuOptions(teamModuleMenu());
        return opt;
    }

    public static int teamModuleMenu() {
        while (true) {
            System.out.println("### TEAM CLUB MENU ###");
            System.out.println("1- Search Team By Name");
            System.out.println("2- List All Teams");
            System.out.println("0- Close the application");
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

    public static int teamModuleMenuOptions(int opt) {
        switch (opt) {
            case 1: { //Search Team By Name
                Optional<Team> teamOptional = searchByTeamName();
                if (teamOptional.isPresent()) {
                    Team team = teamOptional.get();
                    String teamName = team.getTeamName();
                    opt = teamDetailMenuOptions(teamDetailMenu(teamName), team);


                } else {
                    System.out.println("No such team found by this ID!");
                }
                break;
            }
            case 2: { //List All Teams
                System.out.println("--- Lists of All Teams ---");
                databaseModel.teamDB.findAll().stream()
                        .map(team -> team.getId() + " - " + team.getTeamName())
                        .forEach(System.out::println);
                System.out.println("-------------------------------");
                int isContinue = askUserToContinue();
                if (isContinue == 1) {
                    int id = askUserTeamID();
                    Optional<Team> teamOptional = databaseModel.teamDB.getTeamByTeamID(databaseModel.teamDB.findAll(), id);
                    if (teamOptional.isPresent()) {
                        Team team = teamOptional.get();
                        String teamName = team.getTeamName();
                        opt = teamDetailMenuOptions(teamDetailMenu(teamName), team);


                    } else {
                        System.out.println("No such team found by this ID!");
                    }
                }
                break;

            }
            case 0: {
                System.out.println("gülü gülü...");
                return opt;
            }
            default:
                System.out.println("Please enter a valid value!");
        }
        return opt;
    }

    private static Optional<Team> searchByTeamName() {
        System.out.print("Enter a team name: ");
        String teamName = sc.nextLine();
        Optional<Team> teamOptional = Optional.empty();
        List<Team> teamList = databaseModel.teamDB.findTeamByName(databaseModel, teamName);
        if (!teamList.isEmpty()) {
            int userSelection = askUserToContinue();
            if (userSelection == 1) {
                int teamID = askUserTeamID();
                teamOptional = databaseModel.teamDB.getTeamByTeamID(teamList, teamID);
            }
        }
        return teamOptional;
    }

    private static int askUserTeamID() {
        System.out.print("Enter ID: ");
        int teamID = sc.nextInt();
        sc.nextLine();
        return teamID;
    }


    private static int askUserToContinue() {
        while (true) {
            System.out.println("1- Select Team By ID");
            System.out.println("0- Return to main menu");
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


    // Team Detail Menu
    public static int teamDetailMenu(String teamName) {
        while (true) {
            System.out.println("### " + teamName + " ###");
            System.out.println("1- Show Team Details");
            System.out.println("2- Show Team Players");
            System.out.println("0- Return to main menu");
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

    public static int teamDetailMenuOptions(int opt, Team team) {
        switch (opt) {
            case 1: { //Show team Details
                System.out.println(team);
                System.out.println("----------------------------------");
                teamDetailMenuOptions(teamDetailMenu(team.getTeamName()), team); //Dongu koymadan donguye soktuk.
                break;
            }
            case 2: { //Show Team Players
                System.out.println("----- "+team.getTeamName()+" PLAYERS -----");
                databaseModel.teamDB.getTeamSquad(databaseModel, team.getId());
                teamDetailMenuOptions(teamDetailMenu(team.getTeamName()), team);
                break;
            }
            case 0: {
                System.out.println("Returning to main menu...");
                return opt;
            }
            default:
                System.out.println("Please enter a valid value!");
        }
        return opt;
    }


}
