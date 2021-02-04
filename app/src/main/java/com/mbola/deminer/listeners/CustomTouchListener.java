package com.mbola.deminer.listeners;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.mbola.deminer.MainActivity;
import com.mbola.deminer.classes.Cell;
import com.mbola.deminer.classes.Grid;
import com.mbola.deminer.views.PolygonImageView;

public class CustomTouchListener implements View.OnTouchListener {

    private Grid grid;
    private Cell cell;
    private MainActivity activity;

    public CustomTouchListener(MainActivity activity, Cell cell) {
        this.cell = cell;
        this.activity = activity;
        this.grid = this.activity.getGrid();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (((PolygonImageView)v).isAreaClickable(event) && !this.activity.isGameOver()) {
            // Start the timer if it has not already been started
            if(!this.activity.isTimerStarted())
            {
                // Start the count down
                this.activity.getCounter().start();
                this.activity.setTimerStarted(true);
            }

            if (!cell.isRevealed()) {
                if (cell.isHasBomb()) {
                    this.activity.getCounter().cancel();
                    this.cell.setRevealed(true);
                    this.cell.setColor(Color.RED);
                    this.activity.setGameOver(true);
                } else {
                    cell.revealEmptiesRecursively();
                }
            }
            System.out.println("ID : "+cell.getPolygonImageView().getId()+" IS REVEALED : "+cell.isRevealed()+" HAS BOMB : "+cell.isHasBomb());
        };

        if (grid.isGameWon()) {
            this.activity.getCounter().cancel();
            this.activity.setGameWon(true);
            for (int i = 0; i < this.grid.getCells().size(); i++) {
                if (this.grid.getCells().get(i).isHasBomb()) {
                    this.grid.getCells().get(i).setColor(Color.GREEN);
                }
            }
        }
        return true;
    }
}
