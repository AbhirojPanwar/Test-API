package io.github.abhirojp.myfeed_android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import io.github.abhirojp.myfeed_android.R;
import io.github.abhirojp.myfeed_android.asynctasks.TaskDelete;
import io.github.abhirojp.myfeed_android.asynctasks.TaskInsert;
import io.github.abhirojp.myfeed_android.callback.OnFeedItemClick;
import io.github.abhirojp.myfeed_android.data.DataModel;

import static io.github.abhirojp.myfeed_android.Utility.checkIfFeedIsPresent;
import static io.github.abhirojp.myfeed_android.Utility.checkValue;
import static io.github.abhirojp.myfeed_android.Utility.feedNotPresent;
import static io.github.abhirojp.myfeed_android.Utility.feedPresent;
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
        Collections.sort(getList(), new Comparator<DataModel>() {
            @Override
            public int compare(DataModel dataModel, DataModel t1) {
                return dataModel.getTime().compareTo(t1.getTime());
            }
        });
        for (DataModel e : dataList) {
            Log.d(TAG, "Time sort:" + e.getTime() + " Name: " + e.getName());
        }
        notifyDataSetChanged();
    }

    public ArrayList<DataModel> getList(){
        if(dataList==null){
            dataList=new ArrayList<>();
        }
        return dataList;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG,"creating an item for pos "+position);
        final DataModel item=getList().get(position);
        item.setPos(position);
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setId(R.id.time_club);
        frameLayout.setBackgroundColor(Color.LTGRAY);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        frameLayout.setLayoutParams(params);

        // This code ensures that if there is a frame layout then it must be removed and drawn only if satisfies the condition.
        if (holder.rootView.findViewById(R.id.time_club) != null) {
            LinearLayout root = (LinearLayout) holder.rootView;
            root.removeView(holder.rootView.findViewById(R.id.time_club));
        }
        if ((position == 0 || item.getTime().compareTo(getList().get(position - 1).getTime()) >= 1)) {
            TextView time = new TextView(getContext());
            time.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
            time.setText(item.getTime() + "");
            time.setPadding(10, 12, 12, 12);
            time.setTextColor(Color.BLACK);
            time.setGravity(Gravity.CENTER);
            frameLayout.addView(time);
        } else {
            View horizontalDivider = new View(getContext());
            horizontalDivider.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 4));
            frameLayout.addView(horizontalDivider);
        }

        LinearLayout root = (LinearLayout) holder.rootView;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (position > 0) layoutParams.setMargins(0, 10, 0, 0);
        root.addView(frameLayout, 0, layoutParams);

        holder.api_title.setText(item.getTitle());
        holder.api_name.setText("From: " + item.getName());

        if (holder.child.findViewById(R.id.child_text) != null) {
            holder.child.removeView(holder.child.findViewById(R.id.child_text));
        }
        if (holder.child.findViewById(R.id.child_image) != null) {
            holder.child.removeView(holder.child.findViewById(R.id.child_image));
        }
        if (checkValue(item.getText())) {
            TextView child_text = new TextView(getContext());
            child_text.setTransitionName("TEXT_API");
            child_text.setId(R.id.child_text);
            child_text.setGravity(Gravity.CENTER_VERTICAL);
            if (checkValue(item.getImageUrl())) {
                child_text.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
            } else {
                child_text.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
            }
            child_text.setPadding(5, 5, 5, 5);
            child_text.setText(item.getText());
            holder.child.addView(child_text, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        if (checkValue(item.getImageUrl())) {
            ImageView child_image = new ImageView(getContext());
            child_image.setTransitionName("IMAGE_API");
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
        if (checkIfFeedIsPresent(getContext().getContentResolver(), item.getPos())) {
            feedPresent(holder.markButton);
        } else {
            feedNotPresent(holder.markButton);
        }
        holder.markButton.setOnClickListener(new View.OnClickListener() {
            //TODO: Backend API operations
            @Override
            public void onClick(View view) {
                // TODO: Database operations using Async Task
                if(holder.markButton.getText().equals("LIKE")){
                    new TaskInsert(holder.markButton).execute(item, null);
                }else{
                    new TaskDelete(holder.markButton).execute(item, null);
                }
            }
        });
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFeedItemClick.passData(item);
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
