package com.example.pet_pet.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pet_pet.Dao.NguoiDungDao;
import com.example.pet_pet.R;
import com.example.pet_pet.databinding.FragmentFrgAddNguoiDungBinding;


public class frgAddNguoiDung extends Fragment {


    public frgAddNguoiDung() {
        // Required empty public constructor
    }

    String tenDangNhap, matKhau, hoTen, email, soDienThoai, diaChi, loaiTaiKhoan,anhTaiKhoan;
    FragmentFrgAddNguoiDungBinding binding;

    int soTien;
    private NguoiDungDao dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFrgAddNguoiDungBinding.inflate(inflater, container, false);
        dao = new NguoiDungDao(getContext());
        binding.inTenTaiKhoan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    validTenDangNhap();
                }
            }
        });
        binding.inMatKhau.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    validMatKhau();
                }
            }
        });
        binding.inEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    validEmail();
                }
            }
        });
        binding.inDiaChi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    validDiaChi();
                }
            }
        });
        binding.inHoTen.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    validHoTen();
                }
            }
        });
        binding.inSoDienThoai.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    validSoDienThoai();
                }
            }
        });
        binding.inTien.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    validSoTien();
                }
            }
        });
        binding.inLoaiTaiKhoan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    validLoaiTaiKhoan();
                }
            }
        });
        binding.inAnhTaiKhoan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b){
                    validUrl();
                }
            }
        });

        binding.btnConfilmThemNguoiDung.setOnClickListener(view -> putData());
        binding.btnOutThemNguoiDung.setOnClickListener(view -> {
            frgQuanLyNguoiDung frgQuanLyNguoiDung = new frgQuanLyNguoiDung();

            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
            fragmentTransaction.replace(R.id.frameLayoutMain, frgQuanLyNguoiDung);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void putData() {

        validTenDangNhap();
        validMatKhau();
        validEmail();
        validDiaChi();
        validHoTen();
        validSoDienThoai();
        validSoTien();
        validLoaiTaiKhoan();
        validUrl();
        if (binding.inTenTaiKhoan.getError() == null &&
                binding.inMatKhau.getError() == null &&
                binding.inHoTen.getError() == null &&
                binding.inEmail.getError() == null &&
                binding.inSoDienThoai.getError() == null &&
                binding.inDiaChi.getError() == null &&
                binding.inTien.getError() == null &&
                binding.inLoaiTaiKhoan.getError() == null &&
                binding.inAnhTaiKhoan.getError() == null) {
            Bundle bundle = new Bundle();
            bundle.putString("tenDangNhap", tenDangNhap);
            bundle.putString("matKhau", matKhau);
            bundle.putString("hoTen", hoTen);
            bundle.putString("email", email);
            bundle.putString("soDienThoai", soDienThoai);
            bundle.putString("diaChi", diaChi);
            bundle.putInt("soTien", soTien);
            bundle.putString("loaiTaiKhoan", loaiTaiKhoan);
            bundle.putString("anhTaiKhoan",anhTaiKhoan);
            frgQuanLyNguoiDung frgQuanLyNguoiDung = new frgQuanLyNguoiDung();
            frgQuanLyNguoiDung.setArguments(bundle);
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayoutMain, frgQuanLyNguoiDung);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else {
            Toast.makeText(getContext(), "Vui lòng kiểm tra trường dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }

    private void validTenDangNhap() {
        tenDangNhap = binding.edtTenTaiKhoan.getText().toString().trim();
        if (tenDangNhap.isEmpty()) {
            binding.inTenTaiKhoan.setError("Không được để trống");
        } else if (dao.tenDangNhapDaTonTai(tenDangNhap)) {
            binding.inTenTaiKhoan.setError("Tên đăng nhập đã tồn tại, vui lòng chọn tên khác");
        } else {
            binding.inTenTaiKhoan.setError(null);
        }
    }

    private void validMatKhau() {
        matKhau = binding.edtMatKhau.getText().toString().trim();
        if (matKhau.isEmpty()) {
            binding.inMatKhau.setError("Không được để trống");
        } else {
            binding.inMatKhau.setError(null);
        }
    }
    private void validUrl() {
        anhTaiKhoan = binding.edtAnhTaiKhoan.getText().toString();
        if (anhTaiKhoan.isEmpty()) {
            binding.inAnhTaiKhoan.setError("Không được để trống");
        } else {
            binding.inAnhTaiKhoan.setError(null);
        }
    }
    private void validHoTen() {
        hoTen = binding.edtHoTen.getText().toString();
        if (hoTen.isEmpty()) {
            binding.inHoTen.setError("Không được để trống");
        } else {
            binding.inHoTen.setError(null);
        }
    }

    private void validEmail() {
        email = binding.edtEmail.getText().toString().trim();
        if (email.isEmpty()) {
            binding.inEmail.setError("Không được để trống");
        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+(\\.+[a-z]+)?")) {
            binding.inEmail.setError("Email không hợp lệ");
        } else {
            binding.inEmail.setError(null);
        }
    }

    private void validSoDienThoai() {
        soDienThoai = binding.edtSoDienThoai.getText().toString().trim();
        if (soDienThoai.isEmpty()) {
            binding.inSoDienThoai.setError("Không được để trống");
        } else if (!soDienThoai.matches("0\\d{9}")) {
            binding.inSoDienThoai.setError("Số điện thoại phải là 10 số");
        } else {
            binding.inSoDienThoai.setError(null);
        }
    }

    private void validDiaChi() {
        diaChi = binding.edtDiaChi.getText().toString();
        if (diaChi.isEmpty()) {
            binding.inDiaChi.setError("Không được để trống");
        } else {
            binding.inDiaChi.setError(null);
        }
    }

    private void validSoTien() {
        try {
            soTien = Integer.parseInt(binding.edtTien.getText().toString());
            if (soTien < 0) {
                binding.inTien.setError("Tiền không được là số âm");
            } else {
                binding.inTien.setError(null);
            }
        } catch (NumberFormatException e) {
            binding.inTien.setError("Vui lòng nhập số tiền hợp lệ");
        }
    }

    private void validLoaiTaiKhoan() {
        loaiTaiKhoan = binding.edtLoaiTaiKhoan.getText().toString().trim();
        if (loaiTaiKhoan.isEmpty()) {
            binding.inLoaiTaiKhoan.setError("Không được để trống");
        } else {
            binding.inLoaiTaiKhoan.setError(null);
        }
    }
}