package com.eminekarabolat.postgres;

import com.eminekarabolat.entities.League;
import com.eminekarabolat.entities.Team;

import java.sql.*;
import java.util.List;

import static com.eminekarabolat.postgres.Constants.*;

public class TeamRepository {

    public static boolean insertTeam(int leagueId) {
        String TEAM_INSERT_SQL = "INSERT INTO Team(id,leagueid,teamname,budget) VALUES (?,?,?,?)";
        String connectionString = "jdbc:postgresql://localhost:5432/FootballApp";
        String username = "postgres";
        String password = "root";
        try (
                Connection connection = DriverManager.getConnection(connectionString, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(TEAM_INSERT_SQL)) {

            League league = databaseModel.leagueDB.findByID(leagueId).orElse(null);
            if(league == null) {
                System.out.println("Lig bulunamadi.....");
                return false;
            }
            List<Team> teams = databaseModel.teamDB.findAll();


            for(Team t : teams){
                preparedStatement.setInt(1, t.getId());
                preparedStatement.setInt(2,leagueId);
                preparedStatement.setString(3,t.getTeamName());
                preparedStatement.setLong(4, t.getBudget());
               
                int etkilenenSatirSayisi = preparedStatement.executeUpdate();
                if (etkilenenSatirSayisi > 0) {
                    System.out.println("Kayıt başarı ile eklendi");
                } else {
                    System.out.println("Kaydetmede sorun yaşandı");
                }
            }

            return true;
        } catch (SQLException e) {
            System.out.println("Bağlantı hatası..." + e.getMessage());
        }
        return false;
    }
}