package com.example.dashboard.models;

import java.io.Serializable;

public class CakeType implements Serializable {

    public String getmStringName() {
        return mStringName;
    }

    public void setmStringName(String mStringName) {
        this.mStringName = mStringName;
    }

    public String getmStringDesc() {
        return mStringDesc;
    }

    public void setmStringDesc(String mStringDesc) {
        this.mStringDesc = mStringDesc;
    }

    public String getmStringImage() {
        return mStringImage;
    }

    public void setmStringImage(String mStringImage) {
        this.mStringImage = mStringImage;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    String mStringName,mStringDesc,mStringImage,mId;
    public CakeType(String name, String desc, String img,
                    String id) {
        this.mStringName = name;
        this.mStringDesc = desc;
        this.mStringImage = img;
        this.mId=id;

    }
}
