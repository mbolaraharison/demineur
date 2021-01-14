package com.example.minesweeper;

public class Cell {
    // Static Constants can be accesses from outside
    public static final int BOMB = -1;
    public static final int BLANK = 0;
    private int valueCell; // The number of bombs around this cell ( touching this cell )
    private boolean isRevealed;

    // TODO : Add the possibily to add a flag in a cell
    private boolean isFlagged;

    public Cell(int value)
    {
        this.valueCell = value;
        this.isFlagged = false;
        this.isRevealed = false;
    }

    public int getValue() {
        return valueCell;
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
