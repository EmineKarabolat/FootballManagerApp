package com.eminekarabolat.entities;


import com.eminekarabolat.databases.StadiumDB;
import com.eminekarabolat.models.DatabaseModel;
import com.eminekarabolat.utility.FileIOWriter;
import com.eminekarabolat.utility.ILoad;

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