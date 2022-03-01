package com.example.GuessTheNumber.ServiceLayer;

import com.example.GuessTheNumber.Model.Game;
import com.example.GuessTheNumber.Model.GameDao;
import com.example.GuessTheNumber.Model.Round;
import com.example.GuessTheNumber.Model.RoundDao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cole
 */
@Service
public class ServiceLayerImpl implements ServiceLayer {
    
    @Autowired
    GameDao gameDao;
    @Autowired
    RoundDao roundDao;
    
    @Override
    public int createGame() {
        Game game = new Game();
        game.setAnswer(randomizeAnswer());
        game = gameDao.addGame(game);
        return game.getID();
    }
    
    public String randomizeAnswer() {
        Random rand = new Random();
        String answer = "";
        // Create a list to keep track of numbers.
        List<String> randList = new ArrayList<>();
        // While loop to add numbers to list up to 4 max.
        while (randList.size() < 4) {
            // Create random number and store as string.
            String num = String.valueOf(rand.nextInt(10));
            // Determine if the list already contains the number. 
            // If not in list, add to list.
            if (!randList.contains(num)) {
                randList.add(num);
            }
        }
        // for each loop to add each number to a string.
        for (String item : randList) {
            answer += item;
        }
        return answer;
    } 
    
    @Override
    public Round createRound(Game game, String guess) {
        Round round = new Round(guess, game);
        String answer = game.getAnswer();
        round.setResult(determineGuess(guess, answer));
        if (guess.equals(answer)) {
            game.setInProgress(false);
            gameDao.updateGame(game);
        }
        round = roundDao.createRound(round);
        // Hide the answer if game is still in progress.
        hideAnswerGame(game);
        return round;
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAllGames();
        // Hide the answer if game is still in progress.
        for (Game game : games) {
            hideAnswerGame(game);
        }
        return games;
    }

    @Override
    public Game getGame(int gameID) {
        Game game = gameDao.getGameByID(gameID);
        return game;
    }
    
    @Override
    public Game getGameByID(int gameID) {
        Game game = gameDao.getGameByID(gameID);
        // Hide the answer if game is still in progress.
        hideAnswerGame(game);
        return game;
    }
    
    @Override
    public List<Round> getRoundsforGame(int gameID) {
        List<Round> rounds = roundDao.getAllRounds(gameID);
        for (Round round : rounds) {
            // Hide the answer if game is still in progress.
            if (round.getMyGame().isInProgress()) {
                round.getMyGame().setAnswer("****");
            }
        }
        return rounds;
    }
    
    // Helper method.
    public void hideAnswerGame(Game game) {
        if (game.isInProgress()) {
            game.setAnswer("****");
        }
    }
    
    public String determineGuess(String guess, String answer) {
        String[] split1 = guess.split("");
        String[] split2 = answer.split("");
        int E = 0;
        int P = 0;
        List<String> guessList = new ArrayList<>(Arrays.asList(split1));
        List<String> ansList = new ArrayList<>(Arrays.asList(split2));
        // Loop to determine if each element of guess is in answer.
        for (int i = 0; i < guessList.size(); i++) {
            if (ansList.contains(guessList.get(i))) {
                // If statement to determine if each element is a match or parital.
                if (ansList.get(i).equals(guessList.get(i))) {
                    E++;
                } else {
                    P++;
                }
            }
        }
        String result = "E:" + E + ":P:" + P;
        
        return result;
    }
}
