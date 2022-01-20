package com.example.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dashboard.adapter.Utility;


public class Size extends Fragment implements View.OnClickListener {

    View view;
    LinearLayout mLinearLayout1,mLinearLayout2,mLinearLayout3,mLinearLayout4,mLinearLayout5,
            mLinearLayout6,mLinearLayout7;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_size, container, false);
        mLinearLayout1=view.findViewById(R.id.l1);
        mLinearLayout2=view.findViewById(R.id.l2);
        mLinearLayout3=view.findViewById(R.id.l3);
        mLinearLayout4=view.findViewById(R.id.l4);
        mLinearLayout5=view.findViewById(R.id.l5);
        mLinearLayout6=view.findViewById(R.id.l6);
        mLinearLayout7=view.findViewById(R.id.l7);
        mLinearLayout1.setOnClickListener(this);
        mLinearLayout2.setOnClickListener(this);
        mLinearLayout3.setOnClickListener(this);
        mLinearLayout4.setOnClickListener(this);
        mLinearLayout5.setOnClickListener(this);
        mLinearLayout6.setOnClickListener(this);
        mLinearLayout7.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.l1:
                Toast.makeText(getActivity(), "Size  :   1 ", Toast.LENGTH_SHORT).show();
                Utility.size= "1";
                break;
            case R.id.l2:
                Toast.makeText(getActivity(), "Size  :    2 ", Toast.LENGTH_SHORT).show();
                Utility.size= "2";
                break;
            case R.id.l3:
                Toast.makeText(getActivity(), "Size  :   3 ", Toast.LENGTH_SHORT).show();
                Utility.size= "3";
                break;
            case R.id.l4:
                Toast.makeText(getActivity(), "Size  :   4 ", Toast.LENGTH_SHORT).show();
                Utility.size= "4";
                break;
            case R.id.l5:
                Toast.makeText(getActivity(), "Size  :   5 ", Toast.LENGTH_SHORT).show();
                Utility.size= "5";
                break;
            case R.id.l6:
                Toast.makeText(getActivity(), "Size  :   6 ", Toast.LENGTH_SHORT).show();
                Utility.size= "6";
                break;
            case R.id.l7:
                Toast.makeText(getActivity(), "Size  :   7 ", Toast.LENGTH_SHORT).show();
                Utility.size= "7";
                break;

        }
    }
}