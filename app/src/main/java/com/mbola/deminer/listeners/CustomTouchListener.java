package com.mbola.deminer.listeners;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

import com.mbola.deminer.MainActivity;
import com.mbola.deminer.R;
import com.mbola.deminer.classes.Cell;
import com.mbola.deminer.classes.Grid;
import com.mbola.deminer.classes.Result;
import com.mbola.deminer.views.PolygonImageView;

import services.Service;

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
                // Set game status to STARTED
                this.activity.getGameStatus().setText(R.string.game_status_started);

                this.grid.putBombsExceptAtPosition((MainActivity.LEVEL_PARAMETERS.get(this.activity.getSelectedLevel()))[2], this.grid.getCells().indexOf(this.cell));
                this.grid.countNeighbouringBombsPerCell();
            }

            if (!cell.isRevealed()) {
                if (cell.isHasBomb()) {
                    this.activity.getCounter().cancel();
                    this.activity.setGameOver(true);
                    activity.getGrid().revealBombs(false);
                    this.activity.getGameStatus().setText(R.string.game_status_over);
                    activity.getGameStatus().setTextColor(Color.RED);
                } else {
                    cell.revealEmptiesRecursively();
                }
            }
            System.out.println("ID : "+cell.getPolygonImageView().getId()+" IS REVEALED : "+cell.isRevealed()+" HAS BOMB : "+cell.isHasBomb());
        };

        if (grid.isGameWon() && !this.activity.isGameWon()) {
            this.activity.getCounter().cancel();
            this.activity.setGameWon(true);
            Service.addResultToDb(this.activity, new Result(this.activity.getSelectedLevel(), this.activity.getSecondsElapsed()));
            this.activity.setResultsList(Service.getAllResultsFromDb(this.activity));
            // Set game status to WON
            activity.getGrid().revealBombs(true);
            this.activity.getGameStatus().setText(R.string.game_status_won);
            activity.getGameStatus().setTextColor(Color.GREEN);
        }
        return true;
    }
}
