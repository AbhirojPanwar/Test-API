package io.github.abhirojp.myfeed_android.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.reflect.Array;
import java.util.ArrayList;

import io.github.abhirojp.myfeed_android.R;
import io.github.abhirojp.myfeed_android.callback.OnFeedItemClick;
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d(TAG,"creating an item for pos "+position);
        final DataModel item=getList().get(position);
        holder.api_title.setText(item.getTitle());
        holder.api_name.setText(item.getName());
        if(checkValue(item.getImageUrl())){
            final ImageView imageView=new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            if(checkValue(item.getText()))
            holder.child.addView(imageView,new ViewGroup.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT));
            else{
                holder.child.addView(imageView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            Target target=new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    imageView.setImageBitmap(bitmap);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            };
            if(imageView.getDrawable()==null)
            requestImage(target,item.getImageUrl());
        }if(checkValue(item.getText())){
            TextView view1=new TextView(getContext());
            view1.setTextAppearance(getContext(),android.R.style.TextAppearance_Large);
            view1.setPadding(5,5,5,5);
            holder.child.addView(view1,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        holder.markButton.setOnClickListener(new View.OnClickListener() {
            //TODO: Backend API operations
            @Override
            public void onClick(View view) {
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
                onFeedItemClick.passData(item);
            }
        });
    }

    private void requestImage(Target target,String imageUrl) {
    Picasso p=Picasso.with(getContext());
        p.setDebugging(true);
        p.load(imageUrl).into(target);
    }

    private boolean checkValue(String text) {
    return text!=null && text.length()>0;
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
