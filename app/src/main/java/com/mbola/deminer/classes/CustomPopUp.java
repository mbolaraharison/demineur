package com.mbola.deminer.classes;

import android.app.Dialog;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mbola.deminer.MainActivity;
import com.mbola.deminer.R;

public class CustomPopUp extends Dialog {

    private String score;
    private Button cancel_Button;
    private TextView scoreView;

    public CustomPopUp(MainActivity activity) {
        super(activity, R.style.Theme_AppCompat_DayNight_DarkActionBar);
        setContentView(R.layout.score_template);

        this.score = "0";
        this.scoreView = findViewById(R.id.score);
        this.cancel_Button = findViewById(R.id.cancel_Button);

        ListView scoreListView = findViewById(R.id.score_list);
        scoreListView.setAdapter(new ResultsListAdapter(activity, activity.getResultsList()));
    }

    public void setScore(String score){
        this.score = score;
    }

    public Button getCancel_Button(){return cancel_Button;}

    public void build(){
        show();
        scoreView.setText(score);
    }

    public TextView getScoreView() {
        return scoreView;
    }

    public void setScoreView(TextView scoreView) {
        this.scoreView = scoreView;
    }
}
