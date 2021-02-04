package com.mbola.deminer;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.mbola.deminer.classes.Grid;
import com.mbola.deminer.listeners.CustomClickListener;
import com.mbola.deminer.listeners.CustomTouchListener;
import com.mbola.deminer.services.BackgroundMusicService;

public class MainActivity extends AppCompatActivity {

    private Grid grid;
    private TextView playButton,timer;
    private int secondsElapsed;
    private boolean timerStarted;
    private CountDownTimer counter;

    private boolean isGameWon;
    private boolean isGameOver;
    private Intent musicInent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);
        int width = size.x;
        int height = size.y;

        playButton = findViewById(R.id.activity_main_smiley);
        timer = findViewById(R.id.activity_main_timer);

        playButton.setOnClickListener(new CustomClickListener(playButton, timer, secondsElapsed));

        timerStarted = false;
        isGameOver = false;
        isGameWon = false;

        counter = new CountDownTimer(40000L,1000) {
            // Add one after each second
            @Override
            public void onTick(long l) {
                secondsElapsed+= 1;
                timer.setText(String.format("%03d",secondsElapsed));
            }

            @Override
            public void onFinish() {
                isGameOver = true;
                Toast.makeText(getApplicationContext(),"Game is Over : Time is UP",Toast.LENGTH_SHORT).show();
            }
        };

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layout);

        View templateView = findViewById(R.id.kitty01);

        grid = new Grid(this, templateView, layout, 10, 10);

        for (int i = 0; i < grid.getCells().size(); i++) {
            grid.getCells().get(i).getPolygonImageView().setOnTouchListener(new CustomTouchListener(grid, grid.getCells().get(i)));
        }

        // Handle music background
        musicInent = new Intent(getApplicationContext(), BackgroundMusicService.class);
        startService(new Intent(getApplicationContext(),BackgroundMusicService.class));
    }


}