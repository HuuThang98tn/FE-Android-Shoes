package com.example.duanmotnhom12.FragmentFromBanGiay;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmotnhom12.FromBanGiay.FromBanGiay;
import com.example.duanmotnhom12.R;

import org.jetbrains.annotations.NotNull;

public class NewTaiKhoanFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ImageView img_cd,imageView15;
    TextView textView32, textView33, textView322, textView333;


    private String mParam1;
    private String mParam2;

    public NewTaiKhoanFragment() {
        // Required empty public constructor
    }

    public static NewTaiKhoanFragment newInstance(String param1, String param2) {
        NewTaiKhoanFragment fragment = new NewTaiKhoanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_cai_dat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        anhXa();
        newCaiDatTaiKhoan();
        newDonHangCuaBan();
        newHoiDapThacMac();
        newPhanHoiChatLuong();
        newLichSuMuaHang();
        newAdmin();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.filter_view_admin, menu);
        menu.findItem(R.id.admin_view).setVisible(true);
        return;
    }

    public void anhXa() {
        img_cd = getView().findViewById(R.id.imageView8);
        textView32 = getView().findViewById(R.id.textView32);
        textView33 = getView().findViewById(R.id.textView33);
        textView322 = getView().findViewById(R.id.textView322);
        textView333 = getView().findViewById(R.id.textView333);
        imageView15 = getView().findViewById(R.id.imageView15);
    }
    public void newAdmin() {
        imageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mfmCennter_,
                        new AdminFragment()).commit();
            }
        });
    }

    public void newCaiDatTaiKhoan() {
        img_cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mfmCennter_,
                        new CaiDatTaiKhoanFragment()).commit();
            }
        });
    }

    public void newDonHangCuaBan() {
        textView32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mfmCennter_,
                        new DonHangCuaBanFragment()).commit();
            }
        });
    }

    public void newLichSuMuaHang() {
        textView33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mfmCennter_,
                        new LichSuMuaHangFragment()).commit();
            }
        });
    }

    public void newHoiDapThacMac() {
        textView322.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mfmCennter_,
                        new HoiDapThacMacFragment()).commit();
            }
        });
    }

    public void newPhanHoiChatLuong() {
        textView333.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mfmCennter_,
                        new PhanHoiChatLuongFragment()).commit();
            }
        });
    }
}