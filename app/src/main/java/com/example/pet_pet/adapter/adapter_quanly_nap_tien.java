package com.example.pet_pet.adapter;

import android.app.Activity;
import android.app.AlertDialog;
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
import com.example.pet_pet.databinding.DialogSuaNapTienBinding;
import com.example.pet_pet.databinding.ItemQuanlynaptienBinding;

import java.util.ArrayList;



public class adapter_quanly_nap_tien extends RecyclerView.Adapter<adapter_quanly_nap_tien.ViewH> {
    private ArrayList<NguoiDung> list;
    private Context context;
    NguoiDungDao dao;

    public adapter_quanly_nap_tien(ArrayList<NguoiDung> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new NguoiDungDao(context);
    }

    @NonNull
    @Override
    public ViewH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQuanlynaptienBinding biding = ItemQuanlynaptienBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewH(biding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewH holder, int position) {
        holder.binding.txtmaNguoiDung.setText("Mã tài khoản: " + String.valueOf(list.get(position).getMaTaiKhoan()));
        holder.binding.txttenNguoiDung.setText("Tên người dùng: " + list.get(position).getHoTen());
        holder.binding.txtsotien.setText("Số tiền: " + String.valueOf(list.get(position).getSoTien()));
        NguoiDung nd = list.get(position);
        holder.binding.btnchinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
                DialogSuaNapTienBinding suabiding = DialogSuaNapTienBinding.inflate(layoutInflater);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(suabiding.getRoot());
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawableResource(com.example.pet_pet.R.drawable.nen_dialog_doan);
                dialog.show();
                suabiding.txttenNguoiDung.setText("Tên người dùng: " + nd.getHoTen());
                suabiding.txtmanguoiDung.setText(String.valueOf("Mã người dùng: " + nd.getMaTaiKhoan()));
                suabiding.edsoTien.setText(String.valueOf(  nd.getSoTien()));
                suabiding.btnchinhsua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String manguoidung = suabiding.txtmanguoiDung.getText().toString();
                        String hoten = suabiding.txttenNguoiDung.getText().toString();
                        String sotien = suabiding.edsoTien.getText().toString();
                        if (sotien.isEmpty()) {
                            if (sotien.equals("")) {
                                suabiding.edsoTien.setError("Vui lòng không để trống tên sản phẩm");
                            } else {
                                suabiding.edsoTien.setError(null);
                            }
                        } else {
                            try {
                                int validatesotien = Integer.parseInt(sotien);
                                if (validatesotien < 0) {
                                    suabiding.edsoTien.setError("Số tiền không được nhỏ hơn 0");
                                } else {
                                    suabiding.edsoTien.setError(null);
                                    boolean check = dao.update(nd.getMaTaiKhoan(), hoten, validatesotien);
                                    if (check) {
                                        list.clear();
                                        list = dao.getAllNguoiDung();
                                        notifyDataSetChanged();
                                        Toast.makeText(context, "Cập nhật thành công ", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    } else {
                                        Toast.makeText(context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } catch (NumberFormatException e) {
                                suabiding.edsoTien.setError("Số tiền phải là số");
                            }
                        }
                    }
                });
                suabiding.btnhuy.setOnClickListener(new View.OnClickListener() {
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

    public class ViewH extends RecyclerView.ViewHolder {
        ItemQuanlynaptienBinding binding;

        public ViewH(@NonNull ItemQuanlynaptienBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
