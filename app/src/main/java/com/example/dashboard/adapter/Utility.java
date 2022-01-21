package com.example.dashboard.adapter;

import com.example.dashboard.models.CakeList;
import com.example.dashboard.models.CartItems;
import com.example.dashboard.models.CustomizedModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Utility {
    public static int isfav=1;
    public static List<CakeList> modelArrayList = new ArrayList<>();
    public static List<CartItems> mCartItems = new ArrayList<>();
    public static List<CustomizedModel> customizedModelList = new ArrayList<>();
    public static CopyOnWriteArrayList<CartProduct> products = new CopyOnWriteArrayList<>();
    public static Boolean[]mBooleans={false,false,false,false,false};

    public  static String type="";
    public static  String shape="";
    public static  String size="";
    public static String tire="";
    public static String flavour="";
    public static  String filling="";
    public static  String notes="";
    public static  String Image="";
}
