package com.example.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dashboard.adapter.CakeAdapter;
import com.example.dashboard.adapter.CartAdapter;
import com.example.dashboard.adapter.Utility;
import com.example.dashboard.models.CartItems;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class favorites extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RecyclerView mRecyclerView;
    CartAdapter  mAdapter;
    TextView mTextViewt2,mTextViewTotal;
    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        int total=0;
        Button btn = (Button)findViewById(R.id.miBtn);
        mRecyclerView=(RecyclerView)findViewById(R.id.rc);
        mTextViewt2=(TextView)findViewById(R.id.tvvv2);
        mTextViewTotal=(TextView)findViewById(R.id.Total);
        container=(LinearLayout)findViewById(R.id.container);



        mAdapter = new CartAdapter(favorites.this, Utility.mCartItems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                favorites.this, RecyclerView.VERTICAL, false));

        for(CartItems val: Utility.mCartItems){
            total= total+60;
        }
        container.setVisibility(Utility.mCartItems.size()>0?View.VISIBLE:View.GONE);
        mTextViewt2.setText(total+"$");
        mTextViewTotal.setText(total+"$");
        mRecyclerView.setAdapter(mAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i=new Intent(getApplicationContext(),Create.class);
                startActivity(i);
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.cart);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.miPerson:
                        startActivity(new Intent(getApplicationContext(),Account.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.cart:
                        return true;
                    case R.id.miHome:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.miSearch:
                        startActivity(new Intent(getApplicationContext(),search.class));
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }
        });
    }
}