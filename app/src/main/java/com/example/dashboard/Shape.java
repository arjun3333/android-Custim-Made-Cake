package com.example.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dashboard.adapter.Utility;


public class Shape extends Fragment implements View.OnClickListener {
    View view;
    LinearLayout mLinearLayout1, mLinearLayout2, mLinearLayout3, mLinearLayout4, mLinearLayout5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_shape, container, false);
        mLinearLayout1 = view.findViewById(R.id.l1);
        mLinearLayout2 = view.findViewById(R.id.l2);
        mLinearLayout3 = view.findViewById(R.id.l3);
        mLinearLayout4 = view.findViewById(R.id.l4);
        mLinearLayout5 = view.findViewById(R.id.l5);

        mLinearLayout1.setOnClickListener(this);
        mLinearLayout2.setOnClickListener(this);
        mLinearLayout3.setOnClickListener(this);
        mLinearLayout4.setOnClickListener(this);
        mLinearLayout5.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.l1:
                Toast.makeText(getActivity(), "Shape  :   Heart ", Toast.LENGTH_SHORT).show();
                Utility.shape= "Heart";
                break;
            case R.id.l2:
                Toast.makeText(getActivity(), "Shape  :    Round ", Toast.LENGTH_SHORT).show();
                Utility.shape= "Round";
                break;
            case R.id.l3:
                Toast.makeText(getActivity(), "Shape  :   Square ", Toast.LENGTH_SHORT).show();
                Utility.shape= "Square";
                break;
            case R.id.l4:
                Toast.makeText(getActivity(), "Shape  :   Hexagon ", Toast.LENGTH_SHORT).show();
                Utility.shape= "Hexagon";
                break;
            case R.id.l5:
                Toast.makeText(getActivity(), "Shape  :   Numeric ", Toast.LENGTH_SHORT).show();
                Utility.shape= "Numeric";
                break;

        }
    }
}