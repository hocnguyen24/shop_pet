package com.example.pet_pet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_pet.Dao.LoaiSanPhamDao;
import com.example.pet_pet.Interface.OnButtonLoaiSP;
import com.example.pet_pet.Model.LoaiSanPham;
import com.example.pet_pet.databinding.ItemRcvChonLoaiSanPhamBinding;

import java.util.ArrayList;



public class adapter_chon_loai_san_pham extends RecyclerView.Adapter<adapter_chon_loai_san_pham.ViewHodle> {
    private ArrayList<LoaiSanPham> list;
    private LoaiSanPhamDao dao;
    private Context context;

    public adapter_chon_loai_san_pham(ArrayList<LoaiSanPham> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new LoaiSanPhamDao(context);
    }
    private OnButtonLoaiSP mListener;

    // Phương thức để thiết lập listener
    public void setOnItemClickListener(OnButtonLoaiSP listener) {
        mListener = listener;
    }
    public int getMaLoaiSanPham(int position) {
        if (position > 0 && position < list.size()) {
            return list.get(position).getMaloaisp();
        }
        return -1; // Hoặc một giá trị không hợp lệ nếu vị trí không tồn tại trong danh sách
    }
    @NonNull
    @Override
    public ViewHodle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRcvChonLoaiSanPhamBinding binding = ItemRcvChonLoaiSanPhamBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new ViewHodle(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodle holder, int position) {
        holder.binding.btnChonLoaiSanPham.setText(list.get(position).getTenloaisp());
        holder.binding.btnChonLoaiSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.OnButtonLoaiSP(list.get(holder.getAdapterPosition()));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodle extends RecyclerView.ViewHolder{
        ItemRcvChonLoaiSanPhamBinding binding;
        public ViewHodle(@NonNull ItemRcvChonLoaiSanPhamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
