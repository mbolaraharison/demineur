package com.example.minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineGrid {
    private List<Cell> cells; // ListofCells
    private int size; // number os cells in lines and columns

    public MineGrid(int size)
    {
        this.size =  size;
        cells = new ArrayList<Cell>();
        for(int i=0; i<size*size;i++){
            cells.add(new Cell(Cell.BLANK));
        }
    }

    public void generateGrid(int nbrBombs){
        int bombsPlaced = 0;
        // Place Bombs
        while (bombsPlaced < nbrBombs){
            // Calculate random coordiantes to place the bombs and pass the size as a maximum value
            int x = new Random().nextInt(size);
            int y = new Random().nextInt(size);
            // Convert coordinates to index
            int index = coordsToIndex(x,y);
            // Check if the cell is empty
            if(cells.get(index).getValue() == Cell.BLANK)
            {
                // Place the Bomb by overriding the cell
                cells.set(index,new Cell(Cell.BOMB));
                // Increment
                bombsPlaced++;
            }

        }

        for(int x=0; x<size;x++)
        {
            for(int y=0; y<size;y++){
                // Check is the cell is not a Bomb
                if(cellAtCoords(x,y).getValue() != Cell.BOMB){
                    // get the list of the adjacent cells
                    List<Cell> adjacentCells = adjacentCells(x,y);
                    // count the number of bombs around
                    int countBombs = 0;
                    for(Cell c: adjacentCells){
                        if(c.getValue() == Cell.BOMB)
                        {
                            countBombs++;
                        }
                    }
                    if(countBombs > 0){
                        cells.set(x + (y*size),new Cell(countBombs));
                    }

                }
            }
        }
    }

    public List<Cell> getCells() {
        return cells;
    }

    // Return the appropriate cell index using x , y coordinate ( x and y starts from 0 )
    public int coordsToIndex(int x, int y){
        return x + (y*size);
    }

    public Cell cellAtCoords(int x, int y){
        if(x < 0 || x >= size || y < 0 || y >= size)
        {
            return null;
        }
        return cells.get(coordsToIndex(x,y));
    }

    public int[] indextoCoords(int index){
        int y = index/size;  // y est la partie entière
        int x = index - y*size;
        return new int[]{x,y};
    }

    public List<Cell> adjacentCells(int x,int y){
        List<Cell> adjacentCells = new ArrayList<>();
        List<Cell> listCells = new ArrayList<>();

        // Check all the surronding cells if they exist
        listCells.add(cellAtCoords(x-1,y));
        listCells.add(cellAtCoords(x+1,y));
        listCells.add(cellAtCoords(x,y-1));
        listCells.add(cellAtCoords(x,y+1));
        listCells.add(cellAtCoords(x+1,y+1));
        listCells.add(cellAtCoords(x-1,y-1));
        listCells.add(cellAtCoords(x+1,y-1));
        listCells.add(cellAtCoords(x-1,y+1));

        // Add the adjacent cells to the list
        for(Cell c:listCells)
        {
            if(c != null)
            {
                adjacentCells.add(c);
            }
        }

        return adjacentCells;
    }


    public void showAllBombs()
    {
        for(Cell c:cells)
        {
            if(c.getValue() == Cell.BOMB)
            {
                c.setRevealed(true);
            }
        }
    }
}
