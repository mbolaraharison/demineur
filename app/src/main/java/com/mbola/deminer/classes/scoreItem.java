package com.mbola.deminer.classes;

public class scoreItem {
    private String date;
    private String score;

    public scoreItem(String name,String score){
        this.date = name;
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public String getScore() {
        return score;
    }
}
