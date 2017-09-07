package io.github.abhirojp.myfeed_android.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.github.abhirojp.myfeed_android.R;
import io.github.abhirojp.myfeed_android.data.DataModel;

import static io.github.abhirojp.myfeed_android.Utility.checkValue;
import static io.github.abhirojp.myfeed_android.Utility.requestImage;
import static io.github.abhirojp.myfeed_android.data.Constants.fragtag;

/**
 * Created by abhiroj on 6/9/17.
 */

public class FeedDetailsFragment extends Fragment {


    public static final String TAG = FeedDetailsFragment.class.getSimpleName();
    private static String KEY_NAME = "1";
    private static String KEY_TEXT = "2";
    private static String KEY_IMAGEURL = "3";
    private static String KEY_TITLE = "4";
    private static String KEY_TIME = "5";
    private static String KEY_DESC = "6";

    public static FeedDetailsFragment newInstance(DataModel data){
        FeedDetailsFragment detailsFragment=new FeedDetailsFragment();
        Log.d(TAG, "Is Data Null? " + ((data == null) ? "Yes" : "No") + " For example: name is " + data.getName());
        Bundle bundle=new Bundle();
        bundle.putString(KEY_NAME, data.getName());
        bundle.putString(KEY_TEXT, data.getText());
        bundle.putString(KEY_IMAGEURL, data.getImageUrl());
        bundle.putString(KEY_TITLE, data.getTitle());
        bundle.putLong(KEY_TIME, data.getTime());
        bundle.putString(KEY_DESC, data.getDescription());
        detailsFragment.setArguments(bundle);
        fragtag.put(TAG,detailsFragment);
        return detailsFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, Bundle state) {
        //TODO: transitions
        View view = inflater.inflate(R.layout.fragment_feeddetail, parent, false);
        TextView detail_title=view.findViewById(R.id.detail_title);
        TextView detail_name=view.findViewById(R.id.detail_name);
        TextView detail_desc=view.findViewById(R.id.detail_desc);
        LinearLayout container1 = view.findViewById(R.id.detail_group1);
        String text = getArguments().getString(KEY_TEXT);
        String imageUrl = getArguments().getString(KEY_IMAGEURL);

        if (checkValue(text)) {
            TextView view1 = new TextView(getContext());
            view1.setId(R.id.child_text);
            view1.setGravity(Gravity.CENTER_VERTICAL);
            view1.setTextAppearance(view.getContext(), android.R.style.TextAppearance_Large);
            view1.setPadding(5, 5, 5, 5);
            view1.setText(text);
            container1.addView(view1, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        if (checkValue(imageUrl)) {
            ImageView view1 = new ImageView(getContext());
            view1.setId(R.id.child_image);
            view1.setPadding(5, 5, 5, 5);
            view1.setScaleType(ImageView.ScaleType.FIT_CENTER);
            requestImage(view1, imageUrl);
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(300, 200);
            if (container1.getChildCount() == 1)
                container1.addView(view1, 0, lp2);
            else
                container1.addView(view1, 0, lp1);
        }


        detail_title.setText(getArguments().getString(KEY_TITLE));
        detail_name.setText(getArguments().getString(KEY_NAME));
        detail_desc.setText(getArguments().getString(KEY_DESC));
        return view;
    }
}