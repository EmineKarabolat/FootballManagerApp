package com.eminekarabolat.entities;


import com.eminekarabolat.databases.TeamDB;
import com.eminekarabolat.models.DatabaseModel;
import com.eminekarabolat.utility.FileIOWriter;
import com.eminekarabolat.utility.ILoad;


public class Team extends BaseEntity implements ILoad {
    private String teamName;
    private Long budget;


    public Team(TeamDB teamDB, String teamName, Long budget) {
        super(teamDB);
        this.teamName = teamName;
        this.budget = budget;
        teamDB.save(this);
        FileIOWriter.addEntity(this);
    }

    public Team(TeamDB teamDB) {
        super(teamDB);
        teamDB.save(this);
    //        FileIOWriter.writeTeamToBin(databaseModel);
    }


    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }


    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }


    @Override
    public String toString() {
        return "Team{" +
                "id=" + getId() +
                ", teamName='" + getTeamName() + '\'' +
                ", budget=" + getBudget() +
                '}';
    }

    @Override
    public void saveToDatabase(DatabaseModel databaseModel) {
        databaseModel.teamDB.findAll().add(this);
    }
}