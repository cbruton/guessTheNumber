package com.example.GuessTheNumber.Model;

import java.util.List;

/**
 *
 * @author cole
 */
public interface RoundDao {
    
    //PUBLIC METHODS
    //c - create
    Round createRound(Round round);
    
    //NOT NEEDED BUT STILL WANTS! - useful for unit testing
    //R - read one
    Round getRoundByID(int ID);
    //R - read all
    List<Round> getAllRounds(int gameID);
    //U - update
    void updateRound(int ID);
    //D - delete
    void removeRound(int ID);
    
}