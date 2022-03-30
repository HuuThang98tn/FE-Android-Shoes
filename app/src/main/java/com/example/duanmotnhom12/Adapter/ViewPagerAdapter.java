package com.example.duanmotnhom12.Adapter;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.duanmotnhom12.FragmentFromBanGiay.GioHangFragment;
import com.example.duanmotnhom12.FragmentFromBanGiay.ListSanPhamFragment;
import com.example.duanmotnhom12.FragmentFromBanGiay.TaiKhoanFragment;
import com.example.duanmotnhom12.FragmentFromBanGiay.TrangChuFragment;
import com.example.duanmotnhom12.FragmentFromBanGiay.UaThichFragment;
import com.example.duanmotnhom12.FromBanGiay.FromBanGiay;

import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TrangChuFragment();
            case 1:
                return new ListSanPhamFragment();
            case 2:
                return new UaThichFragment();
            case 3:
                return new GioHangFragment();
            case 4:
                return new TaiKhoanFragment();
            default:
                return new TrangChuFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
