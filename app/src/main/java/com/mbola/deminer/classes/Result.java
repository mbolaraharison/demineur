package com.mbola.deminer.classes;

import java.util.Date;

public class Result {

    private Date date;
    private int score;

    public Result(Date date, int score) {
        this.date = date;
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
