package com.example.pet_pet.fragment;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_pet.Dao.LoaiSanPhamDao;
import com.example.pet_pet.Model.LoaiSanPham;
import com.example.pet_pet.R;
import com.example.pet_pet.adapter.adapter_loai_san_pham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class frgQuanLyLoaiSanPham extends Fragment {
    RecyclerView rcvloaisanpham;
    FloatingActionButton fltadd;
    private ArrayList<LoaiSanPham> list = new ArrayList<>();
    LoaiSanPhamDao dao;
    adapter_loai_san_pham adapter;


    public frgQuanLyLoaiSanPham() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frg_quan_ly_loai_san_pham, container, false);
        rcvloaisanpham = view.findViewById(R.id.rcvloaisp);
        fltadd = view.findViewById(R.id.fltadd);
        dao = new LoaiSanPhamDao(this.getContext());
        list =  dao.getalltheloai();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvloaisanpham.setLayoutManager(layoutManager);
        adapter = new adapter_loai_san_pham( list,getContext());
        rcvloaisanpham.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogthem();
            }
        });
        return view;
    }

    public void dialogthem() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_themloaisanpham,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        // Thiết lập background cho AlertDialog
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.nen_dialog_doan);
        dialog.show();
        TextInputEditText edtenloaisp=view.findViewById(R.id.edten_loai_san_pham);
        Button btnhuythemlsp=view.findViewById(R.id.btnhuy_themlsp);
        Button btnthemlsp=view.findViewById(R.id.btnthemlsp);
        btnthemlsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai=edtenloaisp.getText().toString();
                if(tenloai.isEmpty()){
                    if(tenloai.equals("")){
                        edtenloaisp.setError("Vui lòng nhập tên loại sản phẩm");
                    }else{
                        edtenloaisp.setError(null);
                    }
                }else{
                    LoaiSanPham loaiSanPham;
                    if(dao.insert(tenloai)){
                        loadData();
                        Toast.makeText(getContext(), "Thêm loại sách thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(getContext(), "Thêm loại sách không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnhuythemlsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    private void loadData(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvloaisanpham.setLayoutManager(layoutManager);
        ArrayList<LoaiSanPham> list =dao.getalltheloai();
        adapter = new adapter_loai_san_pham(list,getContext());
        rcvloaisanpham.setAdapter(adapter);
    }
}