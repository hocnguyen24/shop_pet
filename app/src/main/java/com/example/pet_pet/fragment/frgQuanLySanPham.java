package com.example.pet_pet.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pet_pet.Dao.LoaiSanPhamDao;
import com.example.pet_pet.Dao.SanPhamDao;
import com.example.pet_pet.Model.LoaiSanPham;
import com.example.pet_pet.Model.SanPham;
import com.example.pet_pet.R;
import com.example.pet_pet.adapter.adapter_san_pham;
import com.example.pet_pet.databinding.DialogThemSanPhamBinding;
import com.example.pet_pet.databinding.FragmentFrgQuanLySanPhamBinding;

import java.util.ArrayList;
import java.util.HashMap;




public class frgQuanLySanPham extends Fragment {

    View vieww;
    FragmentFrgQuanLySanPhamBinding biding;
    SanPhamDao dao;
    ArrayList<SanPham> list;
    adapter_san_pham adapter;

    public frgQuanLySanPham() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        biding = FragmentFrgQuanLySanPhamBinding.inflate(inflater, container, false);
        vieww = biding.getRoot();
        dao = new SanPhamDao(getContext());
        list = dao.getsanphamall();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        biding.rcvsanpham.setLayoutManager(layoutManager);
        adapter = new adapter_san_pham(list, getContext(), getDsLoaiSanPham());
        biding.rcvsanpham.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        // Inflate the layout for this fragment
        biding.fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogthem();
            }
        });
        return vieww;
    }

    private ArrayList<HashMap<String, Object>> getDsLoaiSanPham() {
        LoaiSanPhamDao loaiDao = new LoaiSanPhamDao(getContext());
        ArrayList<LoaiSanPham> list1 = loaiDao.getalltheloai();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (LoaiSanPham ls : list1) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maloaisanpham", ls.getMaloaisp());
            hs.put("tenloaisanpham", ls.getTenloaisp());
            listHM.add(hs);
        }
        return listHM;
    }

    public void dialogthem() {
        LayoutInflater layoutInflater = getLayoutInflater();
        DialogThemSanPhamBinding themspbiding = DialogThemSanPhamBinding.inflate(layoutInflater);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(themspbiding.getRoot());
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.nen_dialog_doan);
        dialog.show();
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDsLoaiSanPham(),
                android.R.layout.simple_list_item_1,
                new String[]{"maloaisanpham"},
                new int[]{android.R.id.text1}
        );
        themspbiding.spnmaLoaiSanPham.setAdapter(simpleAdapter);
        themspbiding.btnthemlsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensp = themspbiding.edtenSanPham.getText().toString();
                String giasp = themspbiding.edgiaSanPham.getText().toString();
                String mota = themspbiding.edmoTa.getText().toString();
                String soluong=themspbiding.edtsoluong.getText().toString();
                String anhsanpham = themspbiding.edtInAnhSanPham.getText().toString();
                HashMap<String, Object> hs = (HashMap<String, Object>) themspbiding.spnmaLoaiSanPham.getSelectedItem();
                int maloaisp = (int) hs.get("maloaisanpham");
                if (tensp.isEmpty() || giasp.isEmpty() || mota.isEmpty() || anhsanpham.isEmpty()||soluong.isEmpty()) {
                    if (tensp.equals("")) {
                        themspbiding.edtenSanPham.setError("Vui lòng không để trống tên sản phẩm");
                    } else {
                        themspbiding.edtenSanPham.setError(null);
                    }
                    if (giasp.equals("")) {
                        themspbiding.edgiaSanPham.setError("Vui lòng không để trống giá sản phẩm");
                    } else {
                        themspbiding.edgiaSanPham.setError(null);
                    }
                    if (mota.equals("")) {
                        themspbiding.edmoTa.setError("Vui lòng không để trống mô tả");
                    } else {
                        themspbiding.edmoTa.setError(null);
                    }
                    if (anhsanpham.equals("")) {
                        themspbiding.tinLInAnhSanPham.setError("Vui lòng không để trống mô tả");
                    } else {
                        themspbiding.tinLInAnhSanPham.setError(null);
                    }
                    if (anhsanpham.equals("")) {
                        themspbiding.edtInAnhSanPham.setError("Vui lòng không để trống tên sản phẩm");
                    } else {
                        themspbiding.edtInAnhSanPham.setError(null);
                    }
                    if (anhsanpham.equals("")) {
                        themspbiding.edtsoluong.setError("Vui lòng không để trống tên sản phẩm");
                    } else {
                        themspbiding.edtsoluong.setError(null);
                    }
                } else {
                    try {
                        int checkgia = Integer.parseInt(giasp);
                        int soluongcheck=Integer.parseInt(soluong);
                        if (checkgia <= 0||soluongcheck<0) {
                            themspbiding.edgiaSanPham.setError("Giá sản phẩm phải lớn hơn 0");
                            themspbiding.edtsoluong.setError("Số lượng sản phẩm phải lớn hơn 0");
                        } else {
                            themspbiding.edgiaSanPham.setError(null);
                            boolean check = dao.insert(tensp, checkgia, maloaisp, mota, anhsanpham,soluongcheck);
                            if (check) {
                                loadData();
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Thêm thành công sản phẩm", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(getContext(), "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (NumberFormatException e) {
                        themspbiding.edgiaSanPham.setError("Giá sản phẩm phải là số");
                        themspbiding.edtsoluong.setError("So lượng sản phẩm phải là số");
                    }
                }
            }
        });
        themspbiding.btnhuyThemlsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    private void loadData() {
        list = dao.getsanphamall();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        biding.rcvsanpham.setLayoutManager(layoutManager);
        adapter_san_pham adapter = new adapter_san_pham(list, getContext(), getDsLoaiSanPham());
        biding.rcvsanpham.setAdapter(adapter);
    }
}