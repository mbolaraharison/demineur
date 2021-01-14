package com.example.minesweeper;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



/**
 * @author Jarrod Lilkendey
 */
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
        // Create a holder : that will hold our cell --- Creation of an Item View
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
        LinearLayout wrapperHexagone;
        // constructor
        public MineTileViewHolder(@NonNull View itemView) {
            super(itemView);
            // Set up the field by finding the id
            valueTextView = itemView.findViewById(R.id.item_cell_value);
            wrapperHexagone = itemView.findViewById(R.id.id_wrapperHexagone);
        }

        public void bind(final Cell cell) {
            // Set the background to gray
            itemView.setBackgroundColor(Color.WHITE);
            wrapperHexagone.setBackgroundColor(Color.GRAY);
            // Set up a click listener that will invoke onCellClick Methods
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCellClick(cell);
                }
            });

           // Only reveal if the Cell is in the Reveal State
            if(cell.isRevealed()){
                // Change cell state depending on its value
                if(cell.getValue() == Cell.BOMB)
                {
                    valueTextView.setText(R.string.bomb); // Use Bomb Emoji
                }else if (cell.getValue() == Cell.BLANK)
                {
                    valueTextView.setText("");
                    itemView.setBackgroundColor(Color.WHITE);
                    wrapperHexagone.setBackgroundColor(Color.WHITE);
                }else{
                    // Check the number of Bombs Around
                    valueTextView.setText(String.valueOf(cell.getValue()));
                    switch (cell.getValue())
                    {
                        case 1: // 1 Bomb Around
                            valueTextView.setTextColor(Color.BLUE);

                            break;
                        case 2: // 2 Bombs Around
                            valueTextView.setTextColor(Color.GREEN);
                            break;
                        case 3: // 3 Bombs Around : Risk
                            valueTextView.setTextColor(Color.RED);
                            break;
                    }
                }
            }


        }
    }
}