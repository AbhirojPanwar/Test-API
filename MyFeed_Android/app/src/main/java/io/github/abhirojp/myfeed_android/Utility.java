package io.github.abhirojp.myfeed_android;

import android.content.ContentResolver;
import android.database.Cursor;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import io.github.abhirojp.myfeed_android.data.FeedContract;

/**
 * Created by abhiroj on 7/9/17.
 */

public class Utility {

    public static void requestImage(ImageView target, String imageUrl) {
        if (imageUrl != null && imageUrl.length() > 0) {
            Picasso p = Picasso.with(target.getContext());
            p.load(imageUrl).placeholder(target.getContext().getResources().getDrawable(R.drawable.placeholder)).into(target);
        }
    }

    public static boolean checkValue(String text) {
        return text != null && text.length() > 0;
    }

    public static boolean checkIfFeedIsPresent(ContentResolver resolver, int pos) {
        Cursor cursor = resolver.query(FeedContract.FavoritesEntry.CONTENT_URI, new String[]{FeedContract.FavoritesEntry.UID_POS}, FeedContract.FavoritesEntry.UID_POS + "=?", new String[]{pos + ""},
                null);
        return cursor.getCount() > 0;
    }
}
