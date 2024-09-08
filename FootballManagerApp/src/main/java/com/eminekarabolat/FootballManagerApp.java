package com.eminekarabolat;


import com.eminekarabolat.entities.League;
import com.eminekarabolat.models.DatabaseModel;
import com.eminekarabolat.modules.*;
import com.eminekarabolat.postgres.LeagueRepository;
import com.eminekarabolat.postgres.PlayerRepository;
import com.eminekarabolat.postgres.TeamRepository;
import com.eminekarabolat.utility.FileIOReader;
import com.eminekarabolat.utility.FileIOWriter;

import java.util.*;


public class FootballManagerApp {

    private static DatabaseModel databaseModel = DatabaseModel.getInstance();
    private static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        FileIOReader.loadData(databaseModel);
        
        PlayerRepository.insertPlayer();
  

        FileIOWriter.saveAllEntities();
//
//        startApplication();


    }


    public static void startApplication() {
        int opt;
        MatchModule.playMatchesBeforeNow();
        do {
            opt = mainMenu();
            switch (opt) {
                case 1:
                    opt = ManagerModule.managerModule(databaseModel);
                    break;
                case 2:
                    opt = TeamModule.teamModule(databaseModel);
                    break;
                case 3:
                    LeagueModule.leagueModule(databaseModel);
                    break;
                case 4:
                    MatchModule.matchModule();
                    break;
                case 0:
                    System.out.println("Exiting application...");
                    FileIOWriter.saveAllEntities();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (opt != 0);
    }

    private static int mainMenu() {
        while (true) {
            System.out.println("### MAIN MENU ###");
            System.out.println("1- Manager Module");
            System.out.println("2- Team Module ");
            System.out.println("3- League Module");
            System.out.println("4- Match Module");
            System.out.println("0- Exit");
            System.out.print("Selection: ");
            int opt;
            try {
                opt = sc.nextInt();
                return opt;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a numeric value!");
            } finally {
                sc.nextLine();
            }
        }
    }

}