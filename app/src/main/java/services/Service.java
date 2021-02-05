package services;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mbola.deminer.MainActivity;
import com.mbola.deminer.R;
import com.mbola.deminer.classes.Result;

import java.util.ArrayList;
import java.util.List;

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

    public static void removeViewsFromGrid(MainActivity activity, ViewGroup viewGroup) {
        int id = 1;
        while (((ConstraintLayout)viewGroup).getViewById(id) != null) {
            viewGroup.removeView(((ConstraintLayout)viewGroup).getViewById(id));
            id++;
        }

        LinearLayout footerLayout = activity.findViewById(R.id.footer_layout);
        ConstraintLayout.LayoutParams layoutParams1 = (ConstraintLayout.LayoutParams) footerLayout.getLayoutParams();
        layoutParams1.topMargin = (int) Service.toPixel(activity.getBaseContext(), 50);
    }

    public static void createResultsTableIfNotExists(MainActivity activity) {
        /* Create a Table in the Database. */
        activity.getDb().execSQL("CREATE TABLE IF NOT EXISTS "
                + MainActivity.TABLE_NAME
                + " (date VARCHAR(255), level INT(1), score INT(3));");
    }

    public static void addResultToDb (MainActivity activity, Result result) {
        /* Insert data to a Table*/
        activity.getDb().execSQL("INSERT INTO "
                + MainActivity.TABLE_NAME
                + " (date, level, score)"
                + " VALUES ('"+result.getDate()+"', "+result.getLevel()+" , "+result.getScore()+");");
    }

    public static List<Result> getAllResultsFromDb (MainActivity activity) {
        /*retrieve data from database */
        Cursor c = activity.getDb().rawQuery("SELECT * FROM " + MainActivity.TABLE_NAME +" ORDER BY date desc LIMIT 20", null);

        int dateColumn = c.getColumnIndex("date");
        int levelColumn = c.getColumnIndex("level");
        int scoreColumn = c.getColumnIndex("score");

        List<Result> results = null;

        // Check if our result was valid.
        c.moveToFirst();
        if (c.getCount() != 0) {
            results = new ArrayList<>();
            // Loop through all Results
            do {
                results.add(new Result(c.getString(dateColumn), c.getInt(levelColumn), c.getInt(scoreColumn)));
            }while(c.moveToNext());
        }
        return results;
    }

    public static void truncateTable(MainActivity activity) {
        /* Truncate table */
        activity.getDb().execSQL("DELETE FROM "
                + MainActivity.TABLE_NAME
                + ";");
    }
}
