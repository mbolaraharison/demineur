package com.mbola.deminer.listeners;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mbola.deminer.MainActivity;
import com.mbola.deminer.R;

public class ResultsListListener implements View.OnClickListener {

    private MainActivity activity;
    private Context context;

    public ResultsListListener(MainActivity activity) {
        this.activity = activity;
        this.context = this.activity.getBaseContext();
    }

    @Override
    public void onClick(View v) {
        this.activity.getCustomPopUp().setScore("ICI");
        this.activity.getCustomPopUp().getCancel_Button().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Results closed",Toast.LENGTH_SHORT).show();
                activity.getCustomPopUp().dismiss();
            }
        });
        activity.getCustomPopUp().build();
    }
}
