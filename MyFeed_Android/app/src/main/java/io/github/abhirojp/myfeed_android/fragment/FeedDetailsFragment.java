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

import static io.github.abhirojp.myfeed_android.data.Constants.fragtag;

/**
 * Created by abhiroj on 6/9/17.
 */

public class FeedDetailsFragment extends Fragment {


    public static final String TAG = FeedDetailsFragment.class.getSimpleName();
    private static String KEY_ITEM_INDIVIDUAL = "1001";
    private DataModel data;
    private Bundle state;

    public static FeedDetailsFragment newInstance(DataModel data){
        FeedDetailsFragment detailsFragment=new FeedDetailsFragment();
        Log.d(TAG, "Is Data Null? " + ((data == null) ? "Yes" : "No") + " For example: name is " + data.getName());
        Bundle bundle=new Bundle();
        bundle.putSerializable(KEY_ITEM_INDIVIDUAL, data);
        detailsFragment.setArguments(bundle);
        fragtag.put(TAG,detailsFragment);
        return detailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null)
            state = savedInstanceState;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (state != null)
            data = (DataModel) state.getSerializable(KEY_ITEM_INDIVIDUAL);
        View view = inflater.inflate(R.layout.fragment_feeddetail, container, false);
        TextView detail_title=view.findViewById(R.id.detail_title);
        TextView detail_text=view.findViewById(R.id.detail_text);
        ImageView detail_image=view.findViewById(R.id.detail_image);
        TextView detail_name=view.findViewById(R.id.detail_name);
        TextView detail_desc=view.findViewById(R.id.detail_desc);

        if (data != null) {
            detail_title.setText(data.getTitle());
            detail_text.setText(data.getText());
            detail_name.setText(data.getName());
            detail_desc.setText(data.getName());
        } else {
            //  TODO: Show an empty view
        }
        return view;
    }
}
