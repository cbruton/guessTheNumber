package com.example.GuessTheNumber.Model;

/**
 *
 * @author cole
 */
public class Game {
    
    private int ID;
    private String answer;
    private boolean inProgress;

    public Game() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }
    
}
