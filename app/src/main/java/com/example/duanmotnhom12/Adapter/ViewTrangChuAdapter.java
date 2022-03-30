package com.example.duanmotnhom12.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmotnhom12.Model.ModelTrangChu;
import com.example.duanmotnhom12.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ViewTrangChuAdapter extends RecyclerView.Adapter<ViewTrangChuAdapter.ViewTrangChuAdapterViewHolder> {

    Context context;
    ArrayList<ModelTrangChu> modelTrangChus;
    private isClickViewImg isClickViewImg ;

    public ViewTrangChuAdapter(Context context, ArrayList<ModelTrangChu> modelTrangChus,isClickViewImg viewImg) {
        this.context = context;
        this.modelTrangChus = modelTrangChus;
        this.isClickViewImg = viewImg;
    }

    public interface isClickViewImg {
        void onclickViewImg (ImageView  imageView, ModelTrangChu modelTrangChu);
    }

    @NonNull
    @NotNull
    @Override
    public ViewTrangChuAdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_trang_chu, parent, false);

        return new ViewTrangChuAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewTrangChuAdapterViewHolder holder, int position) {
        ModelTrangChu modelTrangChu = modelTrangChus.get(position);
        if (modelTrangChu == null) {
            return;
        }
        holder.img_mayAnh.setImageResource(modelTrangChu.getImg_camera());

        Picasso.get().load(modelTrangChu.getImg_anhChinh()).
                placeholder(R.drawable.noimageavailablesvg).
                into(holder.img_anhChinh);
        holder.tv_nameFb.setText(modelTrangChu.getNameFb());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClickViewImg.onclickViewImg(holder.img_anhChinh,modelTrangChu);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (modelTrangChus != null) {
            return modelTrangChus.size();
        }
        return 0;
    }

    public class ViewTrangChuAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView img_mayAnh;
        ImageView img_anhChinh;
        TextView tv_nameFb;

        public ViewTrangChuAdapterViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            img_mayAnh = itemView.findViewById(R.id.imageView);
            img_anhChinh = itemView.findViewById(R.id.imageView5);
            tv_nameFb = itemView.findViewById(R.id.tv_noiBat);
        }
    }
}
