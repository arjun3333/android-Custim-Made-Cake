package com.example.dashboard.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.Details;
import com.example.dashboard.R;
import com.example.dashboard.models.CakeType;
import com.example.dashboard.models.CartItems;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {
    List<CartItems> mProducts;
    Context mContext;
    public static final int LOADING_ITEM = 0;
    public static final int PRODUCT_ITEM = 1;
    int LoadingItemPos;
    public boolean loading = false;

    public CartAdapter(Context mContext, List<CartItems> homemodelArrayList) {

        mProducts = new ArrayList<>();
        this.mContext = mContext;
        mProducts=homemodelArrayList;
    }



    @Override
    public int getItemViewType(int position) {
        CartItems currentProduct = mProducts.get(position);

        return PRODUCT_ITEM;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //Check which view has to be populated

        View row = inflater.inflate(R.layout.cartitem, parent, false);
        return new CartAdapter.ProductHolder(row);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //get current product
        final CartItems mHomemodell = mProducts.get(position);
        if (holder instanceof CartAdapter.ProductHolder) {
            CartAdapter.ProductHolder productHolder = (CartAdapter.ProductHolder) holder;
            //bind products information with view
            Picasso.with(mContext).load(mHomemodell.getImageResourceId()).
                    into(productHolder.imageViewProductThumb);
            productHolder.name.setText(mHomemodell.getProductName());
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


    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    //Holds view of product with information
    private class ProductHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProductThumb;
        TextView name, textViewProductPrice, textViewNew;


        public ProductHolder(View itemView) {
            super(itemView);
            imageViewProductThumb = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
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


