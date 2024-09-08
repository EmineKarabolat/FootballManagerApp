package com.sacak.utility;


import com.sacak.models.DatabaseModel;

public interface ILoad {
    void saveToDatabase(DatabaseModel databaseModel);
}
