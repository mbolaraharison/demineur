package com.example.minesweeper;

import java.util.ArrayList;
import java.util.List;

public class MineGrid {
    private List<Cell> cells;
    private int size; // number os cells in lines and columns

    public MineGrid(int size)
    {
        this.size =  size;
        cells = new ArrayList<Cell>();
        for(int i=0; i<size*size;i++){
            cells.add(new Cell(Cell.BLANK));
        }
    }

    public List<Cell> getCells() {
        return cells;
    }
}
