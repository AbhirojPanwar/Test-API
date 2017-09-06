package io.github.abhirojp.myfeed_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.abhirojp.myfeed_android.R;
import io.github.abhirojp.myfeed_android.data.DataModel;

import static android.view.View.GONE;

/**
 * Created by abhiroj on 6/9/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DataModel> dataList;

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.list_item,parent,false);
        ListAdapter.ViewHolder vh=new ListAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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
        return 0;
    }

    public Context getContext() {
        return context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView api_title;
        ImageView api_imageUrl;
        TextView api_text;
        TextView api_name;

        public ViewHolder(View itemView) {
            super(itemView);
            api_imageUrl=itemView.findViewById(R.id.api_imageUrl);
            api_title=itemView.findViewById(R.id.api_title);
            api_text=itemView.findViewById(R.id.api_text);
            api_name=itemView.findViewById(R.id.api_name);
        }
    }
}
