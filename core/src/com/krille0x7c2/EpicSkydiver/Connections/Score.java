package com.krille0x7c2.EpicSkydiver.Connections;

public class Score {
    private int Score;
    private int id;

    public Score(int id, int score) {
        Score = score;
        this.id = id;
    }

    public Score() {

    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}