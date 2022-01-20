package com.example.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.dashboard.adapter.Utility;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Account extends AppCompatActivity {
    Button btn11, btn12, btn22;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        btn11 = findViewById(R.id.loginbtn);
        btn12 = findViewById(R.id.favbtn);
        btn22 = findViewById(R.id.odrbtn);
        replaceFragment(new Login(),null);

        Button btn = (Button)findViewById(R.id.miBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i=new Intent(getApplicationContext(),Create.class);
                startActivity(i);
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //replaceFragment(new Login());
                replaceFragment(new Login(),null);
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utility.isfav=1;
                Intent newIntent = new Intent(Account.this,search.class);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newIntent);


                // replaceFragment(new Type(),null);
            }
        });
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Order(),null);
            }
        });
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.miPerson);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.miHome:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.miPerson:
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
    /* private void replaceFragment(Fragment fragment) {
         FragmentManager fragmentManager = getSupportFragmentManager();
         FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
         fragmentTransaction.replace(R.id.frame2,fragment);
         fragmentTransaction.commit();
     }*/
    public void replaceFragment(Fragment fragment,
                                Bundle bundle) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        fragment.setArguments(bundle);
        ft.replace(R.id.frame2, fragment);
        ft.commit();
    }

    public void updateLogin() {
        btn11.setText("Logout");
    }
    public Button getLoginButton(){
        return btn11;
    }
}