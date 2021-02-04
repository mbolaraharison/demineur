package com.mbola.deminer.listeners;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mbola.deminer.R;

public class ResultsListListener implements View.OnClickListener {

    private Activity activity;
    private ConstraintLayout layout;

    public ResultsListListener(Activity activity) {
        this.activity = activity;
        this.layout = activity.findViewById(R.id.layout);
    }

    @Override
    public void onClick(View v) {
        PopupWindow popup = new PopupWindow(this.activity);
        popup.setContentView(this.layout);
    }
}
