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

public class customPopUp extends Dialog {

    private String score;
    private Button cancel_Button;
    private TextView scoreView;

    public customPopUp(Activity activity) {
        super(activity, R.style.Theme_AppCompat_DayNight_DarkActionBar);
        setContentView(R.layout.score_template);

        this.score = "0";
        this.scoreView = findViewById(R.id.score);
        this.cancel_Button = findViewById(R.id.cancel_Button);


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
