package io.github.abhirojp.myfeed_android;

import android.content.ContentResolver;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import io.github.abhirojp.myfeed_android.data.FeedContract;

/**
 * Created by abhiroj on 7/9/17.
 */

public class Utility {

    private static final String TAG = "UtilityClass";

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
        int res = cursor.getCount();
        cursor.close();
        return res > 0;
    }

    public static void feedPresent(Button b) {
        b.setText("UNLIKE");
        b.setTextColor(ColorStateList.valueOf(Color.WHITE));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            b.setBackgroundTintList(ColorStateList.valueOf(b.getContext().getResources().getColor(R.color.colorPrimary)));
        } else {
            ViewCompat.setBackgroundTintList(b, ColorStateList.valueOf(b.getContext().getResources().getColor(R.color.colorPrimary)));
        }
    }

    public static void feedNotPresent(Button b) {
        b.setText("LIKE");
        b.setTextColor(ColorStateList.valueOf(b.getContext().getResources().getColor(R.color.colorPrimary)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            b.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
        } else {
            ViewCompat.setBackgroundTintList(b, ColorStateList.valueOf(Color.LTGRAY));
        }
    }
}
