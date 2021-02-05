package com.mbola.deminer.listeners;

import android.view.View;
import android.widget.AdapterView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mbola.deminer.MainActivity;
import com.mbola.deminer.R;

import services.Service;

public class CustomSpinnerSelectListener implements AdapterView.OnItemSelectedListener {

    private MainActivity activity;

    public CustomSpinnerSelectListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Remove views if exist
        ConstraintLayout layout = (ConstraintLayout) activity.findViewById(R.id.layout);
        Service.removeViewsFromGrid(activity, layout);

        int level = Integer.parseInt(String.valueOf((parent.getItemAtPosition(position)).toString().charAt(0)));
        this.activity.setSelectedLevel(level);
        this.activity.getBombsNumber().setText(String.format("%03d",(MainActivity.LEVEL_PARAMETERS.get(level))[2]));

        activity.setTimerStarted(false);
        activity.setSecondsElapsed((MainActivity.LEVEL_PARAMETERS.get(level))[0]/1000);

        if (activity.getCounter() != null) {
            activity.getCounter().cancel();
        }

        activity.getTimer().setText(String.format("%03d",activity.getSecondsElapsed()));

        // Reset game status
        activity.getGameStatus().setText(R.string.game_status_default);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
