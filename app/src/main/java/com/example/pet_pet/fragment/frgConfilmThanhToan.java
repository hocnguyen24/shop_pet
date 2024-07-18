package com.example.pet_pet.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pet_pet.Dao.DonHangChiTietDao;
import com.example.pet_pet.Model.DonHangChiTiet;
import com.example.pet_pet.R;
import com.example.pet_pet.adapter.adapter_thanh_toan;
import com.example.pet_pet.databinding.FragmentFrgConfilmThanhToanBinding;

import java.util.ArrayList;




public class frgConfilmThanhToan extends Fragment {


    public frgConfilmThanhToan() {
        // Required empty public constructor
    }

    private FragmentFrgConfilmThanhToanBinding binding;
    private ArrayList<DonHangChiTiet> list = new ArrayList<>();
    private DonHangChiTietDao chiTietDao;
    private adapter_thanh_toan adapterThanhToan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFrgConfilmThanhToanBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        chiTietDao = new DonHangChiTietDao(getContext());
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        if (bundle != null) {
            int maDonHang = bundle.getInt("maDonHang", 0);
            if (maDonHang != 0) {
                list = chiTietDao.getChiTietDonHangByMaDonHang(maDonHang);
                adapterThanhToan = new adapter_thanh_toan(list, getContext());
                binding.rcv.setAdapter(adapterThanhToan);

            }
        }
        binding.btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frgGioHang frgGioHang=new frgGioHang();
                FragmentManager fragmentManager=getParentFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.frameLayoutMain,frgGioHang);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return binding.getRoot();
    }
}