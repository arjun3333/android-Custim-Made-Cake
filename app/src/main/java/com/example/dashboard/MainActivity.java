package com.example.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


import com.example.dashboard.adapter.CakeAdapter;
import com.example.dashboard.adapter.Utility;
import com.example.dashboard.adapter.homeadapter;
import com.example.dashboard.models.CakeType;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity  {
    BottomNavigationView bottomNavigationView;
    DatabaseReference mDatabaseReference;
    RecyclerView cakeGridview;
    private CakeAdapter mAdapter;
    List<CakeType> homemodelArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cakeGridview = findViewById(R.id.recyclerViewProducts);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("cakes");
        Button btn = (Button)findViewById(R.id.miBtn);
        Button btn1 = (Button)findViewById(R.id.orderbtn);
        loadData();



        //Toast.makeText(this, homemodelArrayList.get(0).getmStringName(), Toast.LENGTH_SHORT).show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i=new Intent(getApplicationContext(),Create.class);
                startActivity(i);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i=new Intent(getApplicationContext(),search.class);
                startActivity(i);
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.miHome);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.miPerson:
                        startActivity(new Intent(getApplicationContext(),Account.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.miHome:
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(),favorites.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.miSearch:
                        Utility.isfav=0;
                        startActivity(new Intent(getApplicationContext(),search.class));
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }
        });
    }

    public void loadData() {

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while (iterator.hasNext()) {

                    DataSnapshot next = iterator.next();
                    // Cars pojo = next.getValue(Cars.class);
                    //Toast.makeText(getActivity(),pojo.getName(),Toast.LENGTH_SHORT).show();
                    // if(pojo.getInstitute().equalsIgnoreCase("Guru Nanak Dev University College Verka")){


                    CakeType mUsers =
                            new CakeType((String) next.child("name").getValue(),
                                    (String) next.child("desc").getValue(),
                                    (String) next.child("image").getValue(),
                                    (String) next.child("id").getValue());
                    homemodelArrayList.add(mUsers);

                    // }
                }
                //Toast.makeText(MainActivity.this, "sgsdg"+homemodelArrayList.get(0).getmStringName(), Toast.LENGTH_SHORT).show();
                // GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.HORIZONTAL, false);
                mAdapter = new CakeAdapter(MainActivity.this,
                        homemodelArrayList);
                cakeGridview.setLayoutManager(new LinearLayoutManager(
                        MainActivity.this, RecyclerView.HORIZONTAL, false));
                cakeGridview.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }


}
