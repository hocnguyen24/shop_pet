package com.example.pet_pet.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pet_pet.Dao.ThongKeDao;
import com.example.pet_pet.Model.DonHangChiTiet;
import com.example.pet_pet.adapter.adapter_top3_sanphambanchay;
import com.example.pet_pet.databinding.FragmentThongKeBinding;

import java.util.ArrayList;
import java.util.Calendar;



public class ThongKe extends Fragment {

    FragmentThongKeBinding binding;
    View vieww;

    public ThongKe() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentThongKeBinding.inflate(inflater, container, false);
        vieww = binding.getRoot();
        ThongKeDao dao=new ThongKeDao(getContext());
        ArrayList<DonHangChiTiet>list=dao.getTop3();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvctop3sp.setLayoutManager(layoutManager);
        adapter_top3_sanphambanchay adapter=new adapter_top3_sanphambanchay(list,getContext());
        binding.rvctop3sp.setAdapter(adapter);
        Calendar calendar = Calendar.getInstance();

        binding.btnlichBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                Calendar selectedCalendar = Calendar.getInstance();
                                selectedCalendar.set(year, month, dayOfMonth);

                                // Kiểm tra nếu ngày chọn là ngày hiện tại hoặc sau ngày hiện tại và không quá ngày hiện tại
                                if (!selectedCalendar.after(Calendar.getInstance())) {
                                    String ngay = (dayOfMonth < 10) ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                                    String thang = ((month + 1) < 10) ? "0" + (month + 1) : String.valueOf(month + 1);
                                    binding.btnlichBatDau.setText(year + "/" + thang + "/" + ngay);
//                                    binding.btnlichBatDau.setText(ngay+"/"+thang+"/"+year);
                                } else {
                                    // Hiển thị thông báo hoặc thực hiện hành động khác nếu người dùng chọn ngày trước hoặc bằng ngày hiện tại.
                                    // Ví dụ: Toast.makeText(getContext(), "Chọn ngày trong khoảng từ ngày hiện tại đến quá khứ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                // Đặt giới hạn cho DatePickerDialog để chỉ cho phép chọn ngày trong khoảng từ ngày hiện tại đến quá khứ
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


        binding.btnlichKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                Calendar selectedCalendar = Calendar.getInstance();
                                selectedCalendar.set(year, month, dayOfMonth);

                                // Kiểm tra nếu ngày chọn là ngày hiện tại hoặc sau ngày hiện tại và không quá ngày hiện tại
                                if (!selectedCalendar.after(Calendar.getInstance())) {
                                    String ngay = (dayOfMonth < 10) ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                                    String thang = ((month + 1) < 10) ? "0" + (month + 1) : String.valueOf(month + 1);

                                    binding.btnlichKetThuc.setText(year + "/" + thang + "/" + ngay);
//                                    binding.btnlichKetThuc.setText(ngay+"/"+thang+"/"+year);
                                } else {
                                    // Hiển thị thông báo hoặc thực hiện hành động khác nếu người dùng chọn ngày trước hoặc bằng ngày hiện tại.
                                    // Ví dụ: Toast.makeText(getContext(), "Chọn ngày trong khoảng từ ngày hiện tại đến quá khứ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                // Đặt giới hạn cho DatePickerDialog để chỉ cho phép chọn ngày trong khoảng từ ngày hiện tại đến quá khứ
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


        binding.btnthongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThongKeDao dao = new ThongKeDao(getContext());
//                "Ngày bắt đầu: "+ ngay + "/" + thang + "/" + year
                String ngaybd = binding.btnlichBatDau.getText().toString();
                String ngaykt = binding.btnlichKetThuc.getText().toString();

                int tongtien = dao.tongDoanhThu(ngaybd, ngaykt);
                binding.txttongTien.setText(" Tổng doanh thu:"+String.valueOf(tongtien) );

                int tongdon = dao.tongDonHang(ngaybd, ngaykt);
                binding.txtsoLuongDon.setText("Số lượng đơn hàng đã bán ra: "+String.valueOf(tongdon));

            }
        });
        return vieww;
    }
}