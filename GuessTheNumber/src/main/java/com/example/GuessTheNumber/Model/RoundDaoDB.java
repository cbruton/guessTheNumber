package com.example.GuessTheNumber.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class RoundDaoDB implements RoundDao {

    @Autowired
    JdbcTemplate jdbc;
    
    public final class RoundMapper 
            implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int rowNum) 
                throws SQLException {
            Round round = new Round();
            round.setRoundID(rs.getInt("roundID"));
            round.setMyGame(getGame(rs.getInt("gameID")));
            round.setGuess(rs.getString("guess"));
            Timestamp timeStamp = rs.getTimestamp("guessTime");
            round.setGuessTime(timeStamp.toLocalDateTime());
            round.setResult(rs.getString("result"));
            return round;
        }
        
    }
    //Helper Method
    public Game getGame(int ID) {
        final String GET_SQL = "SELECT * FROM game "
                + "WHERE gameID = ?;";
        return jdbc.queryForObject(GET_SQL, new GameDaoDB.GameMapper(), ID);
    }
    
    @Override
    public Round createRound(Round round) {
//        Date date = new Date();
//        SimpleDateFormat format = new           SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;   
//        String currentDateTime = format.format(date);
        final String sql = "INSERT INTO round(gameID, guess, result) VALUES(?,?,?);";
        jdbc.update(sql, round.getMyGame().getID(), round.getGuess(), round.getResult());
        
        int newRoundID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundID(newRoundID);
        return round;
    }

    @Override
    public Round getRoundByID(int roundID) {
        final String GET_SQL = "SELECT * FROM round "
                + "WHERE roundID = ?;";
        return jdbc.queryForObject(GET_SQL, new RoundMapper(), roundID);
    }

    @Override
    public List<Round> getAllRounds(int gameID) {
        final String GET_ALL_SQL = "SELECT * FROM round "
                + "WHERE gameID = ?;";
        return jdbc.query(GET_ALL_SQL, new RoundMapper(), gameID);
    }

    @Override
    public void updateRound(int ID) {
        Round round = getRoundByID(ID);
        final String UPDATE_SQL = "UPDATE round SET guess = ?, guessTime = ?"
                + "WHERE roundID = ?";
        jdbc.update(UPDATE_SQL,
                round.getGuess(),
                round.getGuessTime(), ID);
    }
    //Need to work on a remove method. 
    @Override
    public void removeRound(int ID) {
        final String REMOVE_SQL = "DELETE FROM round WHERE roundID = ?";
        jdbc.update(REMOVE_SQL, ID);
    }

}