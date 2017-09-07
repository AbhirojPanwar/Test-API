package io.github.abhirojp.myfeed_android.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.github.abhirojp.myfeed_android.R;
import io.github.abhirojp.myfeed_android.asynctasks.TaskDelete;
import io.github.abhirojp.myfeed_android.asynctasks.TaskInsert;
import io.github.abhirojp.myfeed_android.data.DataModel;

import static io.github.abhirojp.myfeed_android.Utility.checkIfFeedIsPresent;
import static io.github.abhirojp.myfeed_android.Utility.checkValue;
import static io.github.abhirojp.myfeed_android.Utility.feedNotPresent;
import static io.github.abhirojp.myfeed_android.Utility.feedPresent;
import static io.github.abhirojp.myfeed_android.Utility.requestImage;
import static io.github.abhirojp.myfeed_android.data.Constants.fragtag;

/**
 * Created by abhiroj on 6/9/17.
 */

public class FeedDetailsFragment extends Fragment {


    public static final String TAG = FeedDetailsFragment.class.getSimpleName();
    private static final String KEY_MODEL = "1";

    public static FeedDetailsFragment newInstance(DataModel data){
        FeedDetailsFragment detailsFragment=new FeedDetailsFragment();
        Log.d(TAG, "Is Data Null? " + ((data == null) ? "Yes" : "No") + " For example: name is " + data.getName());
        Bundle bundle=new Bundle();
        bundle.putSerializable(KEY_MODEL, data);
        detailsFragment.setArguments(bundle);
        fragtag.put(TAG,detailsFragment);
        return detailsFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, Bundle state) {
        //TODO: transitions
        View view = inflater.inflate(R.layout.fragment_feeddetail, parent, false);

        final DataModel model = (DataModel) getArguments().getSerializable(KEY_MODEL);

        TextView detail_title=view.findViewById(R.id.detail_title);
        TextView detail_name=view.findViewById(R.id.detail_name);
        TextView detail_desc=view.findViewById(R.id.detail_desc);
        LinearLayout container1 = view.findViewById(R.id.detail_group1);
        final Button api_mark = view.findViewById(R.id.api_mark);
        String text = model.getText();
        String imageUrl = model.getImageUrl();
        if (checkIfFeedIsPresent(getContext().getContentResolver(), model.getPos())) {
            feedPresent(api_mark);
        } else {
            feedNotPresent(api_mark);
        }

        api_mark.setOnClickListener(new View.OnClickListener() {
            //TODO: Backend API operations
            @Override
            public void onClick(View view) {
                if (api_mark.getText().equals("LIKE")) {
                    new TaskInsert(api_mark).execute(model, null);
                } else {
                    new TaskDelete(api_mark).execute(model, null);
                }
            }
        });

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


        detail_title.setText(model.getTitle());
        detail_name.setText("From: " + model.getName());
        detail_desc.setText(model.getDescription());
        return view;
    }
}