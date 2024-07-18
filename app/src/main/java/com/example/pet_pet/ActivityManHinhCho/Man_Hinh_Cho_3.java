package com.example.pet_pet.ActivityManHinhCho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pet_pet.R;
import com.example.pet_pet.databinding.ActivityManHinhCho3Binding;
import com.example.pet_pet.man_hinh_dang_nhap;


public class Man_Hinh_Cho_3 extends AppCompatActivity {
    ActivityManHinhCho3Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.pet_pet.R.layout.activity_man_hinh_cho3);
        binding = ActivityManHinhCho3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnTiepTucMh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Man_Hinh_Cho_3.this, man_hinh_dang_nhap.class);

                // Tạo hiệu ứng chuyển màn hình từ trái sang phải
                ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                        Man_Hinh_Cho_3.this, R.anim.slide_in_right, 0);

                // Bắt đầu Activity mới với hiệu ứng chuyển màn hình
                startActivity(intent, options.toBundle());
            }
        });

    }
}