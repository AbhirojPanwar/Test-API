package io.github.abhirojp.myfeed_android.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.abhirojp.myfeed_android.R;
import io.github.abhirojp.myfeed_android.data.DataModel;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle state) {
        View view = inflater.inflate(R.layout.fragment_feeddetail, container, false);
        TextView detail_title=view.findViewById(R.id.detail_title);
        TextView detail_text=view.findViewById(R.id.detail_text);
        ImageView detail_image=view.findViewById(R.id.detail_image);
        TextView detail_name=view.findViewById(R.id.detail_name);
        TextView detail_desc=view.findViewById(R.id.detail_desc);
        Log.d(TAG, "Is State Null? " + ((state == null) ? "Yes" : "No"));
        detail_title.setText(getArguments().getString(KEY_TITLE));
        detail_text.setText(getArguments().getString(KEY_TEXT));
        requestImage(detail_image, getArguments().getString(KEY_IMAGEURL));
        detail_name.setText(getArguments().getString(KEY_NAME));
        detail_desc.setText(getArguments().getString(KEY_DESC));
        return view;
    }
}