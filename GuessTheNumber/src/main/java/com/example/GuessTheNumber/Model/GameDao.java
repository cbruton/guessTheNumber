package com.example.GuessTheNumber.Model;

import java.util.List;

/**
 *
 * @author cole
 */
public interface GameDao {
    
    //PUBLIC METHODS
    //c - create
    Game addGame(Game game);
    //R - read one
    Game getGame(int ID);
    Game getGameByID(int ID);
    //R - read all
    List<Game> getAllGames();
    //U - update
    void updateGame(Game game);
    //D - delete
    boolean removeGame(int ID);
    
}
