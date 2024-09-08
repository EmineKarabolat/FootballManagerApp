package com.eminekarabolat.postgres;


import com.eminekarabolat.entities.League;

import java.sql.*;

public class LeagueRepository {

    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public static boolean insertLeague(League league){
        String LEAGUE_INSERT_SQL = "INSERT INTO League(id,leaguename,region,division,season,BEGINNING_OF_SEASON,currentdate) VALUES (?,?,?::eregion,?::edivision,?,?,?)";
        String connectionString = "jdbc:postgresql://localhost:5432/FootballApp";
        String username = "postgres";
        String password = "root";
        try (
            Connection connection = DriverManager.getConnection(connectionString,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement(LEAGUE_INSERT_SQL)){
                preparedStatement.setInt(1, league.getId());
                preparedStatement.setString(2, league.getLeaugeName());
                preparedStatement.setString(3, league.getRegion().name());
                preparedStatement.setString(4, league.getDivision().name());
                preparedStatement.setString(5, league.getSeason());
                preparedStatement.setDate(6, Date.valueOf(league.getBEGINNING_OF_SEASON()));
                preparedStatement.setDate(7, Date.valueOf(league.getCurrentDate()));

            int etkilenenSatirSayisi = preparedStatement.executeUpdate();
            if (etkilenenSatirSayisi > 0) {
                System.out.println("Kayıt başarı ile eklendi");
            }
            else {
                System.out.println("Kaydetmede sorun yaşandı");
            }
                return true;
            }

         catch (SQLException e) {
            System.out.println("Bağlantı hatası..."+e.getMessage());
        }
        return false;
    }


}