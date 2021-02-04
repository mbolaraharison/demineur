package com.mbola.deminer;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.mbola.deminer.classes.Grid;
import com.mbola.deminer.listeners.CustomClickListener;
import com.mbola.deminer.listeners.CustomTouchListener;
import com.mbola.deminer.listeners.ResultsListListener;

import java.util.HashMap;

import services.Service;

public class MainActivity extends AppCompatActivity {

    private Grid grid;
    private TextView playButton,timer, resultsList;
    private int secondsElapsed;
    private boolean timerStarted;
    private CountDownTimer counter;
    public static HashMap<Integer, int[]> LEVEL_PARAMETERS;

    private boolean isGameWon;
    private boolean isGameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LEVEL_PARAMETERS = new HashMap<>();
        LEVEL_PARAMETERS.put(1, new int[]{60000, 8, 5});
        LEVEL_PARAMETERS.put(2, new int[]{40000, 9, 10});
        LEVEL_PARAMETERS.put(3, new int[]{25000, 10, 25});

        playButton = findViewById(R.id.activity_main_smiley);
        resultsList = findViewById(R.id.link_results);
        timer = findViewById(R.id.activity_main_timer);

        secondsElapsed = 0;
        playButton.setOnClickListener(new CustomClickListener(this, this, playButton, timer, secondsElapsed, 3));

        resultsList.setOnClickListener(new ResultsListListener(this));

        timerStarted = false;
        isGameOver = false;
        isGameWon = false;
    }

    public int[] getWindowDimensions() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);
        int[] dimensions = new int[]{size.x, size.y};
        return dimensions;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public TextView getPlayButton() {
        return playButton;
    }

    public void setPlayButton(TextView playButton) {
        this.playButton = playButton;
    }

    public TextView getTimer() {
        return timer;
    }

    public void setTimer(TextView timer) {
        this.timer = timer;
    }

    public int getSecondsElapsed() {
        return secondsElapsed;
    }

    public void setSecondsElapsed(int secondsElapsed) {
        this.secondsElapsed = secondsElapsed;
    }

    public boolean isTimerStarted() {
        return timerStarted;
    }

    public void setTimerStarted(boolean timerStarted) {
        this.timerStarted = timerStarted;
    }

    public CountDownTimer getCounter() {
        return counter;
    }

    public void setCounter(CountDownTimer counter) {
        this.counter = counter;
    }

    public boolean isGameWon() {
        return isGameWon;
    }

    public void setGameWon(boolean gameWon) {
        isGameWon = gameWon;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}