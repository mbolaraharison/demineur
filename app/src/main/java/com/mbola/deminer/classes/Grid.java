package com.mbola.deminer.classes;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.mbola.deminer.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import services.Service;

public class Grid {
    private List<Cell> cells;
    private int bombsNumber;

    public Grid(Context context, View templateView, int[] windowDimensions, ViewGroup viewGroup, int size, int bombsNumber) {
        // Remove views if they already exist
        int id = 1;
        while (((ConstraintLayout)viewGroup).getViewById(id) != null) {
            viewGroup.removeView(((ConstraintLayout)viewGroup).getViewById(id));
            id++;
        }

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
                            i%size != (size-1) ? this.cells.get(i-size+1) : null,
                            null,
                            null,
                            this.cells.get(i-1)
                    });
                    this.cells.get(i-1).setTopRightNeighbour(this.cells.get(i));
                    this.cells.get(i-size-1).setBottomRightNeighbour(this.cells.get(i));
                    this.cells.get(i-size).setBottomNeighbour(this.cells.get(i));
                    if (i%size != (size-1)) {
                        this.cells.get(i-size+1).setBottomLeftNeighbour(this.cells.get(i));
                    }
                }
            }
            // This is for all cells at odd position
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
            float gridWidth = this.getGridWidth(context, size);
            int leftMargin = (int) ((windowDimensions[0]- (int) gridWidth)/2);

            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) templateView.getLayoutParams();
            layoutParams.topMargin = 100;
            layoutParams.leftMargin = leftMargin;
            //this.cells.get(i).getPolygonImageView().setVisibility(View.GONE);

            viewGroup.addView(this.cells.get(i).getPolygonImageView(), layoutParams);
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

    public float getGridWidth(Context context, int size) {
        return ((Service.toPixel(context, 50)*size)-(Service.toPixel(context, 20)*(size-2)));
    }

    //public float getGridHeight(Context context, int size)

}
