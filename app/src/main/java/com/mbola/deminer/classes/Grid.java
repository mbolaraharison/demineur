package com.mbola.deminer.classes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mbola.deminer.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {
    private List<Cell> cells;
    private int bombsNumber;

    public Grid(Context context, View templateView, ViewGroup viewGroup, int size, int bombsNumber) {
        this.bombsNumber = bombsNumber;
        this.cells = new ArrayList<>(size*size);

        for (int i = 0; i<size*size; i++) {
            this.cells.add(new Cell(context, i));
            // This is for all first cells of each line except the first one
            if (i != 0 && i%size == 0) {
                this.cells.get(i).setX(this.cells.get(i-size).getX());
                this.cells.get(i).setY(this.cells.get(i-size).getY()-40+this.cells.get(i-size).getDiameter(context));
                this.cells.get(i).setNeighbours(new Cell[] {
                        null,
                        this.cells.get(i-size),
                        null,
                        null,
                        null,
                        null
                });
                this.cells.get(i-size).setBottomNeighbour(this.cells.get(i));
            }
            // This is for all cells at even position
            if (i%size != 0 && (i%size)%2 == 1) {
                // TopRight
                this.cells.get(i).setX(this.cells.get(i-1).getX()+20+(this.cells.get(i-1).getDiameter(context)/2));
                this.cells.get(i).setY(this.cells.get(i-1).getY()+20-(this.cells.get(i-1).getDiameter(context)/2));
                if (i<size) {
                    this.cells.get(i).setNeighbours(new Cell[] {
                            null,
                            null,
                            null,
                            null,
                            null,
                            this.cells.get(i-1)
                    });
                    this.cells.get(i-1).setTopRightNeighbour(this.cells.get(i));
                }
                if (i>size) {
                    this.cells.get(i).setNeighbours(new Cell[] {
                            this.cells.get(i-size-1),
                            this.cells.get(i-size),
                            i%size != 9 ? this.cells.get(i-size+1) : null,
                            null,
                            null,
                            this.cells.get(i-1)
                    });
                    this.cells.get(i-1).setTopRightNeighbour(this.cells.get(i));
                    this.cells.get(i-size-1).setBottomRightNeighbour(this.cells.get(i));
                    this.cells.get(i-size).setBottomNeighbour(this.cells.get(i));
                    if (i%size != 9) {
                        this.cells.get(i-size+1).setBottomLeftNeighbour(this.cells.get(i));
                    }
                }
            }
            if (i%size != 0 && (i%size)%2 == 0) {
                this.cells.get(i).setX(this.cells.get(i-1).getX()+20+(this.cells.get(i-1).getDiameter(context)/2));
                this.cells.get(i).setY(this.cells.get(i-1).getY()-20+(this.cells.get(i-1).getDiameter(context)/2));
                if (i<size) {
                    this.cells.get(i).setNeighbours(new Cell[] {
                            this.cells.get(i-1),
                            null,
                            null,
                            null,
                            null,
                            null
                    });
                    this.cells.get(i-1).setBottomRightNeighbour(this.cells.get(i));
                }
                if (i>size) {
                    this.cells.get(i).setNeighbours(new Cell[] {
                            this.cells.get(i-1),
                            this.cells.get(i-size),
                            null,
                            null,
                            null,
                            null
                    });
                    this.cells.get(i-1).setBottomRightNeighbour(this.cells.get(i));
                    this.cells.get(i-size).setBottomNeighbour(this.cells.get(i));
                }
            }
            viewGroup.addView(this.cells.get(i).getPolygonImageView(), templateView.getLayoutParams());
        }
        // Put bombs
        this.putBombs(bombsNumber);
        // Count neighbouring bombs per cell
        this.countNeighbouringBombsPerCell();
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public int getBombsNumber() {
        return bombsNumber;
    }

    public void setBombsNumber(int bombsNumber) {
        this.bombsNumber = bombsNumber;
    }

    public void putBombs(int number) {
        for (int i=0; i<number; i++) {
            int j = new Random().nextInt(this.cells.size());
            while (this.cells.get(j).isHasBomb()) {
                j = new Random().nextInt(this.cells.size());
            }
            this.cells.get(j).setHasBomb(true);
        }
    }

    public void countNeighbouringBombsPerCell() {
        for (int i=0; i<this.cells.size(); i++) {
            if (!this.cells.get(i).isHasBomb()) {
                Cell[] neighbours = this.cells.get(i).getNeighbours();
                int counter = 0;
                for (int j=0; j<neighbours.length; j++) {
                    if (neighbours[j] != null) {
                        if (neighbours[j].isHasBomb()) {
                            counter++;
                        }
                    }
                }
                if (counter != 0) {
                    this.cells.get(i).setNeighbouringBombsNumber(counter);
                }
            }
        }
    }

    public boolean isGameWon() {
        int counterRevealed = 0;
        int counterBombsRevealed = 0;
        for (int i=0; i<this.cells.size(); i++) {
            if (!this.cells.get(i).isRevealed()) {
                counterRevealed++;
            }
            if (this.cells.get(i).isRevealed() && this.cells.get(i).isHasBomb()) {
                counterBombsRevealed++;
            }
        }
        if (counterRevealed == this.bombsNumber && counterBombsRevealed == 0) {
            return true;
        }
        return false;
    }
}
