package com.example.pet_pet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_pet.Dao.DanhGiaDao;
import com.example.pet_pet.Dao.DonHangChiTietDao;
import com.example.pet_pet.Interface.OnAddDanhGia;
import com.example.pet_pet.Model.DonHangChiTiet;
import com.example.pet_pet.databinding.ItemLsDonHangChiTietBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class adapter_ls_don_hang_chi_tiet  extends RecyclerView.Adapter<adapter_ls_don_hang_chi_tiet.ViewHolder>{
    private ArrayList<DonHangChiTiet> list;
    private Context context;
    private DonHangChiTietDao dao;
    private DanhGiaDao dao2;
    public adapter_ls_don_hang_chi_tiet(ArrayList<DonHangChiTiet> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new DonHangChiTietDao(context);
        dao2=new DanhGiaDao(context);
    }
    private OnAddDanhGia mAddDanhGia;

    public void setonAddDanhia(OnAddDanhGia listener) {
        mAddDanhGia = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLsDonHangChiTietBinding binding = ItemLsDonHangChiTietBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new adapter_ls_don_hang_chi_tiet.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.txtDonGia.setText("Giá: " + String.valueOf(list.get(position).getDonGia()));
        holder.binding.txtmaChiTietDon.setText("Mã chi tiết đơn: " + String.valueOf(list.get(position).getMaChiTietDonHang()));
        holder.binding.txtMaDonHang.setText("Mã đơn hàng: " + String.valueOf(list.get(position).getMaDonHang()));
        holder.binding.txtMaSanPham.setText("Mã sản phẩm: " + String.valueOf(list.get(position).getMaSanPham()));
        holder.binding.txtThanhTien.setText("Thành tiền: " + String.valueOf(list.get(position).getThanhTien()));
        holder.binding.txtSoLuong.setText("Số lượng: " + String.valueOf(list.get(position).getSoLuong()));
        holder.binding.txttensanpham.setText("Tên sản phẩm: " + list.get(position).getTenSanPham());
        Picasso.get().load(list.get(position).getAnhsanpham()).into(holder.binding.imgAnhsp);
        DonHangChiTiet ct = list.get(position);
        holder.binding.btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAddDanhGia != null) {
                    mAddDanhGia.onAddDanhia(list.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemLsDonHangChiTietBinding binding;

        public ViewHolder(ItemLsDonHangChiTietBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

