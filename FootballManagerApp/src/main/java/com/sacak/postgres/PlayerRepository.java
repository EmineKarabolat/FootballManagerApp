package com.sacak.postgres;

import com.sacak.entities.League;
import com.sacak.entities.Player;
import com.sacak.entities.Team;
import com.sacak.models.DatabaseModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.sacak.postgres.Constants.databaseModel;

public class PlayerRepository {

    public static boolean insertPlayer() {
        String PLAYER_INSERT_SQL =
                "INSERT INTO Player(id,teamid,name,age,position,skilllevel,value) VALUES (?,?,?,?,?::eposition,?,?)";
        String connectionString = "jdbc:postgresql://localhost:5432/FootballApp";
        String username = "postgres";
        String password = "root";
        try (
                Connection connection = DriverManager.getConnection(connectionString, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(PLAYER_INSERT_SQL)) {


            List<Player> players = databaseModel.playerDB.findAll();

            int etkilenenSatirSayisi = 0;
            for (Player player : players) {
                preparedStatement.setInt(1, player.getId());
                preparedStatement.setInt(2,player.getTeamId());
                preparedStatement.setString(3,player.getName());
                preparedStatement.setInt(4,Integer.parseInt(player.getAge()));
                preparedStatement.setString(5,player.getPosition().name());
                preparedStatement.setInt(6,player.getSkillLevel());
                preparedStatement.setLong(7,player.getValue());


                etkilenenSatirSayisi = preparedStatement.executeUpdate();

            }
            if (etkilenenSatirSayisi > 0) {
                System.out.println("Kayıtlar başarı ile eklendi");
            } else {
                System.out.println("Kaydetmede sorun yaşandı");
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Bağlantı hatası..." + e.getMessage());
        }
        return false;
    }

}
