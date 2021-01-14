package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnCellClickListener {
    RecyclerView gridRecyclerView;
    MineGridRecyclerAdapter mineGridRecyclerAdapter;
    MineSweeperGame mineSweeperGame;
    TextView face,timer;
    CountDownTimer counter;
    int secondsElapsed; // Count the seconds Elapsed
    boolean timerStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        face = findViewById(R.id.activity_main_smiley);
        // Replay the game
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mineSweeperGame = new MineSweeperGame(10, 10);
                timer.setText(String.format("%03d",secondsElapsed));
                mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());
            }
        });

        // SET UP the Timer
        timer = findViewById(R.id.activity_main_timer);
        timerStarted = false;

        // 40 seconds to play the game : ( Normal Level ) ,  60 seconds  ( Easy level ) , 25 seconds : ( Advanced Level )
        counter = new CountDownTimer(40000L,1000) {
            // Add one after each second
            @Override
            public void onTick(long l) {
                secondsElapsed+= 1;
                timer.setText(String.format("%03d",secondsElapsed));
            }

            @Override
            public void onFinish() {
                mineSweeperGame.timeExpired();
                Toast.makeText(getApplicationContext(),"Game is Over : Time is UP",Toast.LENGTH_SHORT).show();
                mineSweeperGame.getMineGrid().showAllBombs();
                // Show updated version of the Grid
                mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());
            }
        };




        gridRecyclerView = findViewById(R.id.activity_main_grid);
        gridRecyclerView.setLayoutManager(new GridLayoutManager(this, 10));
        mineSweeperGame = new MineSweeperGame(10, 10);
        // Pass the cells and "this" click listener
        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(mineSweeperGame.getMineGrid().getCells(),this);
        // Set the recycler adapter
        gridRecyclerView.setAdapter(mineGridRecyclerAdapter);

    }


    @Override
    public void onCellClick(Cell cell) {

       // Toast.makeText(getApplicationContext(),"cell clicked",Toast.LENGTH_SHORT).show();

        // Check if the timer wasn't started
        if(!timerStarted)
        {
            // Start the count down
            counter.start();
            timerStarted = true;
        }
        // Handle Cell click
        mineSweeperGame.handleCellClick(cell);

        // Check the state of the Game
        if(mineSweeperGame.isGameIsOver())
        {
            // show a game over message
            Toast.makeText(getApplicationContext(),"Game is Over",Toast.LENGTH_SHORT).show();

            //STOP TIMER
            counter.cancel();
            timerStarted = false;
            secondsElapsed = 0;

            // Reveal all the bombs
            mineSweeperGame.getMineGrid().showAllBombs();
        }

        if(mineSweeperGame.isGameWon()){
            // show a game over message
            Toast.makeText(getApplicationContext(),"You Won the Game",Toast.LENGTH_SHORT).show();

            //STOP TIMER and Reset
            counter.cancel();
            timerStarted = false;
            secondsElapsed = 0;

            // Reveal all the bombs
            mineSweeperGame.getMineGrid().showAllBombs();
        }
        // Show the new MineGrid
        mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());
    }
}