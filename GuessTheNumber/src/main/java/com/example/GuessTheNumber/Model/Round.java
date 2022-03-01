package com.example.GuessTheNumber.Model;

import java.time.LocalDateTime;

/**
 *
 * @author cole
 */
public class Round {
    
    int roundID;
    String guess;
    LocalDateTime guessTime;
    String result;
    Game myGame;

    public Round() {
    }

    public Round(String guess, Game myGame) {
        this.guess = guess;
        this.myGame = myGame;
    }

    public int getRoundID() {
        return roundID;
    }

    public void setRoundID(int roundID) {
        this.roundID = roundID;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public LocalDateTime getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(LocalDateTime guessTime) {
        this.guessTime = guessTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public Game getMyGame() {
        return myGame;
    }

    public void setMyGame(Game myGame) {
        this.myGame = myGame;
    }

}
