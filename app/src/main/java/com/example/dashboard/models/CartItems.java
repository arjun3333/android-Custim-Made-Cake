package com.example.dashboard.models;

import java.io.Serializable;

public class CartItems implements Serializable {
    private String imageResourceId;
    private String productName;
    private String productPrice;
    private boolean isLoading = false;
    private boolean isNew = false;

    public CartItems(String imageResourceId, String productName, String productPrice, boolean isNew) {
        this.imageResourceId = imageResourceId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.isNew = isNew;
    }



    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isNew() {
        return isNew;
    }

    public String getImageResourceId() {
        return imageResourceId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
