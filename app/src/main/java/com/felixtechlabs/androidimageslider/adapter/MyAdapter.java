package com.felixtechlabs.androidimageslider.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.felixtechlabs.androidimageslider.ItemClickListener;
import com.felixtechlabs.androidimageslider.R;
import com.felixtechlabs.androidimageslider.activity.MainActivity;
import com.felixtechlabs.androidimageslider.activity.SecondActivity;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<String> moviesList;
    private Context cntx;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener =  listener;

    }


    public MyAdapter(List<String> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item, parent, false);

        cntx = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //  Log.e(TAG, ""+moviesList.get(position) );

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;

        final Bitmap bm = BitmapFactory.decodeFile(moviesList.get(position), options);
        holder.iv_img.setImageBitmap(bm);

        holder.iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondActivity.bm = bm;
                cntx.startActivity(new Intent(cntx, SecondActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {
        public ImageView iv_img;
        public ItemClickListener itemClickListener;

        public MyViewHolder(View view) {
            super(view);
            iv_img = (ImageView) view.findViewById(R.id.image);
        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }
    }


}
