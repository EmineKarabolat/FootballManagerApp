package com.eminekarabolat.entities;


import com.eminekarabolat.utility.DatabaseManager;

public class Person extends BaseEntity{

    protected String name;
    protected String age;

    public Person(DatabaseManager<?> databaseManager) {
        super(databaseManager);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}