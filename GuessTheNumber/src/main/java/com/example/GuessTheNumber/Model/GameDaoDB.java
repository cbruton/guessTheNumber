package com.example.GuessTheNumber.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cole
 */
@Repository
public class GameDaoDB implements GameDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Game addGame(Game game) {
        final String CREATE_SQL = "INSERT INTO game(answer) VALUES (?)";
        jdbc.update(CREATE_SQL, game.getAnswer());
        // use select last_inserted_id to return the last id number through autoincrement sql.
        int newGameID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class); // return it as an integer.
        game.setID(newGameID); // set the new game's id.
        return game;
    }

    @Override
    public Game getGame(int ID) {
        final String GET_SQL = "SELECT gameID, inProgress "
                + "FROM game "
                + "WHERE gameID = ?;";
        return jdbc.queryForObject(GET_SQL, new GameMapper(), ID);
    }
    
    @Override
    public Game getGameByID(int ID) {
        final String GET_SQL = "SELECT * FROM game "
                + "WHERE gameID = ?;";
        return jdbc.queryForObject(GET_SQL, new GameMapper(), ID);
    }

    @Override
    public List<Game> getAllGames() {
        final String GET_ALL_SQL = "SELECT * FROM game;";
        return jdbc.query(GET_ALL_SQL, new GameMapper());
    }

    @Override
    public void updateGame(Game game) {
        final String UPDATE_SQL = "UPDATE game SET "
                + "inProgress = ? WHERE gameID = ?;";
        jdbc.update(UPDATE_SQL, game.isInProgress(), game.getID());
    }

    @Override
    public boolean removeGame(int ID) {
        final String REMOVE_SQL = "DELETE FROM game "
                + "WHERE gameID = ?;";
        return jdbc.update(REMOVE_SQL, ID) > 0;
    }
    
    public static final class GameMapper 
            implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int rowNum) 
                throws SQLException {
            Game game = new Game();
            game.setID(rs.getInt("gameID"));
            game.setAnswer(rs.getString("answer"));
            game.setInProgress(rs.getBoolean("inProgress"));
            return game;
        }
        
    }
}
