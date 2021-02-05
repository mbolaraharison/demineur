package com.mbola.deminer.listeners;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mbola.deminer.MainActivity;
import com.mbola.deminer.R;
import com.mbola.deminer.classes.CustomPopUp;

import services.Service;

public class CustomSpinnerSelectListener implements AdapterView.OnItemSelectedListener {

    private MainActivity activity;

    public CustomSpinnerSelectListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /*SharedPreferences.Editor editor = this.activity.getSharedPreferences(MainActivity.MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("level", position);
        editor.apply();*/
        // Remove views if exist
        ConstraintLayout layout = (ConstraintLayout) activity.findViewById(R.id.layout);
        Service.removeViewsFromGrid(activity, layout);

        int level = Integer.parseInt(String.valueOf((parent.getItemAtPosition(position)).toString().charAt(0)));
        this.activity.setSelectedLevel(level);
        this.activity.getBombsNumber().setText(String.format("%03d",(MainActivity.LEVEL_PARAMETERS.get(level))[2]));

        activity.setCustomPopUp(new CustomPopUp(activity));
        this.activity.setGameWon(false);
        this.activity.setGameOver(false);
        activity.setTimerStarted(false);
        activity.setSecondsElapsed((MainActivity.LEVEL_PARAMETERS.get(level))[0]/1000);

        if (activity.getCounter() != null) {
            activity.getCounter().cancel();
        }

        activity.getTimer().setText(String.format("%03d",activity.getSecondsElapsed()));

        // Reset game status
        activity.getGameStatus().setText(R.string.game_status_default);
        activity.getGameStatus().setTextColor(Color.DKGRAY);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
