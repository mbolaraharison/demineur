package services;

import android.content.Context;
import android.util.TypedValue;

public class Service {
    public static float toPixel(Context context, float value) {
        return (float) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value,
                context.getResources().getDisplayMetrics()
        );
    }
}
