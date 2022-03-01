package com.example.GuessTheNumber.ServiceLayer;

import com.example.GuessTheNumber.Model.Game;
import com.example.GuessTheNumber.Model.Round;
import java.util.List;

/**
 *
 * @author cole
 */
public interface ServiceLayer {
    
    public int createGame();    

    public Round createRound(Game game, String guess); 
    
    public List<Game> getAllGames(); //List all games but not show answer for inProgress
    
    public Game getGame(int gameID); //BUT not show anser for game inProgress
    public Game getGameByID(int gameID); //BUT not show anser for game inProgress
    public List<Round> getRoundsforGame(int gameID);
//    public String getRoundForGame(Round round);
    
}
