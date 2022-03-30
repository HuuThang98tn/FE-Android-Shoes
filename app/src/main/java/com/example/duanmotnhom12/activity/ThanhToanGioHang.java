package com.example.duanmotnhom12.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.JSON.JsonPhuongXa;
import com.example.duanmotnhom12.JSON.JsonQuanHuyen;
import com.example.duanmotnhom12.JSON.JsonTinh;
import com.example.duanmotnhom12.Model.ModelDangKyTaiKhoan;
import com.example.duanmotnhom12.Model.ModelPhuongXa;
import com.example.duanmotnhom12.Model.ModelQuanHuyen;
import com.example.duanmotnhom12.Model.ModelTinhThanh;
import com.example.duanmotnhom12.R;

import java.util.HashMap;
import java.util.Map;

public class ThanhToanGioHang extends AppCompatActivity {

    Spinner spinner_tinhThanh, spinner_quanHuyen, spinner_phuongXa;
    Button btn_ThanhToan;
    EditText ed_hoTen, ed_soDienThoai, ed_diaChi;
    String tenTinh;
    String tenHuyen;
    String phuongxa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan_gio_hang);


        Toolbar tb_dangKy = findViewById(R.id.hoaDon);
        setSupportActionBar(tb_dangKy);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        btn_ThanhToan = findViewById(R.id.button5);
        ed_hoTen = findViewById(R.id.editTextTextPersonName);
        ed_soDienThoai = findViewById(R.id.editTextTextPersonName2);
        ed_diaChi = findViewById(R.id.editTextTextPassword);

        callApiTinhThanh();
        thongTinThanhToan();
    }

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

    public void callApiTinhThanh() {
        spinner_tinhThanh = findViewById(R.id.spinner);
        JsonTinh jsonTinh = new JsonTinh();
        jsonTinh.getJsonArray(URLss.URL_TINH, getApplicationContext(), spinner_tinhThanh);
        spinner_tinhThanh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final ModelTinhThanh modelTinhThanh = JsonTinh.modelTinhThanhs.get(position);
                String srcTenTinh = modelTinhThanh.getId() + "";
                tenTinh = modelTinhThanh.getTitle() + "";
                if (JsonQuanHuyen.modelQuanHuyens != null) {
                    JsonQuanHuyen.modelQuanHuyens.clear();
                    spinner_quanHuyen = findViewById(R.id.spinner2);
                    JsonQuanHuyen jsonQuanHuyen = new JsonQuanHuyen();
                    jsonQuanHuyen.getJsonArrayQuanHuyen(URLss.URL_HUYEN + srcTenTinh + "/district", getApplicationContext(), spinner_quanHuyen);
                }

                spinner_quanHuyen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        final ModelQuanHuyen modelQuanHuyen = JsonQuanHuyen.modelQuanHuyens.get(position);
                        String srcTenHuyen = modelQuanHuyen.getId_huyen() + "";
                        tenHuyen = modelQuanHuyen.getTitle() + "";
                        if (JsonPhuongXa.modelPhuongXas != null) {
                            JsonPhuongXa.modelPhuongXas.clear();
                            spinner_phuongXa = findViewById(R.id.spinner3);
                            JsonPhuongXa jsonPhuongXa = new JsonPhuongXa();
                            jsonPhuongXa.getJsonArrayPhuongXa(URLss.URL_XA + srcTenHuyen + "/ward", getApplicationContext(), spinner_phuongXa);
                        }
                        spinner_phuongXa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                ModelPhuongXa modelPhuongXa = JsonPhuongXa.modelPhuongXas.get(position);
                                phuongxa = modelPhuongXa.getTitle();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void thongTinThanhToan() {
        btn_ThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hoTen = ed_hoTen.getText().toString();
                String soDienThoai = ed_soDienThoai.getText().toString();
                String diaChi = ed_diaChi.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLss.URL_THENHOADON, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Nullable
                    @org.jetbrains.annotations.Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        ModelDangKyTaiKhoan modelDangKyTaiKhoan = new ModelDangKyTaiKhoan();
                        params.put("hoTenNn_Sp_", hoTen);
                        params.put("sdt_hd_", soDienThoai);
                        params.put("tinh_thanh_pho", tenTinh);
                        params.put("quan_huyen", tenHuyen);
                        params.put("phuong_xa", phuongxa);
                        params.put("dia_chi_cuThe", diaChi);
                        params.put("id_user_", modelDangKyTaiKhoan.getId_user() + "");

                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(ThanhToanGioHang.this);
                requestQueue.add(stringRequest);

                Toast.makeText(getApplicationContext(), "Đặt hàng thành công", Toast.LENGTH_LONG).show();
            }
        });
    }
}