package io.github.abhirojp.myfeed_android.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class FeedProvider extends ContentProvider {

    private static final int FAVORITE = 1000; // when match for a set of rows
    private static final int FAVORITE_ID = 1001; // when match for an individual row item
    private static final UriMatcher MATCHER = buildUriMatcher();
    private static FeedDBHelper dbhelper;

    public FeedProvider() {
    }

    private static UriMatcher buildUriMatcher() {
        String authority = FeedContract.CONTENT_AUTHORITY;

        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(authority, FeedContract.PATH_FEED_FAVORITES, FAVORITE); //  "io.github.abhirojp.myfeed_android.data/feedfavorites"
        uriMatcher.addURI(authority, FeedContract.PATH_FEED_FAVORITES + "/#", FAVORITE_ID);// "io.github.abhirojp.myfeed_android.data/feedfavorites/#4 exmp."
        return uriMatcher;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        int res;  // number of rows deleted
        switch (MATCHER.match(uri)) {
            case FAVORITE:
                res = db.delete(FeedContract.FavoritesEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri:" + uri);
        }

        if (selection == null || res != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return res;
    }

    @Override
    public String getType(Uri uri) {
        int match = MATCHER.match(uri);
        switch (match) {
            case FAVORITE:
                return FeedContract.FavoritesEntry.CONTENT_LIST_TYPE;
            case FAVORITE_ID:
                return FeedContract.FavoritesEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase database = dbhelper.getWritableDatabase();
        Uri returnUri;
        long id;
        switch (MATCHER.match(uri)) {
            case FAVORITE:
                id = database.insert(FeedContract.FavoritesEntry.TABLE_NAME, null, values);
                if (id > 0) {
                    returnUri = FeedContract.FavoritesEntry.buildUri(id);
                } else {
                    throw new UnsupportedOperationException("Can not insert at:" + id + "for " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri:" + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public boolean onCreate() {
        dbhelper = new FeedDBHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase database = dbhelper.getReadableDatabase();
        Cursor cursor;
        switch (MATCHER.match(uri)) {
            case FAVORITE:
                cursor = database.query(FeedContract.FavoritesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder, null);
                break;
            case FAVORITE_ID:
                long id = ContentUris.parseId(uri);
                cursor = database.query(FeedContract.FavoritesEntry.TABLE_NAME, projection, FeedContract.FavoritesEntry._ID + "=?", new String[]{String.valueOf(id)}, null, null, sortOrder, null);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
