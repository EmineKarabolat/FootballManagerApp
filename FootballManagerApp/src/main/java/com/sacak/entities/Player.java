package com.sacak.entities;


import com.sacak.databases.PlayerDB;
import com.sacak.models.DatabaseModel;
import com.sacak.utility.FileIOWriter;
import com.sacak.utility.ILoad;
import com.sacak.utility.enums.EPosition;

public class Player extends Person implements ILoad {

    static Integer playerIdCounter = 0;

    private EPosition position;
    private Integer skillLevel; //1-10 arasi olacak.
    private Long value; //degeri
    private Integer teamId;

    public Player(PlayerDB playerDB) {
        super(playerDB);
        playerDB.save(this);
        FileIOWriter.addEntity(this);
    }

    @Override
    public void saveToDatabase(DatabaseModel databaseModel) {
        databaseModel.playerDB.findAll().add(this);
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }



    public EPosition getPosition() {
        return position;
    }

    public void setPosition(EPosition position) {
        this.position = position;
    }

    public Integer getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(Integer skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "Player{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", age='" + getAge() + '\'' +
                ", teamId=" + getTeamId() +
                ", position=" + getPosition() +
                ", skillLevel=" + getSkillLevel() +
                ", value=" + getValue() +
                '}';
    }


}
