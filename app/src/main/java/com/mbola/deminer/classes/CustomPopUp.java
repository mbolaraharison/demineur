package com.mbola.deminer.classes;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mbola.deminer.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomPopUp extends Dialog {

    private String score;
    private Button cancel_Button;
    private TextView scoreView;

    public CustomPopUp(Activity activity) {
        super(activity, R.style.Theme_AppCompat_DayNight_DarkActionBar);
        setContentView(R.layout.score_template);

        this.score = "0";
        this.scoreView = findViewById(R.id.score);
        this.cancel_Button = findViewById(R.id.cancel_Button);

        List<scoreItem> scoreItemList = new ArrayList<>();
        scoreItemList.add(new scoreItem("24/01/2021","54,30"));
        scoreItemList.add(new scoreItem("02/01/2021","20,30"));
        scoreItemList.add(new scoreItem("02/02/2021","46,30"));

        ListView scoreListView = findViewById(R.id.score_list);
        scoreListView.setAdapter(new scoreItemAdapter(activity,scoreItemList));
    }

    public void setScore(String score){
        this.score = score;
    }

    public Button getCancel_Button(){return cancel_Button;}

    public void build(){
        show();
        scoreView.setText(score);
    }
}
