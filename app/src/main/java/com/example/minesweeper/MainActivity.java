package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnCellClickListener {
    RecyclerView gridRecyclerView;
    MineGridRecyclerAdapter mineGridRecyclerAdapter;
    MineSweeperGame mineSweeperGame;
    TextView face;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        face = findViewById(R.id.activity_main_smiley);
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mineSweeperGame = new MineSweeperGame(10, 10);
                mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());
            }
        });
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
        mineSweeperGame.handleCellClick(cell);
        if(mineSweeperGame.isGameIsOver())
        {
            // show a game over message
            Toast.makeText(getApplicationContext(),"Game is Over",Toast.LENGTH_SHORT).show();

            // Reveal all the bombs
            mineSweeperGame.getMineGrid().showAllBombs();
        }
        // Show the new MineGrid
        mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());
    }
}