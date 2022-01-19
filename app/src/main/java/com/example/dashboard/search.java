package com.example.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dashboard.adapter.CartProduct;
import com.example.dashboard.adapter.Product;
import com.example.dashboard.adapter.Utility;
import com.example.dashboard.adapter.homeadapter;
import com.example.dashboard.adapter.productsAdapter;
import com.example.dashboard.models.CakeList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class search extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageButton img;
    CakeList mCakeList;
    RecyclerView mRecyclerView;
    com.example.dashboard.adapter.productsAdapter productsAdapter;
    int[] staticimages = {R.drawable.cake16,
            R.drawable.cake2,
            R.drawable.cake4, R.drawable.cake5,R.drawable.cake6,};

    String[] cakeNames = {"Chocolate Cake","Vanila Cake","Strawberry Cake",
            "PineApple Cake", "Avacoda Cake"};
    String[] cakeDesc = {"This Chocolate Cake is around 637 calories, with 38 grams of fat, 32 grams of carbs, and 20 grams of sugar.",
            "This Chocolate Cake is around 637 calories, with 38 grams of fat, 32 grams of carbs, and 20 grams of sugar.",
            "This Chocolate Cake is around 637 calories, with 38 grams of fat, 32 grams of carbs, and 20 grams of sugar.",
            "This Chocolate Cake is around 637 calories, with 38 grams of fat, 32 grams of carbs, and 20 grams of sugar.",
            "This Chocolate Cake is around 637 calories, with 38 grams of fat, 32 grams of carbs, and 20 grams of sugar."};

    private homeadapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Utility. modelArrayList.clear();
        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerViewProducts) ;
        productsAdapter = new productsAdapter(this);
        feedData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,
                2,//span count no of items in single row
                GridLayoutManager.VERTICAL,//Orientation
                false);//reverse scrolling of recyclerview
        //set layout manager as gridLayoutManager
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(productsAdapter);
        // ImageButton img = (ImageButton) findViewById(R.id.avacoda);
       /* img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i=new Intent(getApplicationContext(),Details.class);
                startActivity(i);
            }
        });*/

        //Button btn = (Button)findViewById(R.id.miBtn);
        //btn.setOnClickListener(new View.OnClickListener() {
        // @Override
        // public void onClick(View v) {
        // TODO Auto-generated method stub
        //   Intent i=new Intent(getApplicationContext(),Create.class);
        //   startActivity(i);
        //}
        //});

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.miSearch);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.miPerson:
                        startActivity(new Intent(getApplicationContext(),Account.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.miSearch:
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(),favorites.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.miHome:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }
        });
    }
    private void feedData() {
        //show loading in recyclerview
        //productsAdapter.showLoading();
        final List<Product> products = new ArrayList<>();

        if(Utility.isfav==1){
            for(CartProduct mCartProduct: Utility.products){
                Product mProduct = (Product) mCartProduct;

                products.add(mCartProduct);
            }
            productsAdapter.addProducts(products);
        }else if(Utility.isfav==0) {
            int[] imageUrls = {R.drawable.cake16,
                    R.drawable.cake2,
                    R.drawable.cake4, R.drawable.cake5,R.drawable.cake6,};
            String[] ProductName = {"Chocolate Cake","Vanila Cake","Strawberry Cake",
                    "PineApple Cake", "Avacoda Cake"};

            String[] ProductPrice = {"$594", "$500", "$200", "$199", "$199"};
            boolean[] isNew = {false, false, false, false,false};
            for (int i = 0; i < imageUrls.length; i++) {
                Product product = new Product(imageUrls[i],
                        ProductName[i],
                        ProductPrice[i],
                        isNew[i]);
                products.add(product);
            }
            productsAdapter.addProducts(products);
        }
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //hide loading
                productsAdapter.hideLoading();
                //add products to recyclerview
                productsAdapter.addProducts(products);
            }
        }, 1000);*/
    }
    /*private void loadData() {

        for(int i=0;i<staticimages.length;i++){

           // if(Utility.isfav==0 ) {
              CakeList  mCakeList = new CakeList(staticimages[i], cakeNames[i],
                        cakeDesc[i], "$20", R.drawable.heart1, false);
                Utility.modelArrayList.add(mCakeList);
            //}

        }

        mAdapter = new homeadapter(search.this,Utility.modelArrayList);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);

        // at last set adapter to recycler view.
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Toast.makeText(search.this,  Utility. modelArrayList.size()+"", Toast.LENGTH_SHORT).show();

    }*/

}