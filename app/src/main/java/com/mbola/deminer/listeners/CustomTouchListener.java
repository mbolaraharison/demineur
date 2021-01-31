package com.mbola.deminer.listeners;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.mbola.deminer.classes.Cell;
import com.mbola.deminer.classes.Grid;
import com.mbola.deminer.views.PolygonImageView;

public class CustomTouchListener implements View.OnTouchListener {

    private Grid grid;
    private Cell cell;
    private boolean isGameWon;
    private boolean isGameOver;

    public CustomTouchListener(Grid grid, Cell cell) {
        this.grid = grid;
        this.cell = cell;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (((PolygonImageView)v).isAreaClickable(event)) {
            if (!cell.isRevealed()) {
                if (cell.isHasBomb()) {
                    this.cell.setRevealed(true);
                    this.cell.setColor(Color.RED);
                    isGameOver = true;
                } else {
                    cell.revealEmptiesRecursively();
                }
            }
            System.out.println("ID : "+cell.getPolygonImageView().getId()+" IS REVEALED : "+cell.isRevealed()+" HAS BOMB : "+cell.isHasBomb());
        };
        isGameWon = grid.isGameWon();
        if (isGameWon) {
            for (int i = 0; i < this.grid.getCells().size(); i++) {
                if (this.grid.getCells().get(i).isHasBomb()) {
                    this.grid.getCells().get(i).setColor(Color.GREEN);
                }
            }
        }
        System.out.println("GAME WON : "+isGameWon+" GAME OVER : "+isGameOver);
        return true;
    }
}
