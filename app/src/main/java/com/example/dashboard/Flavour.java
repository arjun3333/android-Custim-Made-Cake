package com.example.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dashboard.adapter.Utility;


public class Flavour extends Fragment implements View.OnClickListener {
    View view;
    TextView mTextView1,mTextView2,mTextView3,mTextView4,mTextView5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_flavour, container, false);
        mTextView1 = view.findViewById(R.id.textView5);
        mTextView2 = view.findViewById(R.id.textView6);
        mTextView3 = view.findViewById(R.id.textView7);
        mTextView4 = view.findViewById(R.id.textView8);
        mTextView5 = view.findViewById(R.id.textView8);
        mTextView1.setOnClickListener(this);
        mTextView2.setOnClickListener(this);
        mTextView3.setOnClickListener(this);
        mTextView4.setOnClickListener(this);
        mTextView5.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView5:
                Toast.makeText(getActivity(), "Flavour  :    1 ", Toast.LENGTH_SHORT).show();
                Utility.flavour= "Flavour 1";
                break;
            case R.id.textView6:
                Toast.makeText(getActivity(), "Flavour  :    2", Toast.LENGTH_SHORT).show();
                Utility.flavour= "Flavour 2";
                break;
            case R.id.textView7:
                Toast.makeText(getActivity(), "Flavour  :    3", Toast.LENGTH_SHORT).show();
                Utility.flavour= "Flavour 3";
                break;
            case R.id.textView8:
                Toast.makeText(getActivity(), "Flavour  :    4 ", Toast.LENGTH_SHORT).show();
                Utility.flavour= "Flavour 4";
                break;


        }
    }
}