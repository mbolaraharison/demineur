package com.example.minesweeper;

public class MineSweeperGame {
    private MineGrid mineGrid;

    public MineSweeperGame(int size)
    {
        mineGrid = new MineGrid(size);
    }

    public MineGrid getMineGrid() {
        return mineGrid;
    }
}
