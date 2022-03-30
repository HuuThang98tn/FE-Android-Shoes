package com.example.duanmotnhom12.FromBanGiay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.Model.ModelGioHang;
import com.example.duanmotnhom12.Model.ModelSanPham;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.AnimationUlti;
import com.example.duanmotnhom12.activity.Gmail;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class MainActivityViewChiTietSanPham extends AppCompatActivity {

    ImageView img_chiTiet, img_start, rd_img;
    TextView tv_ten, tv_gia, tv_hang, tv_ngaySX, tv_trangThai, tv_mieuTa, tv_binhLuan;
    Button btn_themGioHang, btn_lienHe;
    View view;
    int id_sp = 0;
    String img_chiTietSP = "";
    String tv_ten_ = "";
    int tv_gia_ = 0;
    String tv_hang_ = "";
    String tv_ngaySX_ = "";
    String tv_trangThai_ = "";
    String tv_mieuTa_ = "";
    String tv_binhLuan_ = "";
    TextView tv_numberPhone;

    public static final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_chi_tiet_san_pham);

        Toolbar toolbar = findViewById(R.id.tb_chiTiet);
        setSupportActionBar(toolbar);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        anhXa();
        getInfoChiTietSanPham();
        checkTrangThai();
        btnLienHe();
        btn_themVaoGioHang();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void anhXa() {
        img_chiTiet = findViewById(R.id.imageView7);
        img_start = findViewById(R.id.img_start);
        tv_ten = findViewById(R.id.textView22);
        tv_gia = findViewById(R.id.textView23);
        tv_hang = findViewById(R.id.textView24);
        tv_ngaySX = findViewById(R.id.textView25);
        tv_trangThai = findViewById(R.id.textView26);
        tv_mieuTa = findViewById(R.id.textView27);
        tv_binhLuan = findViewById(R.id.textView28);
        view = findViewById(R.id.tv_end);

        btn_themGioHang = findViewById(R.id.button2);
        btn_lienHe = findViewById(R.id.button3);
    }

    public void getInfoChiTietSanPham() {

        ModelSanPham modelSanPham = (ModelSanPham) getIntent().getSerializableExtra("viewChiTietSanPham");

        id_sp = modelSanPham.getId_sanPham_();
        img_chiTietSP = modelSanPham.getImgSP_();
        tv_ten_ = modelSanPham.getTenSp_();
        tv_gia_ = Integer.parseInt(modelSanPham.getGiaTienSP_());
        tv_hang_ = modelSanPham.getNhaSX_();
        tv_ngaySX_ = modelSanPham.getNgay_san_xuat();
        tv_trangThai_ = modelSanPham.getTrang_thai_hang();
        tv_mieuTa_ = modelSanPham.getMieuTaSP_();
        tv_binhLuan_ = modelSanPham.getBinhLuanSP_();

        tv_ten.setText(tv_ten_);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tv_gia.setText(decimalFormat.format(tv_gia_) + "\t VND");
        tv_hang.setText(tv_hang_);
        tv_ngaySX.setText(tv_ngaySX_);
        tv_trangThai.setText(tv_trangThai_);
        tv_mieuTa.setText(tv_mieuTa_);
        tv_binhLuan.setText(tv_binhLuan_);
        Picasso.get().load(img_chiTietSP).
                placeholder(R.drawable.noimageavailablesvg).
                error(R.drawable.error).
                into(img_chiTiet);
    }

    public void checkTrangThai() {
        if (tv_trangThai.getText().toString().equals("Hết hàng")) {
            btn_lienHe.setVisibility(View.VISIBLE);
            // btn_lienHe.setVisibility(View.INVISIBLE);
        } else {
            btn_lienHe.setVisibility(View.INVISIBLE);
            btn_themGioHang.setVisibility(View.VISIBLE);
        }
    }

    public void btn_themVaoGioHang() {

        btn_themGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationUlti.transalateAnimation(img_start, btn_themGioHang, view, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        img_start.setImageResource(R.drawable.icons8product68);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        setCountCart(FromBanGiay.getmCountProduct() + 1);
                        if (FromBanGiay.modelGioHangs.size() > 0) {
                            int soLuong_ = 1;
                            boolean ex = false;
                            for (int i = 0; i < FromBanGiay.modelGioHangs.size(); i++) {
                                if (FromBanGiay.modelGioHangs.get(i).getId_sanPham_gioHang() == id_sp) {
                                    FromBanGiay.modelGioHangs.get(i).setSo_luong_gioHang(FromBanGiay.modelGioHangs.get(i).getSo_luong_gioHang() + soLuong_);
                                    FromBanGiay.modelGioHangs.get(i).setGia_tien_gioHang(String.valueOf(FromBanGiay.modelGioHangs.get(i).getSo_luong_gioHang() * tv_gia_));
                                    ex = true;
                                }
                            }
                            if (ex == false) {
                                int soluong = 1;
                                long giaMoi = soluong * tv_gia_;
                                FromBanGiay.modelGioHangs.add(
                                        new ModelGioHang(
                                                id_sp,
                                                img_chiTietSP,
                                                tv_ten_,
                                                giaMoi + "",
                                                soluong)
                                );
                            }
                        } else {
                            int soluong = 1;
                            long giaMoi = soluong * tv_gia_;
                            FromBanGiay.modelGioHangs.add(
                                    new ModelGioHang(
                                            id_sp,
                                            img_chiTietSP,
                                            tv_ten_,
                                            giaMoi + "",
                                            soluong)
                            );
                        }

                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(FromBanGiay.modelGioHangs);
                        editor.putString("tasklist", json);
                        editor.apply();
                        Toast.makeText(MainActivityViewChiTietSanPham.this, "Sản phẩm đã được thêm vào giỏ hàng", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            }
        });
    }

    public void btnLienHe() {
        btn_lienHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivityViewChiTietSanPham.this, R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_view_lien_he, (LinearLayout) findViewById(R.id.bottomSheetContainer));
                bottomSheetView.findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();

                bottomSheetView.findViewById(R.id.textView29).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Phone", Toast.LENGTH_LONG).show();
                        tv_numberPhone = bottomSheetView.findViewById(R.id.textView29);
                        String phoneNo = tv_numberPhone.getText().toString();
                        if (!TextUtils.isEmpty(phoneNo)) {
                            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNo)));
                        }
                    }
                });
                bottomSheetView.findViewById(R.id.tv_email).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Email", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MainActivityViewChiTietSanPham.this, Gmail.class));
                    }
                });
            }
        });
    }

    public void setCountCart(int count) {
        FromBanGiay.sum = count;
        AHNotification notification = new AHNotification.Builder()
                .setText(String.valueOf(count))
                .setBackgroundColor(ContextCompat.getColor(MainActivityViewChiTietSanPham.this, R.color.color_notification_back))
                .setTextColor(ContextCompat.getColor(MainActivityViewChiTietSanPham.this, R.color.color_notification_text))
                .build();
        FromBanGiay.ahBottomNavigation.setNotification(notification, 3);
    }
}