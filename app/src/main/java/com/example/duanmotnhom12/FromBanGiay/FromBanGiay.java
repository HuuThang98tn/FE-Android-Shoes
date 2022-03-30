package com.example.duanmotnhom12.FromBanGiay;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.example.duanmotnhom12.Adapter.ViewPagerAdapter;
import com.example.duanmotnhom12.Model.ModelGioHang;
import com.example.duanmotnhom12.Model.ModelSanPham;
import com.example.duanmotnhom12.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class FromBanGiay extends AppCompatActivity {

    public static ArrayList<ModelGioHang> modelGioHangs;
    public static AHBottomNavigation ahBottomNavigation;
    public static AHBottomNavigationViewPager ahBottomNavigationViewPager;
    public static ViewPagerAdapter pagerAdapter;
    public static int sum = 0;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_ban_giay);

        ahBottomNavigation = findViewById(R.id.bottom_nav_ah);
        ahBottomNavigationViewPager = findViewById(R.id.view_Pager_ah);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ahBottomNavigationViewPager.setAdapter(pagerAdapter);
        ahBottomNavigationViewPager.setPagingEnabled(true);

        // setItem AHbottom
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.home, R.color.color_tab);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.list_text, R.color.color_tab);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.heart, R.color.color_tab);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_4, R.drawable.shopping_cart_empty_side_view, R.color.color_tab);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.tab_5, R.drawable.user, R.color.color_tab);

        ahBottomNavigation.addItem(item1);
        ahBottomNavigation.addItem(item2);
        ahBottomNavigation.addItem(item3);
        ahBottomNavigation.addItem(item4);
        ahBottomNavigation.addItem(item5);

        checkMang();
        setAhBottomNavigation();
        setAhBottomNavigation_2();
        setHassMap();
    }

    public void checkMang() {
        SharedPreferences sharedPreferences =getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json =sharedPreferences.getString("tasklist",null);
        Type type = new TypeToken<ArrayList<ModelGioHang>>(){}.getType();
        FromBanGiay.modelGioHangs =gson.fromJson(json,type);
        Log.d("aaacx",sharedPreferences.getString("tasklist",null)+"");
        if (modelGioHangs != null) {
            //Mảng giỏ hàng đã có dữ liệu
        } else {
            modelGioHangs = new ArrayList<>();
            // mảng giỏ hàng chưa có dữ liệu
        }
    }

    public void setAhBottomNavigation() {
        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                ahBottomNavigationViewPager.setCurrentItem(position);
                return true;
            }
        });
    }

    public void setAhBottomNavigation_2() {
        ahBottomNavigationViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ahBottomNavigation.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public static int getmCountProduct() {
        return sum;
    }

    public void setHassMap() {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("soLuong", getmCountProduct() + "");
        Log.d("daaaa",(map.put("soLuong", getmCountProduct() + ""))+"");
    }
}