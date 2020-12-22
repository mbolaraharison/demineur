package com.mbola.deminer;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.mbola.deminer.shapes.PaperPolygonShape;
import com.mbola.deminer.shapes.RegularPolygonShape;
import com.mbola.deminer.shapes.StarPolygonShape;
import com.mbola.deminer.views.PolygonImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView testHexagon;
    private Shape hexagonShape;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.testHexagon = findViewById(R.id.test_hexagon);
        this.testHexagon.setBackground(new DrawPolygon(Color.RED, 3));

        this.testHexagon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("HELLO : ");
            }
        });
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);
        int width = size.x;
        int height = size.y;

        PolygonImageView kitty = (PolygonImageView) findViewById(R.id.kitty01);
        kitty.setPadding(0, 0 , 0, 0);
        float diameter = toPixel(Math.abs(kitty.getPolygonShapeSpec().getDiameter()));
        kitty.setX((width/2)-diameter);

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layout);
        for(int i=0; i<3; i++){
            PolygonImageView view = new PolygonImageView(this);
            view.setId(i);
            view.setImageResource(R.drawable.cat07);
            view.setPadding(0,0,0,0);

            view.setX(kitty.getX()-(toPixel(diameter)/2));
            view.setY(kitty.getY()+(toPixel(diameter)/2));
            if (i==1) {
                view.setX(kitty.getX()+(toPixel(diameter)/2));
            } else if (i==2) {
                view.setX(kitty.getX());
                view.setY(kitty.getY()+(toPixel(diameter)));
            }

            view.addShadow(10f, 0f, 0f, Color.RED);
            view.addBorder(5, Color.WHITE);
            view.setCornerRadius(25);
            view.setVertices(6);

            view.setPolygonShape(new RegularPolygonShape());
            layout.addView(view, kitty.getLayoutParams());
        }

    }

    private float toPixel(float value) {
        Resources r = getResources();
        return (float) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value,
                r.getDisplayMetrics()
        );
    }
}