package com.example.pet_pet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;

import com.example.pet_pet.Dao.NguoiDungDao;
import com.example.pet_pet.databinding.ActivityManHinhDangNhapBinding;


public class man_hinh_dang_nhap extends AppCompatActivity {
    ActivityManHinhDangNhapBinding binding;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_nhap);
        binding = ActivityManHinhDangNhapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checkRemember();
       NguoiDungDao nguoiDungDao = new NguoiDungDao(this);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_button);
        binding.btnDangNhap.setAnimation(animation);
        binding.tiedtTenDangNhap.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        binding.tiedtNhapMatKhau.setImeOptions(EditorInfo.IME_ACTION_DONE);
        binding.btnDangNhap.setOnClickListener(view -> {
           view.startAnimation(animation);
            String userName = binding.tiedtTenDangNhap.getText().toString();
            String passWord = binding.tiedtNhapMatKhau.getText().toString();

            if (userName.isEmpty()) {
                binding.tinLTenDangNhap.setError("Không được để trống");

            }
            if (passWord.isEmpty()) {
                binding.tipLMatKhau.setError("Không được để trống");
                return;
            }
            if (nguoiDungDao.checkDangNhap(userName, passWord)) {
                if (binding.chkNhoMatKhau.isChecked()) {
                    editor.putString("username", userName);
                    editor.putString("password", passWord);
                    editor.putBoolean("isChecked", binding.chkNhoMatKhau.isChecked());

                    editor.apply();
                } else {
                    editor.clear();
                    editor.apply();
                }
                binding.tiedtTenDangNhap.setText("");
                binding.tiedtNhapMatKhau.setText("");
                Intent intent = new Intent(man_hinh_dang_nhap.this, MainActivity.class);
                man_hinh_dang_nhap.this.startActivity(intent);
                this.finish();
            } else {
                binding.tiedtTenDangNhap.setError("Tên đăng nhập không đúng");
                binding.tiedtNhapMatKhau.setError("Mật khẩu không đúng");
            }

        });

        binding.txtChuyenQuaDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(man_hinh_dang_nhap.this, man_hinh_dang_ky.class);
                startActivity(intent);
            }
        });
    }

    private void checkRemember() {
        preferences = this.getSharedPreferences("ACCOUNT", MODE_PRIVATE);
        editor = preferences.edit(); // gọi dòng trên và edit vào nó
        boolean isCheck = preferences.getBoolean("isChecked", false);
        if (isCheck) {
            binding.tiedtTenDangNhap.setText(preferences.getString("username", ""));
            binding.tiedtNhapMatKhau.setText(preferences.getString("password", ""));
            binding.chkNhoMatKhau.setChecked(isCheck);
        }
    }
}