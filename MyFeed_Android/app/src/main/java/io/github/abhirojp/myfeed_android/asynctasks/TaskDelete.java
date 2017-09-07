package io.github.abhirojp.myfeed_android.asynctasks;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Button;

import io.github.abhirojp.myfeed_android.data.DataModel;
import io.github.abhirojp.myfeed_android.data.FeedContract;

/**
 * Created by abhiroj on 7/9/17.
 */

public class TaskDelete extends AsyncTask<DataModel, Void, Boolean> {

    Button mark;
    Context context;

    public TaskDelete(Button b) {
        mark = b;
        context = b.getContext();
    }

    @Override
    protected Boolean doInBackground(DataModel... dataModels) {
        DataModel item = dataModels[0];
        Cursor cursor = context.getContentResolver().query(FeedContract.FavoritesEntry.CONTENT_URI, null, FeedContract.FavoritesEntry.UID_POS + "=?", new String[]{item.getPos() + ""}, null);
        cursor.moveToFirst();
        long id = cursor.getLong(cursor.getColumnIndex(FeedContract.FavoritesEntry._ID));
        int rows = context.getContentResolver().delete(FeedContract.FavoritesEntry.CONTENT_URI, FeedContract.FavoritesEntry._ID + "=?", new String[]{id + ""});
        return rows > 0;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (aBoolean) {
            mark.setText("LIKE");
            mark.setTextColor(Color.BLUE);
            mark.setBackgroundColor(Color.GRAY);
        }
    }
}
