package com.eminekarabolat.databases;


import com.eminekarabolat.entities.Manager;
import com.eminekarabolat.utility.DatabaseManager;

import java.util.Optional;

public class ManagerDB extends DatabaseManager<Manager> {

    private static ManagerDB instance;

    private ManagerDB(){

    }
    public static ManagerDB getInstance(){
        if(instance==null){
            instance=new ManagerDB();
        }
        return instance;
    }


    public Optional<Manager> findByUsernameAndPassword(String username, String password) {
        return veriListesi.stream().filter(user-> user.getUsername().equals(username)
                && user.getPassword().equals(password)).findFirst();
    }


}