package com.sacak.utility.matchEngine;


import com.sacak.entities.*;
import com.sacak.models.*;
import com.sacak.utility.enums.*;

import java.util.*;

import static com.sacak.utility.matchEngine.Constants.*;

//TODO: Work in progress
public class MatchEngine {
    static DatabaseModel databaseModel = DatabaseModel.getInstance();
    static Random random = new Random();





    public static void simulateMatch(Match match) {
        MatchStats matchStats = calculateTeamPassDistribution(match);

        Team homeTeam = databaseModel.teamDB.findByID(match.getHomeTeamId()).get();
        Team awayTeam = databaseModel.teamDB.findByID(match.getAwayTeamId()).get();


        int totalPass = matchStats.getAwayTeam_Passes() + matchStats.getHomeTeam_Passes();
        int homeTeamBallPercantage = matchStats.getHomeTeamBallPercantage();


        Team attackingTeam;
        Team defenceTeam;
        for (int i = 0; i < totalPass; i++) {
            double olasilik = Math.random();
            if (olasilik <= EVENT_POSSIBILITY) {//pas yapıp pozisyona girmedikce pozisyona girme olasiligi %sel artsın.
                int rasgele = random.nextInt(101);
                if (rasgele < homeTeamBallPercantage) {
                    attackingTeam = homeTeam;
                    defenceTeam = awayTeam;
                } else {
                    attackingTeam = awayTeam;
                    defenceTeam = homeTeam;
                }
                if (makeFinalPass(attackingTeam)) {
                    if (takeShot(attackingTeam)) {
                        if(attackingTeam==homeTeam){
                            matchStats.setHomeTeam_Shots(matchStats.getHomeTeam_Shots()+1);
                        }
                        else{
                            matchStats.setAwayTeam_Shots(matchStats.getAwayTeam_Shots()+1);
                        }
                        if (!makeSave(defenceTeam)) {
                            System.out.println("GOOOOOOOLLLL " + attackingTeam.getTeamName() + " scores amazing goal!!!");
                            if (attackingTeam == homeTeam) {
                                match.setHomeTeamScore(match.getHomeTeamScore() + 1);
                                System.out.println("Score = " + match.getHomeTeamScore() + " - " + match.getAwayTeamScore());
                            } else {
                                match.setAwayTeamScore(match.getAwayTeamScore() + 1);
                                System.out.println("Score = " + match.getHomeTeamScore() + " - " + match.getAwayTeamScore());
                            }

                        }else{
                            if(attackingTeam==homeTeam){
                                matchStats.setAwayTeam_Saves(matchStats.getAwayTeam_Saves()+1);
                            }
                            else{
                                matchStats.setHomeTeam_Saves(matchStats.getHomeTeam_Saves()+1);
                            }
                        }
                    }
                }
            }

        }


    }



    private static int calculateTeamPower(Team team) {
        int teamPower = databaseModel.playerDB.findAll().stream()
                .filter(p -> p.getTeamId() == team.getId())
                .map(Player::getSkillLevel)
                .reduce(0, Integer::sum);
        return teamPower / NUMBER_OF_PLAYERS_IN_MATCH_SQUAD;
    }


    private static MatchStats calculateTeamPassDistribution(Match match) {

        MatchStats matchStats = new MatchStats(databaseModel.matchStatsDB, match.getId());

        int totalPass = random.nextInt(MIN_TOTAL_PASS_BOUNDARY, MAX_TOTAL_PASS_BOUNDARY);

        Team homeTeam = databaseModel.teamDB.findByID(match.getHomeTeamId()).get();
        Team awayTeam = databaseModel.teamDB.findByID(match.getAwayTeamId()).get();

        int homeTeamPower = calculateTeamPower(homeTeam);
        int awayTeamPower = calculateTeamPower(awayTeam);


        int homeTeamBasePercentage = (int) (50 + (homeTeamPower - awayTeamPower) * 0.5);
        int awayTeamBasePercentage = (int) (50 + (awayTeamPower - homeTeamPower) * 0.5);


        int homeTeamPassPercentage = Math.max(30, Math.min(70, homeTeamBasePercentage));
        int awayTeamPassPercentage = Math.max(30, Math.min(70, awayTeamBasePercentage));

        if (homeTeamPassPercentage + awayTeamPassPercentage != 100) {
            awayTeamPassPercentage = 100 - homeTeamPassPercentage;
        } //TODO: metodlaştır. util sinifinda...


        int homeTeamPasses = (int) Math.round((homeTeamPassPercentage / 100.0) * totalPass);
        int awayTeamPasses = (int) Math.round((awayTeamPassPercentage / 100.0) * totalPass);


        matchStats.setHomeTeam_Passes(homeTeamPasses);
        matchStats.setAwayTeam_Passes(awayTeamPasses);
        matchStats.setHomeTeamBallPercantage(homeTeamPassPercentage);
        matchStats.setAwayTeamBallPercantage(awayTeamPassPercentage);

        return matchStats;

    }


    private static boolean makeFinalPass(Team team) { //Pozisyona girdikten sonra outputlu pas seceneği.
        Random random = new Random();

        List<Player> list = databaseModel.playerDB.findAll().stream()
                .filter(p -> p.getTeamId().equals(team.getId()))
                .filter(p -> p.getPosition().equals(EPosition.MIDFIELDER) || p.getPosition().equals(EPosition.FORWARD))
                .toList();

        Player player = list.get(new Random().nextInt(list.size()));


        int passSuccess = random.nextInt(90);

        boolean success = passSuccess < player.getSkillLevel();


        if (success) {
            System.out.println(player.getName() + " makes a successful pass.");
        } else {
            System.out.println(player.getName() + " loses the ball.");
        }
        return success;
    }

    private static boolean takeShot(Team team) {
        Random random = new Random();
        int shotSuccess = random.nextInt(100);
        List<Player> list = databaseModel.playerDB.findAll().stream()
                .filter(p -> p.getTeamId().equals(team.getId()))
                .filter(p -> p.getPosition().equals(EPosition.MIDFIELDER) || p.getPosition().equals(EPosition.FORWARD))
                .toList();

        Player player = list.get(new Random().nextInt(list.size()));
        boolean success = shotSuccess < player.getSkillLevel();

        if (success) {
            System.out.println(player.getName() + " takes a shot on target!");
        } else {
            System.out.println((player.getName()) + " misses the shot.");
        }
        return success;
    }


    private static boolean makeSave(Team team) {
        Random random = new Random();
        int saveSuccess = random.nextInt(100);
        Player goalKeeper = databaseModel.playerDB.findAll().stream().filter(p -> p.getTeamId().equals(team.getId()))
                .filter(p -> p.getPosition().equals(EPosition.GOALKEEPER)).findFirst().get();
        boolean success = saveSuccess < goalKeeper.getSkillLevel();

        if (success) {
            System.out.println("Great save by " + team.getTeamName() + "'s goalkeeper!");
        }

        return success;
    }


}
