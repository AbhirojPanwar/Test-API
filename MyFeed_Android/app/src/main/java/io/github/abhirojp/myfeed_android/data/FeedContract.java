package io.github.abhirojp.myfeed_android.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by abhiroj on 7/9/17.
 */

public final class FeedContract {
    public static final String CONTENT_AUTHORITY = "io.github.abhirojp.myfeed_android.data";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY); // Uri to access Content Provider
    public static final String PATH_FEED_FAVORITES = "feedfavorites"; // path to database table, appends with Base Uri

    public final static class FavoritesEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FEED_FAVORITES).build(); // Path for the table
        public static final String CONTENT_LIST_TYPE = "vnd.adnroid.cursor.dir/" + CONTENT_URI + "/" + PATH_FEED_FAVORITES;
        public static final String CONTENT_ITEM_TYPE = "vnd.adnroid.cursor.item/" + CONTENT_URI + "/" + PATH_FEED_FAVORITES;

        public static final String TABLE_NAME = "table_favorites"; //  name of database table
        public static final String COL_NAME = "name";
        public static final String COL_DESC = "description";
        public static final String COL_TEXT = "text"; // can be blank
        public static final String COL_TIME = "time";
        public static final String COL_IMGURL = "imageUrl"; // can be blank
        public static final String COL_TITLE = "title";
        public static final String UID_POS = "position"; // used a unique Id for every row item

        public static Uri buildUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}