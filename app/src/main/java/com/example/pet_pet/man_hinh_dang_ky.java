package com.example.pet_pet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Toast;

import com.example.pet_pet.Dao.NguoiDungDao;
import com.example.pet_pet.Model.NguoiDung;
import com.example.pet_pet.databinding.ActivityManHinhDangKyBinding;



public class man_hinh_dang_ky extends AppCompatActivity {
    ActivityManHinhDangKyBinding binding;

    NguoiDung nguoiDung = new NguoiDung();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_ky);
        binding = ActivityManHinhDangKyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imgTroVeDangNhap.setOnClickListener(view -> {
            Intent intent = new Intent(man_hinh_dang_ky.this, man_hinh_dang_nhap.class);
            startActivity(intent);
        });
        binding.btnDangKyDangKy.setOnClickListener(view -> {
            if (validateDangKy()) {
                clickDangKy();
            }

        });
    }

    private void clickDangKy() {
        // Lấy thông tin từ các trường nhập liệu
        nguoiDung.setTenDangNhap(binding.edtTenDangNhapDangKy.getText().toString().trim());
        nguoiDung.setMatKhau(binding.edtNhapPassDangKy.getText().toString().trim());
        nguoiDung.setHoTen(binding.edtNhapHoTenDangKy.getText().toString());
        nguoiDung.setSoDienThoai(binding.edtNhapSDTDangKy.getText().toString().trim());
        nguoiDung.setDiaChi(binding.edtNhapDiaChiDangKy.getText().toString());
        nguoiDung.setEmail(binding.edtNhapEmailDangKy.getText().toString().trim());
        nguoiDung.setSoTien(0); // Đặt số tiền mặc định khi đăng ký
        nguoiDung.setAnhnguoidung(binding.edtAnhDangKy.getText().toString());
        nguoiDung.setLoaiTaiKhoan("khachhang"); // Đặt loại tài khoản mặc định khi đăng ký

        // Thực hiện đăng ký bằng cách thêm người dùng vào cơ sở dữ liệu
        NguoiDungDao dao = new NguoiDungDao(man_hinh_dang_ky.this);
        boolean result = dao.checkDangKy(nguoiDung);

        if (result) {
            // Đăng ký thành công
            Intent intent = new Intent(man_hinh_dang_ky.this, man_hinh_dang_nhap.class);
            startActivity(intent);
        } else {
            // Đăng ký thất bại
            Toast.makeText(man_hinh_dang_ky.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateDangKy() {
        NguoiDungDao dao = new NguoiDungDao(man_hinh_dang_ky.this);
        String tenDangNhap = binding.edtTenDangNhapDangKy.getText().toString().trim();
        String matKhau = binding.edtNhapPassDangKy.getText().toString().trim();
        String nhapLaiMatKhau = binding.edtNhapLaiPassDangKy.getText().toString().trim();
        String hoTen = binding.edtNhapHoTenDangKy.getText().toString().trim();
        String sdt = binding.edtNhapSDTDangKy.getText().toString().trim();
        String diaChi = binding.edtNhapDiaChiDangKy.getText().toString().trim();
        String email = binding.edtNhapEmailDangKy.getText().toString().trim();
        String anh = binding.edtAnhDangKy.getText().toString().trim();
        boolean isValid = true;
        if (tenDangNhap.isEmpty()) {
            binding.tipLTenDangNhapDangKy.setError("Vui lòng nhập tên đăng nhập");
            isValid = false;
        } else if (dao.tenDangNhapDaTonTai(tenDangNhap)) {
            binding.tipLTenDangNhapDangKy.setError("Tên đăng nhập đã tồn tại, vui lòng chọn tên khác");
            return false;
        } else {
            binding.tipLTenDangNhapDangKy.setError(null);
        }

        if (matKhau.isEmpty()) {
            binding.tipLNhapPassDangKy.setError("Vui lòng nhập mật khẩu");
            isValid = false;
        } else {
            binding.tipLNhapPassDangKy.setError(null);
        }
        if (anh.isEmpty()) {
            binding.tipLAnhDangKy.setError("Vui lòng nhập link ảnh");
            isValid = false;
        } else {
            binding.tipLAnhDangKy.setError(null);
        }

        if (nhapLaiMatKhau.isEmpty()) {
            binding.tiLNhapLaiPassDangKy.setError("Vui lòng nhập lại mật khẩu");
            isValid = false;
        } else if (!nhapLaiMatKhau.equals(matKhau)) {
            binding.tiLNhapLaiPassDangKy.setError("Mật khẩu không trùng nhau");
            isValid = false;
        } else {
            binding.tiLNhapLaiPassDangKy.setError(null);
        }

        if (hoTen.isEmpty()) {
            binding.tiLNhapHoTenDangKy.setError("Vui lòng nhập họ tên");
            isValid = false;
        } else {
            binding.tiLNhapHoTenDangKy.setError(null);
        }

        if (sdt.isEmpty()) {
            binding.tiLNhapSDTDangKy.setError("Vui lòng nhập số điện thoại");
            isValid = false;
        } else if (!isValidPhoneNumber(sdt)) {
            binding.tiLNhapSDTDangKy.setError("Số điện thoại không hợp lệ");
            isValid = false;
        } else {
            binding.tiLNhapSDTDangKy.setError(null);
        }

        if (diaChi.isEmpty()) {
            binding.tiLNhapDiaChiDangKy.setError("Vui lòng nhập địa chỉ");
            isValid = false;
        } else {
            binding.tiLNhapDiaChiDangKy.setError(null);
        }

        if (email.isEmpty()) {
            binding.tiLNhapEmailDangKy.setError("Vui lòng nhập email");
            isValid = false;
        } else if (!isValidEmail(email)) {
            binding.tiLNhapEmailDangKy.setError("Email không hợp lệ");
            isValid = false;
        } else {
            binding.tiLNhapEmailDangKy.setError(null);
        }

        return isValid;

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