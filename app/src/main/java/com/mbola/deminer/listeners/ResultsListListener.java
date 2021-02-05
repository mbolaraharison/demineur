package com.mbola.deminer.listeners;

import android.content.Context;
import android.view.View;

import com.mbola.deminer.MainActivity;
import com.mbola.deminer.classes.CustomPopUp;

public class ResultsListListener implements View.OnClickListener {

    private MainActivity activity;
    private Context context;

    public ResultsListListener(MainActivity activity) {
        this.activity = activity;
        this.context = this.activity.getBaseContext();
    }

    @Override
    public void onClick(View v) {
        this.activity.setCustomPopUp(new CustomPopUp(this.activity));
        if (this.activity.isGameWon()) {
            this.activity.getCustomPopUp().setScore(String.valueOf(this.activity.getSecondsElapsed()));
        }
        this.activity.getCustomPopUp().getCancel_Button().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getCustomPopUp().setScore("NaN");
                activity.getCustomPopUp().dismiss();
            }
        });
        activity.getCustomPopUp().build();
    }
}
