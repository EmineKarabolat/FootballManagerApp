package com.eminekarabolat.databases;


import com.eminekarabolat.entities.Manager;
import com.eminekarabolat.entities.Team;
import com.eminekarabolat.models.DatabaseModel;
import com.eminekarabolat.utility.DatabaseManager;
import java.util.*;

import java.util.Optional;

public class TeamDB extends DatabaseManager<Team> {

    private static TeamDB instance;

    private TeamDB(){
    }

    public static TeamDB getInstance(){
        if(instance==null){
            instance=new TeamDB();
        }
        return instance;
    }


    public void getRivalTeamSquad(Manager manager, int teamId, DatabaseModel databaseModel){
        Optional<Team> teamOpt = veriListesi.stream()
                .filter(team -> !team.getId().equals(manager.getTeamId()))
                .filter(team -> team.getId().equals(teamId)).findAny();
        if(teamOpt.isPresent()){
            Team team = teamOpt.get();
            System.out.println("-------- " +team.getTeamName().toUpperCase()+" --------");
            databaseModel.playerDB.findAll().stream().filter(player->player.getTeamId()!=null && player.getTeamId()==(teamId))
                    .map(player -> "id="+player.getId()+"  name='"+player.getName()+"'\t\tage="+player.getAge())
                    .forEach(System.out::println);
            return;
        }
        System.out.println("You either trying to get your own team squad or the team with that id does not exist.");
    }


    public void getTeamSquad(DatabaseModel databaseModel,int teamId){
        Team team;
        Optional<Team> teamOpt = findByID(teamId);
        if(!teamOpt.isEmpty()){
            team = teamOpt.get();
        }
        else{
            System.out.println("No team found by that ID");
            return;
        }
        System.out.println("-------------------- "+team.getTeamName().toUpperCase()+" SQUAD --------------------");
        databaseModel.playerDB.findAll().stream().filter(player->player.getTeamId()!=null && player.getTeamId().equals(teamId)).forEach(System.out::println);
    }

    public List<Team> findTeamByName(DatabaseModel databaseModel,String teamName) {
        List<Team> teamList = databaseModel.teamDB.findAll().stream().filter(t -> t.getTeamName().toLowerCase().contains(teamName.toLowerCase())).toList();
        System.out.println("------- Search of "+ teamName + " -------");
        if (teamList.isEmpty()){
            System.out.println("There are no team");
        }
        else {
            teamList.stream().map(team -> team.getId() + "- " + team.getTeamName()).forEach(System.out::println);
        }
        System.out.println("-----------------------------------");
        return teamList;
    }
    public Optional<Team> getTeamByTeamID(List<Team> teamList, int teamID) {
        return teamList.stream().filter(team -> team.getId() == teamID).findFirst();
    }


    public List<Team> getRivalTeams(Manager manager){
            List<Team> rivalTeams = veriListesi.stream().filter(team->!team.getId().equals(manager.getTeamId()))
                    .toList();
            System.out.println("----- RIVAL TEAMS -----");
            rivalTeams.stream().map(team->team.getId()+"\t"+team.getTeamName()).forEach(System.out::println);
            return rivalTeams;
        }



}