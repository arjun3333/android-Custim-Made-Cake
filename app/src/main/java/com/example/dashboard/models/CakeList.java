package com.example.dashboard.models;

public class CakeList {
    public int mStringImg;

    public boolean ismABoolean() {
        return mABoolean;
    }

    public void setmABoolean(boolean mABoolean) {
        this.mABoolean = mABoolean;
    }

    public boolean mABoolean;

    public int getmStringImg() {
        return mStringImg;
    }

    public void setmStringImg(int mStringImg) {
        this.mStringImg = mStringImg;
    }

    public String getmStringName() {
        return mStringName;
    }

    public void setmStringName(String mStringName) {
        this.mStringName = mStringName;
    }

    public String getmStringDescm() {
        return mStringDescm;
    }

    public void setmStringDescm(String mStringDescm) {
        this.mStringDescm = mStringDescm;
    }

    public String getmStringPrice() {
        return mStringPrice;
    }

    public void setmStringPrice(String mStringPrice) {
        this.mStringPrice = mStringPrice;
    }

    public int getmStrExtra() {
        return mStrExtra;
    }

    public void setmStrExtra(int mStrExtra) {
        this.mStrExtra = mStrExtra;
    }

    public String mStringName;
    public String mStringDescm;



    public  String mStringPrice;
    public int mStrExtra;
    public CakeList(int mStringImg, String mStringName, String mStringDescm,
                    String mStringPrice, int mStrExtra,boolean mABoolean
    ) {
        this.mStringImg = mStringImg;
        this.mStringName = mStringName;
        this.mStringDescm = mStringDescm;
        this.mStringPrice = mStringPrice;
        this.mStrExtra = mStrExtra;
        this.mABoolean=mABoolean;
    }
}