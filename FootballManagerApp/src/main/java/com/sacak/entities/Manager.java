package com.sacak.entities;


import com.sacak.databases.ManagerDB;
import com.sacak.models.DatabaseModel;
import com.sacak.utility.FileIOWriter;
import com.sacak.utility.ILoad;

public class Manager extends Person implements ILoad {

    private String username;
    private String password;
    private Integer experience; //between 1-10
    private Integer teamId;


    public Manager(ManagerDB managerDB){
        super(managerDB);
        managerDB.save(this);
        FileIOWriter.addEntity(this);
    }



    //ManagerDB managerDB, String name, String age,String username,String password, Integer teamID, Integer experience
    public Manager(ManagerDB managerDB, String name, String age,String username,String password, Integer teamId, Integer experience) {
        super(managerDB);
        super.name=name;
        super.age=age;
        this.username=username;
        this.password=password;
        this.teamId=teamId;
        this.experience=experience;
        managerDB.save(this);
        FileIOWriter.addEntity(this);
    }

    @Override
    public void saveToDatabase(DatabaseModel databaseModel) {
        databaseModel.managerDB.findAll().add(this);
    }

    {
        this.teamId = -1;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }



    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }


    @Override
    public String toString() {
        return "Manager{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", age='" + getAge() + '\'' +
                ", experience=" + getExperience() +
                ", teamId=" + getTeamId() +
                '}';
    }
}
