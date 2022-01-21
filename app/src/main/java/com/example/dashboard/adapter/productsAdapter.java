package com.example.dashboard.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dashboard.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public  class productsAdapter extends RecyclerView.Adapter {
    List<Product> mProducts;
    Context mContext;
    public static final int LOADING_ITEM = 0;
    public static final int PRODUCT_ITEM = 1;
    int LoadingItemPos;
    public boolean loading = false;

    public productsAdapter(Context mContext) {
        mProducts = new ArrayList<>();
        this.mContext = mContext;
    }
    //method to add products as soon as they fetched
    public void addProducts(List<Product> products) {
        int lastPos = mProducts.size();
        this.mProducts.addAll(products);
        notifyItemRangeInserted(lastPos, mProducts.size());
    }


    @Override
    public int getItemViewType(int position) {
        Product currentProduct = mProducts.get(position);
        if (currentProduct.isLoading()) {
            return LOADING_ITEM;
        } else {
            return PRODUCT_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //Check which view has to be populated
        //  if (viewType == LOADING_ITEM) {
        View row = inflater.inflate(R.layout.listitem, parent, false);
        return new ViewHolder(row);
//        } else if (viewType == PRODUCT_ITEM) {
//            View row = inflater.inflate(R.layout.customrowproduct, parent, false);
//            return new ProductHolder(row);
//        }
        //return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,
                                 @SuppressLint("RecyclerView") int position) {
        //get current product
        final Product currentProduct = mProducts.get(position);
        //   if (holder instanceof ProductHolder) {
        ViewHolder productHolder = (ViewHolder) holder;
        //bind products information with view
        Picasso.with(mContext).load(currentProduct.getImageResourceId()).into(productHolder.imageView);
        productHolder.textView.setText(currentProduct.getProductName());
        //productHolder.textViewProductPrice.setText(currentProduct.getProductPrice());
        if(Utility.isfav==1){
            ((ViewHolder) holder).imageViewFav.setVisibility(View.INVISIBLE);
        }else if(Utility.isfav==0){
            ((ViewHolder) holder).imageViewFav.setVisibility(View.VISIBLE);

            if( Utility.mBooleans[position]==false){
                ((ViewHolder) holder).imageViewFav.setImageResource(R.drawable.heart1);
            }else{
                ((ViewHolder) holder).imageViewFav.setImageResource(R.drawable.heart2);
            }
        }

        ((ViewHolder) holder).imageViewFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Utility.isfav == 0 &&  Utility.mBooleans[position] == false) {
                    Utility.mBooleans[position]=true;
                    CartProduct product = new CartProduct(currentProduct.getImageResourceId(),
                            currentProduct.getProductName(),
                            currentProduct.getProductPrice(),
                            true);
                    Utility.products.add(product);
                    notifyDataSetChanged();
                }
                else if( Utility.mBooleans[position] == true){
                    Utility.mBooleans[position]=false;
                    if( Utility.products.size()>0) {
                        for (CartProduct mCartProduct : Utility.products) {
                            if (mCartProduct.getProductName().equals(currentProduct.getProductName())) {
                                Utility.products.remove(mCartProduct);
                            }
                        }
                        notifyDataSetChanged();
                    }
                }

            }

        });
        //}

    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    //Holds view of product with information
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
    //holds view of loading item
    private class LoadingHolder extends RecyclerView.ViewHolder {
        public LoadingHolder(View itemView) {
            super(itemView);
        }
    }

    //method to show loading
    public void showLoading() {
        Product product = new Product();
        product.setLoading(true);
        mProducts.add(product);
        LoadingItemPos = mProducts.size();
        notifyItemInserted(mProducts.size());
        loading = true;
    }

    //method to hide loading
    public void hideLoading() {
        if (LoadingItemPos <= mProducts.size()) {
            // mProducts.remove(LoadingItemPos - 1);
            notifyItemRemoved(LoadingItemPos);
            loading = false;
        }

    }
}