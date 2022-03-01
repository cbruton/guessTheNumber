package com.example.GuessTheNumber.Controller;

import com.example.GuessTheNumber.ServiceLayer.ServiceLayer;
import com.example.GuessTheNumber.Model.Game;
import com.example.GuessTheNumber.Model.Round;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cole
 */
@RestController
public class Controller {

    @Autowired
    ServiceLayer service;

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int createNewGame(){
        return service.createGame();
    }
    
    //Must return object without showing the answer.
    @PostMapping("/guess") //make a new round. 
    @ResponseStatus(HttpStatus.CREATED)
    public Round makeAGuess(String guess, int gameID) {
        Game game = service.getGame(gameID);
        Round round = service.createRound(game, guess);
        return round;
    }
    
    @GetMapping("/game")
    public List<Game> getAllGames() {
        return service.getAllGames();
    }
    
    @GetMapping("/game/{gameID}")
    public Game getGameByID(int gameID) {
        return service.getGameByID(gameID);
    }
    
    @GetMapping("/rounds/{gameID}")
    public List<Round> getRoundsByID(int gameID) {
        return service.getRoundsforGame(gameID);
    }
    
    
}