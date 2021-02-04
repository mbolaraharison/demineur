package com.mbola.deminer.listeners;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mbola.deminer.MainActivity;
import com.mbola.deminer.R;
import com.mbola.deminer.classes.Grid;

import java.util.logging.Level;

public class CustomClickListener implements View.OnClickListener {

    private TextView playButton,timer;
    private int secondsElapsed; // Count the seconds Elapsed

    private MainActivity activity;
    private Context context;
    private int level;
    //private Level

    public CustomClickListener(Context context, MainActivity activity, TextView textView, TextView timer, int secondsElapsed, int level) {

        this.activity = activity;
        this.context = context;
        this.level = level;

        /*this.playButton = textView;
        this.timer = timer;
        this.secondsElapsed = secondsElapsed;*/
    }

    @Override
    public void onClick(View v) {
        this.activity.setGameOver(false);
        this.activity.setGameWon(false);

        // We reset the timer
        this.resetTimer();

        // We place grid
        this.placeGridOnLayout();
    }

    private void resetTimer() {
        this.activity.setTimerStarted(false);
        this.activity.setSecondsElapsed(0);

        if (this.activity.getCounter() != null) {
            this.activity.getCounter().cancel();
        }

        this.activity.getTimer().setText(String.format("%03d",this.activity.getSecondsElapsed()));

        CountDownTimer counter = new CountDownTimer((MainActivity.LEVEL_PARAMETERS.get(this.level))[0],1000) {
            // Add one after each second
            @Override
            public void onTick(long l) {
                System.out.println("IT IS TICKING");
                activity.setSecondsElapsed(activity.getSecondsElapsed()+1);
                activity.getTimer().setText(String.format("%03d",activity.getSecondsElapsed()));
            }

            @Override
            public void onFinish() {
                //isGameOver = true;
                //Toast.makeText(getApplicationContext(),"Game is Over : Time is UP",Toast.LENGTH_SHORT).show();
            }
        };

        this.activity.setCounter(counter);
    }

    private void placeGridOnLayout() {
        ConstraintLayout layout = (ConstraintLayout) activity.findViewById(R.id.layout);

        View templateView = activity.findViewById(R.id.kitty01);

        int[] windowDimensions = activity.getWindowDimensions();

        Grid grid = new Grid(context, templateView, windowDimensions, layout, (MainActivity.LEVEL_PARAMETERS.get(this.level))[1], (MainActivity.LEVEL_PARAMETERS.get(this.level))[2]);

        activity.setGrid(grid);

        for (int i = 0; i < grid.getCells().size(); i++) {
            activity.getGrid().getCells().get(i).getPolygonImageView().setOnTouchListener(new CustomTouchListener(this.activity, grid.getCells().get(i)));
        }
    }
}
