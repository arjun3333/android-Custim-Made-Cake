package com.example.dashboard.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.R;
import com.example.dashboard.models.CakeList;


import java.util.ArrayList;
import java.util.List;

public class homeadapter extends  RecyclerView.Adapter<homeadapter.ViewHolder>{
    List<CakeList> mProducts=new ArrayList<>();
    Context mContext;
    // RecyclerView recyclerView;
    public homeadapter(Context mContext, List<CakeList> homemodelArrayList) {

        this.mContext = mContext;
        mProducts=homemodelArrayList;
        Toast.makeText(mContext,Utility.isfav+"",Toast.LENGTH_SHORT).show();

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        viewHolder.setIsRecyclable(false);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final CakeList myListData = mProducts.get(position);


        holder.imageViewFav.setVisibility(View.VISIBLE);
        holder.textView.setText(mProducts.get(position).mStringName);
        holder.imageView.setImageResource(mProducts.get(position).mStringImg);
        if (Utility.isfav==0&&   Utility.mBooleans[position] ==  true) {
            holder.imageViewFav.setImageResource(R.drawable.heart2);


        } else {
            holder.imageViewFav.setImageResource(R.drawable.heart1);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Utility.mBooleans[position] == false) {
                    Utility.modelArrayList.get(position).setmABoolean(true);
                    Utility.mBooleans[position]=true;
                    notifyDataSetChanged();

                } else {
                    Utility.modelArrayList.get(position).setmABoolean(false);
                    Utility.mBooleans[position]=false;
                    notifyDataSetChanged();

                }
            }
            //Toast.makeText(mContext, position+"", Toast.LENGTH_SHORT).show();


        });



    }


    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView,imageViewFav;
        public TextView textView;
        // public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.img);
            this.textView = (TextView) itemView.findViewById(R.id.cakename);
            this.imageViewFav = (ImageView) itemView.findViewById(R.id.fav);

            // relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}