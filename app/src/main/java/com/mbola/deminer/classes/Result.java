package com.mbola.deminer.classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Result {

    private String date;
    private int level;
    private int score;

    public Result(int level, int score) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = formatter.format(new Date());
        this.level = level;
        this.score = score;
    }

    public Result(String date, int level, int score) {
        this.date = date;
        this.level = level;
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
