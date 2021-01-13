package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnCellClickListener {
    RecyclerView gridRecyclerView;
    MineGridRecyclerAdapter mineGridRecyclerAdapter;
    MineSweeperGame mineSweeperGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridRecyclerView = findViewById(R.id.activity_main_grid);
        gridRecyclerView.setLayoutManager(new GridLayoutManager(this, 10));
        mineSweeperGame = new MineSweeperGame(10);
        // Pass the cells and "this" click listener
        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(mineSweeperGame.getMineGrid().getCells(),this);
        // Set the recycler adapter
        gridRecyclerView.setAdapter(mineGridRecyclerAdapter);

    }


    @Override
    public void onCellClick(Cell cell) {
        Toast.makeText(getApplicationContext(),"cell clicked",Toast.LENGTH_SHORT).show();
    }
}