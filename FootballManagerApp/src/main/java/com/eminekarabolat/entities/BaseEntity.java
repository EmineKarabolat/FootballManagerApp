package com.eminekarabolat.entities;


import com.eminekarabolat.utility.DatabaseManager;

import java.io.Serializable;

public  class BaseEntity implements Serializable {

    protected Integer id;

    public Integer getId() {
        return id;
    }

    public BaseEntity(DatabaseManager<?> databaseManager) {
        id =databaseManager.findLastId()+1;
    }



}