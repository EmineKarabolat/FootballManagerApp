package com.eminekarabolat.utility;


import com.eminekarabolat.models.DatabaseModel;

public interface ILoad {
    void saveToDatabase(DatabaseModel databaseModel);
}