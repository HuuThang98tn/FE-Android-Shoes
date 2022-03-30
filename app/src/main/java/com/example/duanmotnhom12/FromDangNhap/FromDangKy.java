package com.example.duanmotnhom12.FromDangNhap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.duanmotnhom12.R;

public class FromDangKy extends AppCompatActivity {

    Button btn_DangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_dang_ky);

        Toolbar tb_dangKy = findViewById(R.id.tb_dangKy);
        setSupportActionBar(tb_dangKy);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        anhXa();
        dangKyTaiKhoan();

    }

    //back
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void anhXa() {
        btn_DangKy = findViewById(R.id.btn_DangKy);
    }

    public void dangKyTaiKhoan() {
        btn_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FromDangKy.this, FromDangNhap.class));
                finish();
            }
        });
    }
}