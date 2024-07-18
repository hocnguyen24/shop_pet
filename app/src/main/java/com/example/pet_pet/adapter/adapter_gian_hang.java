package com.example.pet_pet.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_pet.Dao.SanPhamDao;
import com.example.pet_pet.Interface.OnAddToCart;
import com.example.pet_pet.Interface.OnItemClick;
import com.example.pet_pet.Model.SanPham;
import com.example.pet_pet.databinding.ItemGianHangBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;


public class adapter_gian_hang extends RecyclerView.Adapter<adapter_gian_hang.ViewHolder> {
    private  Context context;
    private  ArrayList<SanPham> list;
    SanPhamDao dao;
    private ArrayList<Boolean> isClickThemVaoGio;


    public adapter_gian_hang(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;

        dao = new SanPhamDao(context);

    }
    public void setData(ArrayList<SanPham> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }
    public void clearData() {
        list.clear();
        notifyDataSetChanged();
    }

    public void addAlll(ArrayList<SanPham> data) {
        list.addAll(data);
        notifyDataSetChanged();
    }
    // Biến để lưu trữ listener
    private OnItemClick mListener;

    // Phương thức để thiết lập listener
    public void setOnItemClickListener(OnItemClick listener) {
        mListener = listener;
    }

    private OnAddToCart mAddToCartClickListener;



    public void setOnAddToCartClickListener(OnAddToCart listener) {
        mAddToCartClickListener = listener;
    }
    public SanPham getViTriSanPham(int position) {
        if (position >= 0 && position < list.size()) {
            return  list.get(position);
        }
        return null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGianHangBinding binding = ItemGianHangBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham sanPham = list.get(position);
        holder.binding.txttenHat.setText("Tên sp:" + list.get(position).getTensanpham());
        holder.binding.txtgiaHat.setText("Giá sp:" + String.valueOf(list.get(position).getGia()));
        holder.binding.txttrangThaiSanPham.setText("Tồn kho: "+String.valueOf(list.get(position).getSoluong()));
        holder.binding.txtSoluongdaban.setText("Số lượng đã bán: "+ list.get(position).getSoLuotBanRa());
        Picasso.get().load(list.get(position).getAnhSanPham()).into(holder.binding.imgAnhspGianHang);

        if (list.get(position).getSoluong() == 0) {
            holder.binding.btnThemvaogio.setVisibility(View.GONE);
            holder.binding.txtHetHang.setVisibility(View.VISIBLE);
        } else {
            holder.binding.btnThemvaogio.setVisibility(View.VISIBLE);
            holder.binding.txtHetHang.setVisibility(View.GONE);
            holder.binding.btnThemvaogio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mAddToCartClickListener != null) {
                        mAddToCartClickListener.onAddToCartClick(list.get(holder.getAdapterPosition()));
                    }
                }
            });
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemGianHangBinding binding;

        public ViewHolder(ItemGianHangBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
