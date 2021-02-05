package com.mbola.deminer.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mbola.deminer.R;

import java.util.List;

public class ResultsListAdapter extends BaseAdapter {

    private Context context;
    List<Result> resultsList;
    private LayoutInflater inflater;


    public ResultsListAdapter(Context context, List<Result> resultsList){
        this.context = context;
        this.resultsList = resultsList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return resultsList != null ? resultsList.size() : 0;
    }

    @Override
    public Result getItem(int position) {
        return resultsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.adapter_item, null);
        Result currentItem = getItem(position);
        String itemDate = currentItem.getDate();
        String itemLevel = String.valueOf(currentItem.getLevel());
        String itemScore = String.valueOf(currentItem.getScore());

        TextView itemDateView = view.findViewById(R.id.item_date);
        itemDateView.setText(itemDate);

        TextView itemLevelView = view.findViewById(R.id.level);
        itemLevelView.setText("LEVEL : "+itemLevel);

        TextView itemScoreView = view.findViewById(R.id.score);
        itemScoreView.setText("SCORE : "+itemScore);

        return view;
    }
}
