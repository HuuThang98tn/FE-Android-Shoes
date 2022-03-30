package com.example.duanmotnhom12.FragmentFromBanGiay;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmotnhom12.FromBanGiay.FromBanGiay;
import com.example.duanmotnhom12.R;

import org.jetbrains.annotations.NotNull;


public class LichSuMuaHangFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public LichSuMuaHangFragment() {
        // Required empty public constructor
    }


    public static LichSuMuaHangFragment newInstance(String param1, String param2) {
        LichSuMuaHangFragment fragment = new LichSuMuaHangFragment();
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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lich_su_mua_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar tb_dangKy = view.findViewById(R.id.tb_lichSu);
        ((FromBanGiay) getActivity()).setSupportActionBar(tb_dangKy);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        ((FromBanGiay) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((FromBanGiay) getActivity()).getSupportActionBar().setHomeAsUpIndicator(drawable);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mfmCennter_,
                        new NewTaiKhoanFragment()).commit();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}