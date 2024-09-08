package com.sacak.postgres;

import com.sacak.models.DatabaseModel;

public class Constants {
    static final String DB_HOST_NAME = "localhost";
    static final String DB_PORT = "5432";
    static final String DB_NAME = "FootballApp";
    static final String DB_USERNAME = "postgresql";
    static final String DB_PASSWORD= "root";

    static final DatabaseModel databaseModel = DatabaseModel.getInstance();


}