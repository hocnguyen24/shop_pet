package com.example.pet_pet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_pet.Dao.DanhGiaDao;
import com.example.pet_pet.Model.DanhGia;
import com.example.pet_pet.databinding.ItemRcvDanhGiaBinding;

import java.util.ArrayList;



public class adapter_danh_gia extends RecyclerView.Adapter<adapter_danh_gia.ViewHolder> {
private ArrayList<DanhGia> list;
private Context context;
private DanhGiaDao dao;

    public adapter_danh_gia(ArrayList<DanhGia> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new DanhGiaDao(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRcvDanhGiaBinding binding = ItemRcvDanhGiaBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.binding.txtTenNguoiDung.setText(list.get(position).getTenTaiKhoan());
            holder.binding.txtDanhGia.setText(list.get(position).getDanhGia());
            holder.binding.txtNhanXet.setText(list.get(position).getNhanXet());
            holder.binding.txtNgayDanhGia.setText(list.get(position).getNgayDanhGia());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    ItemRcvDanhGiaBinding binding;
        public ViewHolder(ItemRcvDanhGiaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
