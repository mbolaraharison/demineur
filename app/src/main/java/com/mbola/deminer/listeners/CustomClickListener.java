package com.mbola.deminer.listeners;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class CustomClickListener implements View.OnClickListener {

    private TextView playButton,timer;
    private int secondsElapsed; // Count the seconds Elapsed

    public CustomClickListener(TextView textView, TextView timer, int secondsElapsed) {
        this.playButton = textView;
        this.timer = timer;
        this.secondsElapsed = secondsElapsed;
    }

    @Override
    public void onClick(View v) {
        timer.setText(String.format("%03d",secondsElapsed));
    }
}
