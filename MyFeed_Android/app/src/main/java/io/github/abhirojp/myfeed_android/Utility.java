package io.github.abhirojp.myfeed_android;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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

}
