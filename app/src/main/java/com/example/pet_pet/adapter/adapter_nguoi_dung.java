package com.example.pet_pet.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_pet.Dao.NguoiDungDao;
import com.example.pet_pet.Model.NguoiDung;
import com.example.pet_pet.R;
import com.example.pet_pet.databinding.DialogXoaNguoiDungBinding;
import com.example.pet_pet.databinding.ItemQlNdBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class adapter_nguoi_dung extends RecyclerView.Adapter<adapter_nguoi_dung.ViewHolder> {
        private ArrayList<NguoiDung> list;
        private Context context;
        private NguoiDungDao dao;

    public adapter_nguoi_dung(ArrayList<NguoiDung> list, Context context) {
        this.list = list;
        this.context = context;
        dao=new NguoiDungDao(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQlNdBinding binding = ItemQlNdBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.txtmaNguoiDung.setText("Mã: "+String.valueOf(list.get(position).getMaTaiKhoan()));
        holder.binding.txtTenNguoiDung.setText("Tên: " + String.valueOf(list.get(position).getHoTen()));
        holder.binding.txtSoDienThoai.setText("Số ĐT: "+list.get(position).getSoDienThoai());
        holder.binding.txtDiaChiEmail.setText("Email: "+list.get(position).getEmail());
        holder.binding.txtDiaChi.setText("Địa chỉ: "+ list.get(position).getDiaChi());
        holder.binding.txtCoins.setText("Coins: "+String.valueOf(list.get(position).getSoTien()));
        Picasso.get().load(list.get(position).getAnhnguoidung()).into(holder.binding.imgAnhQlNguoiDung);
        NguoiDung nguoiDung = list.get(position);
        holder.binding.btnxoaND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();

                DialogXoaNguoiDungBinding dialogXoaNguoiDungBinding = DialogXoaNguoiDungBinding.inflate(inflater);
                builder.setView(dialogXoaNguoiDungBinding.getRoot());

                Dialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.nen_dialog_doan);
                dialog.show();
                dialogXoaNguoiDungBinding.btnConfilmXoaNguoiDung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (dao.xoaNguoiDung(nguoiDung)){
                            list.clear();
                            list.addAll(dao.getAllNguoiDung());
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialogXoaNguoiDungBinding.btnOutXoaNguoiDung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemQlNdBinding binding;
        public ViewHolder(ItemQlNdBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
