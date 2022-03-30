package com.example.duanmotnhom12.FragmentFromBanGiay;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.duanmotnhom12.Adapter.ViewTrangChuAdapter;
import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.FromBanGiay.FromBanGiay;
import com.example.duanmotnhom12.Model.ModelTrangChu;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.TranslateAnimation_nav;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class TrangChuFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private ViewTrangChuAdapter viewTrangChuAdapter;
    ArrayList<ModelTrangChu> modelTrangChus;

    private String mParam1;
    private String mParam2;

    public TrangChuFragment() {
        // Required empty public constructor
    }

    public static TrangChuFragment newInstance(String param1, String param2) {
        TrangChuFragment fragment = new TrangChuFragment();
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
        return inflater.inflate(R.layout.fragment_trang_chu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rcv_viewTinNoiBat);
        modelTrangChus = new ArrayList<>();
        viewTrangChuAdapter = new ViewTrangChuAdapter(getContext(), modelTrangChus, new ViewTrangChuAdapter.isClickViewImg() {
            @Override
            public void onclickViewImg(ImageView imageView, ModelTrangChu modelTrangChu) {
                LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = layoutInflater.inflate(R.layout.dialog_view_img, null);
                ImageView img = view.findViewById(R.id.imageView3);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(view);
                Picasso.get().load(modelTrangChu.getImg_anhChinh())
                        .placeholder(R.drawable.noimageavailablesvg)
                        .into(img);
                builder.show();
                builder.setCancelable(false);
            }
        });

        RecyclerView.LayoutManager  layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setOnTouchListener(new TranslateAnimation_nav(getContext(), FromBanGiay.ahBottomNavigation));

        modelTrangChus.add(new ModelTrangChu(R.drawable.ic_baseline_camera_alt_24, URLss.URLquangCao_1,"#fbHuuThang"));
        modelTrangChus.add(new ModelTrangChu(R.drawable.ic_baseline_camera_alt_24, URLss.URLquangCao_2,"#emailHuongLy"));
        modelTrangChus.add(new ModelTrangChu(R.drawable.ic_baseline_camera_alt_24, URLss.URLquangCao_3,"#fbNgocDung"));
        modelTrangChus.add(new ModelTrangChu(R.drawable.ic_baseline_camera_alt_24, URLss.URLquangCao_4,"#emailLongVu"));
        modelTrangChus.add(new ModelTrangChu(R.drawable.ic_baseline_camera_alt_24, URLss.URLquangCao_5,"#fbDangHuynh"));
        modelTrangChus.add(new ModelTrangChu(R.drawable.ic_baseline_camera_alt_24, URLss.URLquangCao_6,"#fbQuynhNhu"));
        modelTrangChus.add(new ModelTrangChu(R.drawable.ic_baseline_camera_alt_24, URLss.URLquangCao_7,"#fbDoManh"));
        modelTrangChus.add(new ModelTrangChu(R.drawable.ic_baseline_camera_alt_24, URLss.URLquangCao_8,"#emailNgocNguyen"));
        modelTrangChus.add(new ModelTrangChu(R.drawable.ic_baseline_camera_alt_24, URLss.URLquangCao_9,"#emailNamQuyen"));
        modelTrangChus.add(new ModelTrangChu(R.drawable.ic_baseline_camera_alt_24, URLss.URLquangCao_10,"#fbManhHa"));

        recyclerView.setAdapter(viewTrangChuAdapter);
    }

}