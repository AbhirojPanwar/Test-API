package io.github.abhirojp.myfeed_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.abhirojp.myfeed_android.R;
import io.github.abhirojp.myfeed_android.callback.OnFeedItemClick;
import io.github.abhirojp.myfeed_android.data.DataModel;

import static io.github.abhirojp.myfeed_android.Utility.checkValue;
import static io.github.abhirojp.myfeed_android.Utility.requestImage;

/**
 * Created by abhiroj on 6/9/17.
 */

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.ViewHolder> {

    private static final String TAG= FeedListAdapter.class.getSimpleName();

    private Context context;
    private ArrayList<DataModel> dataList;
    private OnFeedItemClick onFeedItemClick;

    public FeedListAdapter(Context context){
        Log.d(TAG,"Initalizing");
        this.context=context;
        onFeedItemClick=(OnFeedItemClick) context;
    }

    @Override
    public FeedListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.list_item,parent,false);
        FeedListAdapter.ViewHolder vh=new FeedListAdapter.ViewHolder(view);
        return vh;
    }

    public void addAPIData(ArrayList<DataModel> d){
        getList().clear();
        getList().addAll(d);
        notifyDataSetChanged();
    }

    public ArrayList<DataModel> getList(){
        if(dataList==null){
            dataList=new ArrayList<>();
        }
        return dataList;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG,"creating an item for pos "+position);
        final DataModel item=getList().get(position);
        holder.api_title.setText(item.getTitle());
        holder.api_name.setText(item.getName());
        // If this is not used, Data shown in list breaks and gets unorderly.
        if (holder.child.findViewById(R.id.child_text) != null) {
            holder.child.removeView(holder.child.findViewById(R.id.child_text));
        }
        if (holder.child.findViewById(R.id.child_image) != null) {
            holder.child.removeView(holder.child.findViewById(R.id.child_image));
        }
        if (checkValue(item.getText())) {
            Log.d(TAG, "Text For position " + position);
            TextView child_text = new TextView(getContext());
            child_text.setId(R.id.child_text);
            child_text.setGravity(Gravity.CENTER_VERTICAL);
            child_text.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
            child_text.setPadding(5, 5, 5, 5);
            child_text.setText(item.getText());
            holder.child.addView(child_text, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        if (checkValue(item.getImageUrl())) {
            Log.d(TAG, "Image For position " + position);
            ImageView child_image = new ImageView(getContext());
            child_image.setId(R.id.child_image);
            child_image.setScaleType(ImageView.ScaleType.FIT_CENTER);
            requestImage(child_image, item.getImageUrl());
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(300, 200);
            if (holder.child.getChildCount() == 1)
                holder.child.addView(child_image, 0, lp2);
            else
                holder.child.addView(child_image, 0, lp1);
        }
        holder.markButton.setOnClickListener(new View.OnClickListener() {
            //TODO: Backend API operations
            @Override
            public void onClick(View view) {
                // TODO: Database operations using Async Task
                if(holder.markButton.getText().equals("LIKE")){
                    holder.markButton.setText("UNLIKE");
                }else{
                    holder.markButton.setText("LIKE");
                }
            }
        });
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFeedItemClick.passData(dataList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    public Context getContext() {
        return context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView api_title;
        public TextView api_name;
        public Button markButton;
        public View rootView;
        public LinearLayout child;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView=itemView;
            api_title=itemView.findViewById(R.id.api_title);
            api_name=itemView.findViewById(R.id.api_name);
            markButton=itemView.findViewById(R.id.api_mark);
            child=itemView.findViewById(R.id.linLayout);
        }
    }
}
