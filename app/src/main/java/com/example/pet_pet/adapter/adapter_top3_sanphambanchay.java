package com.example.pet_pet.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_pet.Dao.ThongKeDao;
import com.example.pet_pet.Model.DonHangChiTiet;
import com.example.pet_pet.databinding.ItemTop3SanphamBanchayBinding;

import java.util.ArrayList;


public class adapter_top3_sanphambanchay extends RecyclerView.Adapter<adapter_top3_sanphambanchay.Viewh> {

private ArrayList<DonHangChiTiet>list;
private Context context;
private ThongKeDao dao;

    public adapter_top3_sanphambanchay(ArrayList<DonHangChiTiet> list, Context context) {
        this.list = list;
        this.context = context;
        dao=new ThongKeDao(context);
    }

    @NonNull
    @Override
    public Viewh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTop3SanphamBanchayBinding binding=ItemTop3SanphamBanchayBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new Viewh(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewh holder, int position) {
        holder.biding.txtmaSanPham.setText("MÃ sản phẩm: " + String.valueOf(list.get(position).getMaSanPham()));
        holder.biding.txttenLoaiSanPham.setText("Tên sản phẩm: " + list.get(position).getTenSanPham());
        holder.biding.txtsoluongban.setText("Số lượng: "+String.valueOf(list.get(position).getSoLuong()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewh extends RecyclerView.ViewHolder {
        ItemTop3SanphamBanchayBinding biding;
            public Viewh(@NonNull ItemTop3SanphamBanchayBinding biding) {
                super(biding.getRoot());
                this.biding=biding;
            }
        }
}
