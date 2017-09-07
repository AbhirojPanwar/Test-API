package io.github.abhirojp.myfeed_android.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abhiroj on 7/9/17.
 */

public class FeedDBHelper extends SQLiteOpenHelper {

    private static final int DB_VER = 1;
    private static final String DB_NAME = "FavoriteFeed.db";

    public FeedDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + FeedContract.FavoritesEntry.TABLE_NAME + " ( " + FeedContract.FavoritesEntry._ID + " INTEGER PRIMARY KEY," +
                FeedContract.FavoritesEntry.UID_POS + " INTEGER UNIQUE NOT NULL," + FeedContract.FavoritesEntry.COL_NAME + " TEXT NOT NULL," +
                FeedContract.FavoritesEntry.COL_DESC + " TEXT NOT NULL," + FeedContract.FavoritesEntry.COL_TEXT + " TEXT," +
                FeedContract.FavoritesEntry.COL_IMGURL + " TEXT," + FeedContract.FavoritesEntry.COL_TIME + " TEXT NOT NULL," +
                FeedContract.FavoritesEntry.COL_TITLE + " TEXT NOT NULL);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
