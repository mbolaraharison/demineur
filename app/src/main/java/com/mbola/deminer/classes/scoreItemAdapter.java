package com.mbola.deminer.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mbola.deminer.R;

import org.w3c.dom.Text;

import java.util.List;

public class scoreItemAdapter extends BaseAdapter {

    private Context context;
    private List<scoreItem> scoreItemList;
    private LayoutInflater inflater;


    public scoreItemAdapter(Context context,List<scoreItem> scoreItemList){
        this.context = context;
        this.scoreItemList = scoreItemList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return scoreItemList.size();
    }

    @Override
    public scoreItem getItem(int position) {
        return scoreItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = inflater.inflate(R.layout.adapter_item, null);
        scoreItem currentItem = getItem(position);
        String itemDate = currentItem.getDate();
        String itemScore = currentItem.getScore();

        TextView itemDateView = view.findViewById(R.id.item_date);
        itemDateView.setText(itemDate);

        TextView itemScoreView = view.findViewById(R.id.score);
        itemScoreView.setText(itemScore);

        return view;
    }
}
