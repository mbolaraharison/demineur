package com.example.minesweeper;

import java.util.ArrayList;
import java.util.List;

public class MineSweeperGame {
    private MineGrid mineGrid;
    private boolean clearMode;
    private boolean gameIsOver;

    public MineSweeperGame(int size,int nbrOfBombs)
    {
        this.clearMode = true;
        mineGrid = new MineGrid(size);
        // Generate the grids
        mineGrid.generateGrid(nbrOfBombs);
    }
    public void handleCellClick(Cell c)
    {
        // Check if the game is over before clearing any cell
        if(!isGameIsOver())
        {
            if(clearMode)
            {
                clear(c);
            }
        }
    }

    public void clear(Cell cell){
        int index = getMineGrid().getCells().indexOf(cell);
        // Reveal the Value of the cell
        getMineGrid().getCells().get(index).setRevealed(true);

        // Check is the cell is a Bomb
        if(cell.getValue()== Cell.BOMB)
        {
            // Game is Over
            gameIsOver = true;
        }else if(cell.getValue() == Cell.BLANK)
        {
            // Get all the surronding cells that are blank
            List<Cell> cellsToClear = new ArrayList<>();
            List<Cell> toCheckAdjacents = new ArrayList<>();

            // Add the inital cell to check its adjacents
            toCheckAdjacents.add(cell);

            while(toCheckAdjacents.size() > 0)
            {
                Cell c = toCheckAdjacents.get(0);
                // get its index
                int cellIndex = getMineGrid().getCells().indexOf(c);
                // GET THE coords x and y of the cell
                int[] cellCoords = getMineGrid().indextoCoords(cellIndex);
                // Loop through all the adjacent cells of our cell
                for(Cell adjacent:getMineGrid().adjacentCells(cellCoords[0],cellCoords[1])){
                    // Check the value of each adjacent cell
                    if(adjacent.getValue() == Cell.BLANK)
                    {
                        // Check is the cell doesn't exist in the two Lists
                        if (!cellsToClear.contains(adjacent)) {
                            if (!toCheckAdjacents.contains(adjacent)) {
                                // Add it to the arrayList in order to check its adjacents too
                                toCheckAdjacents.add(adjacent);
                            }
                        }
                    } else {
                        if(!cellsToClear.contains(adjacent))
                        {
                            cellsToClear.add(adjacent);
                        }
                    }
                }
                toCheckAdjacents.remove(c);
                cellsToClear.add(c);
            }

            // Reval cells to Clear
            for(Cell c:cellsToClear){
                c.setRevealed(true);
            }

        }
    }
    public MineGrid getMineGrid() {
        return mineGrid;
    }

    public boolean isGameIsOver() {
        return gameIsOver;
    }
}
