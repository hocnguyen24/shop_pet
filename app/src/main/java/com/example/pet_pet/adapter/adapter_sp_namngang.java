package com.example.pet_pet.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_pet.Dao.SanPhamDao;
import com.example.pet_pet.Interface.OnItemClick;
import com.example.pet_pet.Model.SanPham;
import com.example.pet_pet.databinding.ItemListSpTrangChuBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class adapter_sp_namngang extends RecyclerView.Adapter<adapter_sp_namngang.ViewHol> {

    private ArrayList<SanPham> list;
    private Context context;
    SanPhamDao spd;
    public adapter_sp_namngang(ArrayList<SanPham> list, Context context){
        this.list= list;
        this.context= context;
        spd= new SanPhamDao(context);
    }

    private OnItemClick mListener;

    public void setOnItemClick(OnItemClick listener) {
        mListener = listener;
    }
    public SanPham getViTriSp(int position) {
        if (position >= 0 && position < list.size()) {
            return list.get(position);
        }
        return null;
    }
    @NonNull
    @Override
    public ViewHol onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListSpTrangChuBinding binding= ItemListSpTrangChuBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new ViewHol(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHol holder, int position) {
        Picasso.get().load(list.get(position).getAnhSanPham()).into(holder.binding.imgAnhListSp);
        holder.binding.txtgia.setText(String.valueOf(list.get(position).getGia()));
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

    public  class ViewHol extends RecyclerView.ViewHolder {
        ItemListSpTrangChuBinding binding;
        public ViewHol(@NonNull ItemListSpTrangChuBinding binding) {
            super(binding.getRoot());
            this.binding= binding;
        }
    }
}
