package com.example.pet_pet.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pet_pet.Dao.NguoiDungDao;
import com.example.pet_pet.Model.NguoiDung;
import com.example.pet_pet.R;
import com.example.pet_pet.adapter.adapter_nguoi_dung;
import com.example.pet_pet.databinding.FragmentFrgQuanLyNguoiDungBinding;

import java.util.ArrayList;




public class frgQuanLyNguoiDung extends Fragment {


    private FragmentFrgQuanLyNguoiDungBinding binding;
    private View vView;
    private NguoiDungDao nguoiDungDao;
    private adapter_nguoi_dung adapterNguoiDung;
    private ArrayList<NguoiDung> list = new ArrayList<>();

    public frgQuanLyNguoiDung() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFrgQuanLyNguoiDungBinding.inflate(inflater, container, false);
        vView = binding.getRoot();
        nguoiDungDao = new NguoiDungDao(getContext());
        list = nguoiDungDao.getAllNguoiDung();
        RecyclerView rcv = binding.rcvNguoiDung;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        adapterNguoiDung = new adapter_nguoi_dung(list, getContext());
        rcv.setAdapter(adapterNguoiDung);
        // Inflate the layout for this fragment
        binding.flNguoiDung.setOnClickListener(view -> {
            frgAddNguoiDung frgAddNguoiDung = new frgAddNguoiDung();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
            transaction.replace(R.id.frameLayoutMain, frgAddNguoiDung);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            String tenDangNhap = bundle.getString("tenDangNhap");
            String matKhau = bundle.getString("matKhau");
            String hoTen = bundle.getString("hoTen");
            String email = bundle.getString("email");
            String soDienThoai = bundle.getString("soDienThoai");
            String diaChi = bundle.getString("diaChi");
            int tien = bundle.getInt("soTien");
            String loaiTaiKhoan = bundle.getString("loaiTaiKhoan");
            String anhtaikhoan = bundle.getString("anhTaiKhoan");
//            nguoiDung.setTenDangNhap(binding.edtTenDangNhapDangKy.getText().toString().trim());
//            nguoiDung.setMatKhau(binding.edtNhapPassDangKy.getText().toString().trim());
//            nguoiDung.setHoTen(binding.edtNhapHoTenDangKy.getText().toString());
//            nguoiDung.setSoDienThoai(binding.edtNhapSDTDangKy.getText().toString().trim());
//            nguoiDung.setDiaChi(binding.edtNhapDiaChiDangKy.getText().toString());
//            nguoiDung.setEmail(binding.edtNhapEmailDangKy.getText().toString().trim());
//            nguoiDung.setSoTien(0); // Đặt số tiền mặc định khi đăng ký
//            nguoiDung.setAnhnguoidung(binding.edtAnhDangKy.getText().toString());
//            nguoiDung.setLoaiTaiKhoan("khachhang"); // Đặt loại tài khoản mặc định khi đăng ký
//
//            // Thực hiện đăng ký bằng cách thêm người dùng vào cơ sở dữ liệu
//            NguoiDungDao dao = new NguoiDungDao(man_hinh_dang_ky.this);
//            boolean result = dao.checkDangKy(nguoiDung);
//            NguoiDung nd = new NguoiDung(tenDangNhap, matKhau, hoTen, email, soDienThoai, diaChi, tien, loaiTaiKhoan,anhtaikhoan);
                NguoiDung nd = new NguoiDung();
                nd.setTenDangNhap(tenDangNhap);
            nd.setMatKhau(matKhau);
            nd.setHoTen(hoTen);
            nd.setEmail(email);
            nd.setSoDienThoai(soDienThoai);
            nd.setDiaChi(diaChi);
            nd.setSoTien(tien);
            nd.setLoaiTaiKhoan(loaiTaiKhoan);
            nd.setAnhnguoidung(anhtaikhoan);
            boolean kiemTra = nguoiDungDao.checkDangKy(nd);
            if (kiemTra) {
                list.clear();
                list.addAll(nguoiDungDao.getAllNguoiDung());
                adapterNguoiDung.notifyDataSetChanged();
                Toast.makeText(getContext(), "Thêm người dùng thành công", Toast.LENGTH_SHORT).show();
            }
        }

        return vView;
    }
}