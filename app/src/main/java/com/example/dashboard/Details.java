package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dashboard.adapter.Product;
import com.example.dashboard.adapter.Utility;
import com.example.dashboard.models.CakeType;
import com.example.dashboard.models.CartItems;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {
    Button bttn, bttn1;
    ImageView mImageView;
    TextView mTextView,mTextView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mImageView = (ImageView) findViewById(R.id.imageView2);
        mTextView = (TextView) findViewById(R.id.text1);
        mTextView2 = (TextView) findViewById(R.id.detail3);

        CakeType mCakeType= (CakeType) getIntent().getSerializableExtra("Data");
        Picasso.with(this).load(mCakeType.getmStringImage()).into(mImageView);
        mTextView.setText(mCakeType.getmStringName());
        mTextView2.setText(mCakeType.getmStringDesc());
        Button bttn = (Button) findViewById(R.id.detailshome);
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        Button bttn1 = (Button) findViewById(R.id.button7);
        bttn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                CartItems product = new CartItems(mCakeType.getmStringImage(),
                        mCakeType.getmStringName(),
                        "60$",
                        true);
                Utility. mCartItems.add(product);
                Toast.makeText(Details.this, "Item Successfully Added into the Cart", Toast.LENGTH_SHORT).show();

            }
        });
    }
}