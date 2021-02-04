package services;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;

public class Service {
    public static float toPixel(Context context, float value) {
        return (float) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value,
                context.getResources().getDisplayMetrics()
        );
    }

    public static int[] windowDimensions(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);
        int[] dimensions = new int[]{size.x, size.y};
        return dimensions;
    }
}
