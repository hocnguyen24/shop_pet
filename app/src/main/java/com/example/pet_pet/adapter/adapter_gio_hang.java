package com.example.pet_pet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_pet.Dao.GioHangDao;
import com.example.pet_pet.Model.GioHang;
import com.example.pet_pet.databinding.ItemGioHangBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class adapter_gio_hang extends RecyclerView.Adapter<adapter_gio_hang.ViewHolder> {

    private ArrayList<GioHang> list;
    Context context;
    GioHangDao dao;

    private TotalPriceListener listener;


    public adapter_gio_hang(Context context,  ArrayList<GioHang> list) {
        this.context = context;
        this.list = list;

        dao = new GioHangDao(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGioHangBinding binding = ItemGioHangBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GioHang gioHang = list.get(position);
        // Hiển thị thông tin sản phẩm
        holder.binding.txtgia.setText(String.valueOf(gioHang.getSoLuongMua() * gioHang.getGiaSanPham()));
        holder.binding.txtsoluong.setText(String.valueOf(gioHang.getSoLuongMua()));
        holder.binding.txttensp.setText(gioHang.getTenSanPham());
        Picasso.get().load(list.get(position).getAnhSanPham()).into(holder.binding.imganhsp);
        holder.binding.chkChonSanPham.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                gioHang.setSelected(b);
                holder.binding.chkChonSanPham.setChecked(b);

                notifyDataSetChanged();
                updateTotalPrice();

            }
        });


        holder.binding.btncong.setOnClickListener(view -> {
            if (gioHang.getSoLuongMua() < gioHang.getSoLuong()) {
                gioHang.setSoLuongMua(gioHang.getSoLuongMua() + 1);
                dao.updateGioHang(gioHang);
                notifyDataSetChanged();
                updateTotalPrice();
            } else {
                Toast.makeText(context, "Không thể mua thêm, số lượng trong kho đã đạt tối đa", Toast.LENGTH_SHORT).show();
            }
        });
        holder.binding.btntru.setOnClickListener(view -> {
            if (gioHang.getSoLuongMua() > 1) {
                gioHang.setSoLuongMua(gioHang.getSoLuongMua() - 1);

                dao.updateGioHang(gioHang);
                notifyDataSetChanged();
                updateTotalPrice();
            } else {

                removeItem(gioHang);

            }
        });

    }


    public void updateCartList(ArrayList<GioHang> updatedList) {
        list.clear();
        list.addAll(updatedList);
//        this.list = updatedList;
        notifyDataSetChanged();

    }

    private void removeItem(GioHang gioHang) {
        if (dao.deleteGioHang(gioHang)) {
            list.remove(gioHang);

            notifyDataSetChanged();
            updateTotalPrice();
        } else {
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    public void removeItem2(int pos) {
        GioHang gioHang1=list.get(pos);
        if (dao.deleteGioHang(gioHang1)) {
            list.remove(gioHang1);

            notifyDataSetChanged();
            updateTotalPrice();
        } else {
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
    }


    private void updateTotalPrice() {
        if (listener != null) {
            int totalAmount = 0;

            for (GioHang gioHang : list) {
                if (gioHang.isSelected()) {
                    totalAmount += gioHang.getSoLuongMua() * gioHang.getGiaSanPham();
                }
            }
            listener.onTotalPriceUpdated(totalAmount);
        }
    }

    public void setTotalPriceListener(TotalPriceListener listener) {
        this.listener = listener;
    }

    public Context getContext() {
        return context;
    }


    public interface TotalPriceListener {
        void onTotalPriceUpdated(int totalAmount);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemGioHangBinding binding;

        public ViewHolder(ItemGioHangBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }
}
