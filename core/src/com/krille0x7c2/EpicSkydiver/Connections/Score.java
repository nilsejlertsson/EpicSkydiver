package com.krille0x7c2.EpicSkydiver.Connections;

/**
 * Created by Christian Bodelsson
 */
public class Score {
    private int score;
    private int id;

    public Score(int id, int score) {
        this.score = score;
        this.id = id;
    }

    public Score() {

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}