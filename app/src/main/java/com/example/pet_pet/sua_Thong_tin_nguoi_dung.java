package com.example.pet_pet;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pet_pet.Dao.NguoiDungDao;
import com.example.pet_pet.Model.NguoiDung;
import com.example.pet_pet.databinding.ActivitySuaThongTinNguoiDungBinding;

import java.util.ArrayList;



public class sua_Thong_tin_nguoi_dung extends AppCompatActivity {
    ActivitySuaThongTinNguoiDungBinding biding;
    NguoiDungDao dao;
    NguoiDung nguoiDung;
    private ArrayList<NguoiDung> list = new ArrayList<>();
    String tendangnhap1, matkhaucu1, matkhaumoi1, nhaplaimkmoi1, hoten1, email1, diachi1, sodienthoai1, matkhau, anh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thong_tin_nguoi_dung);
        biding = ActivitySuaThongTinNguoiDungBinding.inflate(getLayoutInflater());
        setContentView(biding.getRoot());
        SharedPreferences preferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        int maTK = preferences.getInt("mataikhoan",0);
        String tenDN = preferences.getString("tendangnhap", "");
        matkhau = preferences.getString("matkhau", "");
        String hoten = preferences.getString("hoten", "");
        String email = preferences.getString("email", "");
        String sodienthoai = preferences.getString("sodienthoai", "");
        String diachi = preferences.getString("diachi", "");
        String urlAnh = preferences.getString("anhtaikhoan", "");
        dao = new NguoiDungDao(this);
        nguoiDung = dao.getNguoiDungByMaTaiKhoan(maTK);
        biding.edtTenDangNhapDangKy.setText(tenDN);
        biding.edtNhapHoTen.setText(hoten);
        biding.edtNhapEmailDangKy.setText(email);
        biding.edtNhapDiaChiDangKy.setText(diachi);
        biding.edtNhapSDT.setText(sodienthoai);
        biding.edtNhapUrl.setText(urlAnh);
        //////
        biding.imgTroVeDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sua_Thong_tin_nguoi_dung.this, Profile.class));
            }
        });

        biding.btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validTenDangNhap();
                validMatKhauCu();
                validNhapLaiMatKhauMoi();
                validEmail();
                validDiaChi();
                validHoTen();
                validSoDienThoai();
                validUrl();
                if (biding.edtTenDangNhapDangKy.getError() == null &&
                        biding.edmatKhau.getError() == null &&
                        biding.edmatKhauMoi.getError() == null &&
                        biding.edtNhapLaiPassMoi.getError() == null &&
                        biding.edtNhapHoTen.getError() == null &&
                        biding.edtNhapEmailDangKy.getError() == null &&
                        biding.edtNhapDiaChiDangKy.getError() == null &&
                        biding.edtNhapSDT.getError() == null &&
                        biding.edtNhapUrl.getError() == null) {
                    if (matkhaucu1.equals(matkhau)) {
                        nguoiDung.setTenDangNhap(tendangnhap1);
                        nguoiDung.setMatKhau(matkhaumoi1);
                        nguoiDung.setHoTen(hoten1);
                        nguoiDung.setEmail(email1);
                        nguoiDung.setDiaChi(diachi1);
                        nguoiDung.setAnhnguoidung(urlAnh);
                        nguoiDung.setSoDienThoai(sodienthoai1);
                        boolean result = dao.updatekhachhang(nguoiDung);
                        if (result) {
                            list.clear();
                            list = dao.getAllNguoiDung();
                            Intent intent = new Intent(sua_Thong_tin_nguoi_dung.this, man_hinh_dang_nhap.class);
                            startActivity(intent);
                            Toast.makeText(sua_Thong_tin_nguoi_dung.this, "Đổi thông tin thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            // Đăng ký thất bại
                            Toast.makeText(sua_Thong_tin_nguoi_dung.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        biding.edmatKhau.setError("mật khẩu cũ không trùng khớp");
                    }
                }
            }
        });
    }

    private void validTenDangNhap() {
        tendangnhap1 = biding.edtTenDangNhapDangKy.getText().toString();
        if (tendangnhap1.isEmpty()) {
            biding.edtTenDangNhapDangKy.setError("Vui lòng nhập tên đăng nhập");
        } else {
            biding.edtTenDangNhapDangKy.setError(null);
        }
    }

    private void validMatKhauCu() {
        matkhaucu1 = biding.edmatKhau.getText().toString();
        if (matkhaucu1.isEmpty()) {
            biding.edmatKhau.setError("Vui lòng nhập lại mật khẩu");

        } else if (!matkhaucu1.equals(matkhau)) {
            biding.edmatKhau.setError("Mật khẩu không đúng");
        } else {
            biding.edmatKhau.setError(null);
        }
    }



    private void validNhapLaiMatKhauMoi() {
        nhaplaimkmoi1 = biding.edtNhapLaiPassMoi.getText().toString();
        matkhaumoi1 = biding.edmatKhauMoi.getText().toString();
        if (nhaplaimkmoi1.isEmpty()) {
            biding.edtNhapLaiPassMoi.setError("Vui lòng nhập lại mật khẩu");
        } else if (matkhaumoi1.isEmpty()) {
            biding.edmatKhauMoi.setError("Vui lòng nhập mật khẩu mới");
        } else if (!nhaplaimkmoi1.equals(matkhaumoi1)) {
            biding.edtNhapLaiPassMoi.setError("Mật khẩu không trùng nhau");
        } else {
            biding.edtNhapLaiPassMoi.setError(null);
            biding.edmatKhauMoi.setError(null);
        }
    }

    private void validHoTen() {
        hoten1 = biding.edtNhapHoTen.getText().toString();
        if (hoten1.isEmpty()) {
            biding.edtNhapHoTen.setError("Vui lòng nhập họ tên");
        } else {
            biding.edtNhapHoTen.setError(null);
        }
    }

    private void validEmail() {
        email1 = biding.edtNhapEmailDangKy.getText().toString();
        if (email1.isEmpty()) {
            biding.edtNhapEmailDangKy.setError("Vui lòng nhập email");
        } else if (!isValidEmail(email1)) {
            biding.edtNhapEmailDangKy.setError("Email không hợp lệ");
        } else {
            biding.edtNhapEmailDangKy.setError(null);
        }
    }

    private void validDiaChi() {
        diachi1 = biding.edtNhapDiaChiDangKy.getText().toString();
        if (diachi1.isEmpty()) {
            biding.edtNhapDiaChiDangKy.setError("Vui lòng nhập địa chỉ");
        } else {
            biding.edtNhapDiaChiDangKy.setError(null);
        }
    }

    private void validSoDienThoai() {
        sodienthoai1 = biding.edtNhapSDT.getText().toString();
        if (sodienthoai1.isEmpty()) {
            biding.edtNhapSDT.setError("Vui lòng nhập số điện thoại");
        } else if (!isValidPhoneNumber(sodienthoai1)) {
            biding.edtNhapSDT.setError("Số điện thoại không hợp lệ");
        } else {
            biding.edtNhapSDT.setError(null);
        }
    }

    private void validUrl() {
        anh = biding.edtNhapUrl.getText().toString();
        if (anh.isEmpty()) {
            biding.edtNhapUrl.setError("Vui lòng nhập địa chỉ");
        } else {
            biding.edtNhapUrl.setError(null);
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "0\\d{9}";
        return phoneNumber.matches(regex);
    }

    // Hàm kiểm tra định dạng email
    private boolean isValidEmail(String email) {
        String regex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+(\\.+[a-z]+)?";
        return email.matches(regex);
    }
}