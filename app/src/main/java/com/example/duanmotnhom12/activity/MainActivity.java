package com.example.duanmotnhom12.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.duanmotnhom12.CheckNetwork.AppUtil;
import com.example.duanmotnhom12.FromDangNhap.FromDangNhap;
import com.example.duanmotnhom12.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadSplashScreen();
    }
    public void loadSplashScreen(){
        if (AppUtil.isNetWorkAvailable(this)){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity.this, FromDangNhap.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
            }, 0000);
        }else {
            Toast.makeText(this,"Không có kế nối internet",Toast.LENGTH_LONG).show();
        }
    }
}