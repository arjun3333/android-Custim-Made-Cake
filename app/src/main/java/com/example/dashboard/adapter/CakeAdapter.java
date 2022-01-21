package com.example.dashboard.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.Details;
import com.example.dashboard.R;
import com.example.dashboard.models.CakeType;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CakeAdapter extends RecyclerView.Adapter {
    List<CakeType> mProducts;
    Context mContext;
    public static final int LOADING_ITEM = 0;
    public static final int PRODUCT_ITEM = 1;
    int LoadingItemPos;
    public boolean loading = false;

    public CakeAdapter(Context mContext, List<CakeType> homemodelArrayList) {

        mProducts = new ArrayList<>();
        this.mContext = mContext;
        mProducts=homemodelArrayList;
    }



    @Override
    public int getItemViewType(int position) {
        CakeType currentProduct = mProducts.get(position);

        return PRODUCT_ITEM;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //Check which view has to be populated

        View row = inflater.inflate(R.layout.cakeitem, parent, false);
        return new ProductHolder(row);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //get current product
        final CakeType mHomemodell = mProducts.get(position);
        if (holder instanceof ProductHolder) {
            ProductHolder productHolder = (ProductHolder) holder;
            //bind products information with view
            Picasso.with(mContext).load(mHomemodell.getmStringImage()).into(productHolder.imageViewProductThumb);
            // productHolder.textViewProductName.setText(mHomemodell.getName());
            //productHolder.textViewProductPrice.setText(mHomemodell.getCity());
            productHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // user selected product now you can show details of that product
                    //Toast.makeText(mContext, "Selected "+currentProduct.getProductName(), Toast.LENGTH_SHORT).show();
                    // callClass(mHomemodell);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent=new Intent(mContext,
                            Details.class);
                    mIntent.putExtra("Data",  mProducts.get(position));
                    mContext.startActivity(mIntent);
                }
            });
        }

    }

    private void callClass(CakeType mHomemodell) {
//        Intent mIntent=new Intent(mContext, shoedetailpage.class);
//        mIntent.putExtra("ShoeModel",  mHomemodell);
//        mContext.startActivity(mIntent);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    //Holds view of product with information
    private class ProductHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProductThumb;
        TextView textViewProductName, textViewProductPrice, textViewNew;


        public ProductHolder(View itemView) {
            super(itemView);
            imageViewProductThumb = itemView.findViewById(R.id.img);
            //textViewProductName = itemView.findViewById(R.id.textViewProductName);
            //textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);


        }
    }
    //holds view of loading item
    private class LoadingHolder extends RecyclerView.ViewHolder {
        public LoadingHolder(View itemView) {
            super(itemView);
        }
    }



}


