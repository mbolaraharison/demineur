package com.mbola.deminer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mbola.deminer.classes.Grid;
import com.mbola.deminer.classes.Result;
import com.mbola.deminer.listeners.CustomClickListener;
import com.mbola.deminer.listeners.CustomSpinnerSelectListener;
import com.mbola.deminer.listeners.ResultsListListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import services.Service;
import com.mbola.deminer.services.BackgroundMusicService;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private Grid grid;
    private TextView bombsNumber, playButton,timer, gameStatus, resultsList;
    private int secondsElapsed;
    private boolean timerStarted;
    private CountDownTimer counter;
    public static HashMap<Integer, int[]> LEVEL_PARAMETERS;

    private boolean isGameWon;
    private boolean isGameOver;
    private Intent musicInent;

    private Spinner levelsSpinner;
    private int selectedLevel = 1;

    private SQLiteDatabase db;
    public static String DB_NAME = "Results";
    public static String TABLE_NAME = "results_table";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LEVEL_PARAMETERS = new HashMap<>();
        LEVEL_PARAMETERS.put(1, new int[]{60000, 8, 5});
        LEVEL_PARAMETERS.put(2, new int[]{40000, 9, 10});
        LEVEL_PARAMETERS.put(3, new int[]{25000, 10, 25});

        bombsNumber = findViewById(R.id.activity_main_bombs_number);
        playButton = findViewById(R.id.activity_main_smiley);
        gameStatus = findViewById(R.id.game_status_label);
        resultsList = findViewById(R.id.results_list_label);
        timer = findViewById(R.id.activity_main_timer);
        levelsSpinner = findViewById(R.id.levels_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.level_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        levelsSpinner.setAdapter(adapter);
        levelsSpinner.setOnItemSelectedListener(new CustomSpinnerSelectListener(this));

        secondsElapsed = 60;
        playButton.setOnClickListener(new CustomClickListener(this, playButton, timer, secondsElapsed));

        resultsList.setOnClickListener(new ResultsListListener(this));

        timerStarted = false;
        isGameOver = false;
        isGameWon = false;

        // Handle music background
        musicInent = new Intent(getApplicationContext(), BackgroundMusicService.class);
        startService(new Intent(getApplicationContext(),BackgroundMusicService.class));

        // Open or create database
        this.db = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);

        Service.addResultToDb(this, new Result(1, 45));

        //Service.truncateTable(this);

        List<Result> res = Service.getAllResultsFromDb(this);

        for (int i=0; i<res.size(); i++) {
            System.out.println("DATE : "+res.get(i).getDate()+" LEVEL : "+res.get(i).getLevel()+" SCORE : "+res.get(i).getScore());
        }
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

    public Intent getMusicInent() {
        return musicInent;
    }

    public void setMusicInent(Intent musicInent) {
        this.musicInent = musicInent;
    }

    public Spinner getLevelsSpinner() {
        return levelsSpinner;
    }

    public void setLevelsSpinner(Spinner levelsSpinner) {
        this.levelsSpinner = levelsSpinner;
    }

    public int getSelectedLevel() {
        return selectedLevel;
    }

    public void setSelectedLevel(int selectedLevel) {
        this.selectedLevel = selectedLevel;
    }

    public TextView getBombsNumber() {
        return bombsNumber;
    }

    public void setBombsNumber(TextView bombsNumber) {
        this.bombsNumber = bombsNumber;
    }

    public TextView getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(TextView gameStatus) {
        this.gameStatus = gameStatus;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }
}