package com.mbola.deminer.listeners;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mbola.deminer.MainActivity;
import com.mbola.deminer.R;
import com.mbola.deminer.classes.Grid;

public class CustomClickListener implements View.OnClickListener {

    private MainActivity activity;
    private Context context;
    private int level;

    public CustomClickListener(MainActivity activity, TextView textView, TextView timer, int secondsElapsed) {

        this.activity = activity;
        this.context = this.activity.getBaseContext();

    }

    @Override
    public void onClick(View v) {
        // Here we set the selected level
        this.level = this.activity.getSelectedLevel();

        this.activity.setGameOver(false);
        this.activity.setGameWon(false);

        // We reset the timer
        this.resetTimer();

        // We place grid
        this.placeGridOnLayout();

        // Reset game status
        this.activity.getGameStatus().setText(R.string.game_status_default);
    }

    private void resetTimer() {
        this.activity.setTimerStarted(false);
        this.activity.setSecondsElapsed((MainActivity.LEVEL_PARAMETERS.get(this.level))[0]/1000);

        if (this.activity.getCounter() != null) {
            this.activity.getCounter().cancel();
        }

        this.activity.getTimer().setText(String.format("%03d",this.activity.getSecondsElapsed()));

        CountDownTimer counter = new CountDownTimer((MainActivity.LEVEL_PARAMETERS.get(this.level))[0],1000) {
            // Add one after each second
            @Override
            public void onTick(long l) {
                activity.setSecondsElapsed(activity.getSecondsElapsed()-1);
                activity.getTimer().setText(String.format("%03d",activity.getSecondsElapsed()));
            }

            @Override
            public void onFinish() {
                activity.setGameOver(true);
            }
        };

        this.activity.setCounter(counter);
    }

    private void placeGridOnLayout() {
        ConstraintLayout layout = (ConstraintLayout) activity.findViewById(R.id.layout);

        View templateView = activity.findViewById(R.id.kitty01);

        int[] windowDimensions = activity.getWindowDimensions();

        Grid grid = new Grid(this.activity, templateView, windowDimensions, layout, (MainActivity.LEVEL_PARAMETERS.get(this.level))[1], (MainActivity.LEVEL_PARAMETERS.get(this.level))[2]);

        activity.setGrid(grid);

        for (int i = 0; i < grid.getCells().size(); i++) {
            activity.getGrid().getCells().get(i).getPolygonImageView().setOnTouchListener(new CustomTouchListener(this.activity, grid.getCells().get(i)));
        }
    }
}
