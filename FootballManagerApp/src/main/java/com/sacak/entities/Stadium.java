package com.sacak.entities;


import com.sacak.databases.StadiumDB;
import com.sacak.models.DatabaseModel;
import com.sacak.utility.FileIOWriter;
import com.sacak.utility.ILoad;

public class Stadium extends BaseEntity implements ILoad {

    private String name;
    private String location;
    private Integer capacity;

    public Stadium(StadiumDB stadiumDB, String name, String location, Integer capacity) {
        super(stadiumDB);
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        stadiumDB.save(this);
        FileIOWriter.addEntity(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Stadium{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", location='" + getLocation() + '\'' +
                ", capacity=" + getCapacity() +
                '}';
    }

    @Override
    public void saveToDatabase(DatabaseModel databaseModel) {
        databaseModel.stadiumDB.findAll().add(this);
    }
}
