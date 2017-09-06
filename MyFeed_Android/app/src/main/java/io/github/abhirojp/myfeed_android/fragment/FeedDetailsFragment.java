package io.github.abhirojp.myfeed_android.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.abhirojp.myfeed_android.R;
import io.github.abhirojp.myfeed_android.data.DataModel;

import static io.github.abhirojp.myfeed_android.data.Constants.fragtag;
import static io.github.abhirojp.myfeed_android.data.Constants.positionForData;

/**
 * Created by abhiroj on 6/9/17.
 */

public class FeedDetailsFragment extends Fragment {


    public static final String TAG = FeedDetailsFragment.class.getSimpleName();
    private DataModel data;

    public static FeedDetailsFragment newInstance(DataModel data){
        FeedDetailsFragment detailsFragment=new FeedDetailsFragment();

        Bundle bundle=new Bundle();
        bundle.putSerializable(positionForData,data);
        detailsFragment.setArguments(bundle);
        fragtag.put(TAG,detailsFragment);
        return detailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null)
        data= (DataModel) savedInstanceState.getSerializable(positionForData);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.fragment_feeddetail,container,false);
        TextView detail_title=view.findViewById(R.id.detail_title);
        TextView detail_text=view.findViewById(R.id.detail_text);
        ImageView detail_image=view.findViewById(R.id.detail_image);
        TextView detail_name=view.findViewById(R.id.detail_name);
        TextView detail_desc=view.findViewById(R.id.detail_desc);

        detail_title.setText("Random");
        detail_text.setText("Lorum Ipsum Dorus");
        detail_name.setText("Annabelle");
        detail_desc.setText("LOrum Ipsum Dorus LOrum Ipsum Dorus LOrum Ipsum Dorus LOrum Ipsum Dorus LOrum Ipsum Dorus");

        return view;
    }
}
