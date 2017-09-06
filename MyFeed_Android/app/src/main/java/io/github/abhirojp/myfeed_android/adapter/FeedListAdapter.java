package io.github.abhirojp.myfeed_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.abhirojp.myfeed_android.R;
import io.github.abhirojp.myfeed_android.data.DataModel;
import io.github.abhirojp.myfeed_android.fragment.FeedListFragment;

import static android.view.View.GONE;

/**
 * Created by abhiroj on 6/9/17.
 */

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.ViewHolder> {

    private static final String TAG= FeedListAdapter.class.getSimpleName();

    private Context context;
    private ArrayList<DataModel> dataList;

    public FeedListAdapter(Context context, ArrayList<DataModel> dataList){
        Log.d(TAG,"Initalizing");
        this.context=context;
        this.dataList=dataList;
    }

    @Override
    public FeedListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.list_item,parent,false);
        FeedListAdapter.ViewHolder vh=new FeedListAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG,"creating an item for pos "+position);
        DataModel item=dataList.get(position);
        holder.api_title.setText(item.getTitle());
        holder.api_name.setText(item.getName());
        if (item.getText()!=null) {
            holder.api_text.setText(item.getText());
        } else {
            holder.api_text.setVisibility(GONE);
        }
        if(item.getImageUrl()!=null){
            // TODO: fetch image using Picasso API
        }else{
            holder.api_imageUrl.setVisibility(GONE);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public Context getContext() {
        return context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView api_title;
        public ImageView api_imageUrl;
        public TextView api_text;
        public TextView api_name;

        public ViewHolder(View itemView) {
            super(itemView);
            api_imageUrl=itemView.findViewById(R.id.api_imageUrl);
            api_title=itemView.findViewById(R.id.api_title);
            api_text=itemView.findViewById(R.id.api_text);
            api_name=itemView.findViewById(R.id.api_name);
        }
    }
}
