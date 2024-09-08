package com.eminekarabolat.modules;


import com.eminekarabolat.entities.*;
import com.eminekarabolat.models.*;

import java.util.*;

public class ManagerModule {

    //ManagerModule yazalım. Menajer id şifre ile giriş yapsın, kulüp görüntülemesi yaparken kendi kulübünü detaylı görüntülesin,
    // diğer kulüpleri özet bilgiyle görüntüleyebilsin. Ayrıca başka kulüp futbolcularını özet bilgileriyle görüntüleyebilirken,
    // kendi kulübünün futbolcularını detaylı görüntüleyebilsin.
    //bağlam menüsü var

    private static Scanner sc = new Scanner(System.in);
    private static DatabaseModel databaseModel;

    private static Optional<Manager> managerOptional = Optional.empty();

    public static int managerModule(DatabaseModel db) {

        databaseModel = db;
        int opt = 0;
        opt = managerModuleMenuOptions(managerModuleMenu());
        return opt;
    }

    public static int managerModuleMenu() {
        while (true) {
            System.out.println("### MANAGER  LOGIN MENU ###");
            System.out.println("1- Login");
            System.out.println("0- Return");
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

    private static int managerModuleMenuOptions(int opt) {
        switch (opt) {
            case 1:
                loginManager();
                managerMainMenuOptions(managerMainMenu());
                break;
            case 0:
                System.out.println("See you later.");
                return opt;
            default:
                System.out.println("Please enter a valid value!");
        }
        return opt;
    }

    private static int managerMainMenu() {
        while (true) {
            System.out.println("#### MANAGER MAIN MENU");
            System.out.println("1- Go to My team");
            System.out.println("2- All teams in the league");
            System.out.println("0- Log out");
            System.out.print("Selection: ");

            try {
                int opt = sc.nextInt();
                return opt;
            } catch (Exception e) {
                System.out.println("Please enter valid input.");
            } finally {
                sc.nextLine();
            }
        }
    }

    private static int managerMainMenuOptions(int opt) {
        switch (opt) {
            case 1: //manager Home menu
                managerHomeMenuOptions(managerHomeMenu());
                managerMainMenuOptions(managerMainMenu());
                break;
            case 2: //All teams in league menu
                managerAwayMenuOptions(managerAwayMenu());
                managerMainMenuOptions(managerMainMenu());
                break;
            case 0:
                System.out.println("Loggin out");
                managerOptional = Optional.empty();
                return opt;
            default:
                System.out.println("Please make a choice between given options.");
                managerMainMenuOptions(managerMainMenu());
        }
        return opt;
    }

    private static int managerAwayMenu() {
        int opt = 0;
        System.out.println("1- Display rival teams");
        System.out.println("2- Display rival player info");
        System.out.println("0- Return to manager main menu");
        System.out.print("Selection: ");
        try {
            opt = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Please enter valid input.");
            managerAwayMenu();
        } finally {
            sc.nextLine();
        }
        return opt;
    }

    private static int managerAwayMenuOptions(int opt) {
        if (managerOptional.isPresent()) {
            Manager manager = managerOptional.get();
            switch (opt) {
                case 1: //display other teams in the league
                    List<Team> rivalTeams = databaseModel.teamDB.getRivalTeams(manager);
                    int teamId = getRivalTeamIdMenuOptions(getRivelTeamIdMenu());
                    Optional<Team> teamByTeamID = databaseModel.teamDB.getTeamByTeamID(rivalTeams, teamId);
                    if(teamByTeamID.isPresent()){
                        databaseModel.teamDB.getRivalTeamSquad(manager, teamId, databaseModel);
                    }
                    managerAwayMenuOptions(managerAwayMenu());
                    break;
                case 2: //display player
                    dispLayRivalPlayerInfo(manager);
                    managerAwayMenuOptions(managerAwayMenu());
                    break;
                case 0:
                    System.out.println("Returning to manager main menu");
                    managerMainMenuOptions(managerMainMenu());
                    return opt;
                default:
                    System.out.println("Please make a choice between given options.");
                    managerAwayMenuOptions(managerAwayMenu());
            }

        } else {
            System.out.println("404");
        }
        return opt;
    }

    private static int getRivelTeamIdMenu() {
        int opt = -1;
        while (true) {
            try {
                System.out.println("1-Display team info");
                System.out.println("0-Return to previous menu");
                System.out.print("Selection: ");
                opt = sc.nextInt();
                return opt;
            } catch (Exception e) {
                System.out.println("Please enter valid input.");
            } finally {
                sc.nextLine();
            }
        }
    }

    private static int getRivalTeamIdMenuOptions(int opt) {
        int teamId = 0;
        switch (opt) {
            case 1:
                System.out.print("Team id: ");
                try {
                    teamId = sc.nextInt();
                    return teamId;
                } catch (Exception e) {
                    System.out.println("Please enter valid input.");
                    getRivalTeamIdMenuOptions(getRivelTeamIdMenu());
                } finally {
                    sc.nextLine();
                }
                break;
            case 0 :
                System.out.println("Returning to manager Away Menu");
                managerAwayMenuOptions(managerAwayMenu());
        }
        return teamId;
    }

    private static void dispLayRivalPlayerInfo(Manager manager) {
        Optional<Player> playerOpt = getPlayer();
        if (playerOpt.isPresent()) {
            Player player = playerOpt.get();
            if (!player.getTeamId().equals(manager.getTeamId())) {
                System.out.println("-----------------");
                System.out.println("id=" + player.getId()
                        + ", name='" + player.getName()
                        + "', age=" + player.getAge() + "', teamID="
                        + player.getTeamId());
                System.out.println("-----------------");
            } else {
                System.out.println("Player is on your team! You can show player details on your team Menu...");
            }
        } else {
            System.out.println("There is no player in database with id you've entered.\n");
        }
    }

    private static int managerHomeMenu() {
        int opt = 0;
        System.out.println("1- Display my team");
        System.out.println("2- Display player");
        System.out.println("0- Return to manager main menu");
        System.out.print("Selection: ");
        try {
            opt = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Please enter valid input.");
            managerHomeMenu();
        } finally {
            sc.nextLine();
        }
        return opt;
    }

    private static int managerHomeMenuOptions(int opt) {
        if (managerOptional.isPresent()) {
            Manager manager = managerOptional.get();
            switch (opt) {
                case 1: //Display manager team squad
                    databaseModel.teamDB.getTeamSquad(databaseModel, manager.getTeamId());
                    managerHomeMenuOptions(managerHomeMenu());
                    break;
                case 2: //Display player info (detailed)
                    displayDetailedPlayerInfo(manager);
                    managerHomeMenuOptions(managerHomeMenu());
                    break;
                case 0:
                    System.out.println("Returning to manager main menu");
                    return opt;
                default:
                    System.out.println("Please make a choice between given options.");
                    managerHomeMenuOptions(managerHomeMenu());
            }
        } else {
            System.out.println("404 ERROR"); //Bu gelirse kurulan yapida sikinti var demek.
        }
        return opt;

    }

    private static void displayDetailedPlayerInfo(Manager manager) {
        Optional<Player> playerOpt = getPlayer();
        if (playerOpt.isPresent()) {
            if (playerOpt.get().getTeamId().equals(manager.getTeamId())) {
                System.out.println("-----------------");
                System.out.println(playerOpt.get());
                System.out.println("-----------------");
            } else {
                System.out.println("No player found on our squad with id you've entered.");
            }
        } else {
            System.out.println("There is no player in database with id you've entered.\n");
        }
    }

    private static Optional<Player> getPlayer() {
        System.out.print("Player id: ");
        int id = 0;
        try {
            id = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Please enter valid input.");
            managerHomeMenuOptions(managerHomeMenu());
        } finally {
            sc.nextLine();
        }
        return databaseModel.playerDB.findByID(id);
    }

    private static Optional<Manager> loginManager() {
        int count = 0;
        while (true) {
            count++;
            System.out.print("Username: ");
            String username = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();
            Optional<Manager> manager1 = databaseModel.managerDB.findByUsernameAndPassword(username, password);
            if (manager1.isPresent()) {
                managerOptional = manager1;
                System.out.println("You've succesfully logged in.");
                return manager1;
            } else {
                System.out.println("Password or username is wrong. You have" + (3 - count) + " attempts left.");
            }

            if (count == 3) {
                System.out.println("You exceeded your try limit. Returning to main menu.");
                managerModuleMenuOptions(managerModuleMenu());
            }
        }


    }
}