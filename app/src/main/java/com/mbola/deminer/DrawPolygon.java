package com.mbola.deminer;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DrawPolygon extends Drawable {
    private int sidesNumber;
    private Path polygon;
    private Path temporal;
    private Paint paint;

    public DrawPolygon(int color, int sides){
        this.polygon = new Path();
        this.temporal = new Path();
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.paint.setStyle(Paint.Style.FILL);

        this.paint.setColor(color);
        polygon.setFillType(Path.FillType.EVEN_ODD);
        this.sidesNumber = sides;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawPath(polygon, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        this.paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return paint.getAlpha();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.computeHexagone(bounds);
        invalidateSelf();
    }

    private Path createHexagon(int size, int centerX, int centerY) {
        final float section = (float) (2.0 * Math.PI / this.sidesNumber);
        int radius = size / 2;
        Path polygonPath = this.temporal;
        polygonPath.reset();
        polygonPath.moveTo((centerX + radius * (float)Math.cos(0)), (centerY + radius * (float)Math.sin(0)));

        for (int i = 1; i < this.sidesNumber; i++) {
            polygonPath.lineTo((centerX + radius * (float)Math.cos(section * i)), (centerY + radius * (float)Math.sin(section * i)));
        }

        polygonPath.close();
        return polygonPath;
    }

    public void computeHexagone(Rect bounds) {
        final int width = bounds.width();
        final int height = bounds.height();
        final int size = Math.min(width, height);
        final int centerX = bounds.left + (width / 2);
        final int centerY = bounds.top + (height / 2);

        this.polygon.reset();
        this.polygon.addPath(createHexagon(size, centerX, centerY));
        this.polygon.addPath(createHexagon((int) (size * .8f), centerX, centerY));
    }
}
