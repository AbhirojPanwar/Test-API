package io.github.abhirojp.myfeed_android.asynctasks;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;

import io.github.abhirojp.myfeed_android.data.DataModel;
import io.github.abhirojp.myfeed_android.data.FeedContract;

import static io.github.abhirojp.myfeed_android.Utility.checkIfFeedIsPresent;
import static io.github.abhirojp.myfeed_android.Utility.feedPresent;

/**
 * Created by abhiroj on 7/9/17.
 */

public class TaskInsert extends AsyncTask<DataModel, Void, Boolean> {

    Button mark;
    Context context;
    ContentResolver contentResolver;

    public TaskInsert(Button b) {
        mark = b;
        context = b.getContext();
        contentResolver = context.getContentResolver();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (aBoolean) {
            feedPresent(mark);
        }
    }

    @Override
    protected Boolean doInBackground(DataModel... dataModels) {
        DataModel item = dataModels[0];
        if (checkIfFeedIsPresent(contentResolver, item.getPos()))
            return false;
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedContract.FavoritesEntry.COL_DESC, item.getDescription());
        contentValues.put(FeedContract.FavoritesEntry.COL_NAME, item.getName());
        contentValues.put(FeedContract.FavoritesEntry.COL_TEXT, item.getText());
        contentValues.put(FeedContract.FavoritesEntry.COL_TIME, item.getTime() + "");
        contentValues.put(FeedContract.FavoritesEntry.COL_TITLE, item.getTitle());
        contentValues.put(FeedContract.FavoritesEntry.COL_IMGURL, item.getImageUrl());
        contentValues.put(FeedContract.FavoritesEntry.UID_POS, item.getPos());
        contentResolver.insert(FeedContract.FavoritesEntry.CONTENT_URI, contentValues);
        return true;
    }
}
