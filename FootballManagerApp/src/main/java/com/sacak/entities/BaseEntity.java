package com.sacak.entities;


import com.sacak.utility.DatabaseManager;

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
