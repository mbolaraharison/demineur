package com.example.minesweeper;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MineGridRecyclerAdapter extends RecyclerView.Adapter<MineGridRecyclerAdapter.MineTileViewHolder> {
    private List<Cell> cells;
    private OnCellClickListener listener;

    public MineGridRecyclerAdapter(List<Cell> cells, OnCellClickListener listener) {
        this.cells = cells;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MineTileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a holder : that will hold our cell
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cell, parent, false);
        return new MineTileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MineTileViewHolder holder, int position) {
        // PASS THE Cell to the  holder
        holder.bind(cells.get(position));
        holder.setIsRecyclable(false);
    }

    // Return the number of cells in the mineGrid
    @Override
    public int getItemCount() {
        return cells.size();
    }
    // Update the cells in the list
    public void setCells(List<Cell> cells) {
        this.cells = cells;
        // Notify the MineGridRecyclerAdapter with the last updates
        notifyDataSetChanged();
    }

    class MineTileViewHolder extends RecyclerView.ViewHolder {
        // Text field that will display ( Bomb , Flag, ...)
        TextView valueTextView;
        // constructor
        public MineTileViewHolder(@NonNull View itemView) {
            super(itemView);
            // Set up the field by finding the id
            valueTextView = itemView.findViewById(R.id.item_cell_value);
        }

        public void bind(final Cell cell) {
            // Set the background to gray
            itemView.setBackgroundColor(Color.GRAY);
            // Set up a click listener that will invoke onCellClick Methods
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCellClick(cell);
                }
            });


        }
    }
}