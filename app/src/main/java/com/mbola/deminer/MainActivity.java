package com.mbola.deminer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.MatrixCursor;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.mbola.deminer.classes.Grid;
import com.mbola.deminer.classes.customPopUp;
import com.mbola.deminer.classes.scoreItem;
import com.mbola.deminer.classes.scoreItemAdapter;
import com.mbola.deminer.listeners.CustomClickListener;
import com.mbola.deminer.listeners.CustomTouchListener;
import com.mbola.deminer.listeners.ResultsListListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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


    private Button scoreButton;
    private MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.activity = this;




        LEVEL_PARAMETERS = new HashMap<>();
        LEVEL_PARAMETERS.put(1, new int[]{60000, 8, 5});
        LEVEL_PARAMETERS.put(2, new int[]{40000, 9, 10});
        LEVEL_PARAMETERS.put(3, new int[]{25000, 10, 25});

        playButton = findViewById(R.id.activity_main_smiley);
        scoreButton = findViewById(R.id.Score);

        resultsList = findViewById(R.id.link_results);
        timer = findViewById(R.id.activity_main_timer);


        secondsElapsed = 0;
        playButton.setOnClickListener(new CustomClickListener(this, this, playButton, timer, secondsElapsed, 3));





        scoreButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final customPopUp customPopUp = new customPopUp(activity);
                customPopUp.setScore("Mettre ici le score générer par le chrono");

                customPopUp.getCancel_Button().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Results closed",Toast.LENGTH_SHORT).show();
                        customPopUp.dismiss();
                    }
                });
                customPopUp.build();
            }
        });




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