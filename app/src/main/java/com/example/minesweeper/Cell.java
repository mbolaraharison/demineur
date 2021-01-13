package com.example.minesweeper;

public class Cell {
    // Static Constants can be accesses from outside
    public static final int BOMB = -1;
    public static final int BLANK = 0;

    private int value;
    private boolean isFlagged;
    private boolean isRevealed;

    public Cell(int value)
    {
        this.value = value;
        this.isFlagged = false;
        this.isRevealed = false;
    }

    public int getValue() {
        return value;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

}
