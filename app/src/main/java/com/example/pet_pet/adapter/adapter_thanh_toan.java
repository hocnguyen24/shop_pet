package com.example.pet_pet.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_pet.Dao.DanhGiaDao;
import com.example.pet_pet.Dao.DonHangChiTietDao;
import com.example.pet_pet.Model.DonHangChiTiet;
import com.example.pet_pet.databinding.ItemConfilmThanhToanBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class adapter_thanh_toan extends RecyclerView.Adapter<adapter_thanh_toan.ViewHolder> {
    private ArrayList<DonHangChiTiet> list;
    private Context context;
    private DonHangChiTietDao dao;
    private DanhGiaDao dao2;

    public adapter_thanh_toan(ArrayList<DonHangChiTiet> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new DonHangChiTietDao(context);
        dao2=new DanhGiaDao(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemConfilmThanhToanBinding binding = ItemConfilmThanhToanBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.txtTenSanPham.setText("Tên sản phẩm: " + list.get(position).getTenSanPham());
        holder.binding.txtMaSanPham.setText("Mã sản phẩm: " + String.valueOf(list.get(position).getMaSanPham()));
        holder.binding.txtMaDonHang.setText("Mã đơn hàng: " + String.valueOf(list.get(position).getMaDonHang()));
        holder.binding.txtSoLuong.setText("Số lượng: " + String.valueOf(list.get(position).getSoLuong()));
        holder.binding.txtDonGia.setText("Giá: " + String.valueOf(list.get(position).getDonGia()));
        holder.binding.txtThanhTien.setText("Thành tiền: " + String.valueOf(list.get(position).getThanhTien()));
        Picasso.get().load(list.get(position).getAnhsanpham()).into(holder.binding.imgAnhSp);
        DonHangChiTiet ct=list.get(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemConfilmThanhToanBinding binding;

        public ViewHolder(ItemConfilmThanhToanBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
