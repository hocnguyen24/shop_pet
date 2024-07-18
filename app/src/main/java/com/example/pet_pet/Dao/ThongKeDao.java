package com.example.pet_pet.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pet_pet.Database.dbHelper;
import com.example.pet_pet.Model.DonHangChiTiet;

import java.util.ArrayList;



public class ThongKeDao {
    dbHelper dbs;

    public ThongKeDao(Context context) {
        dbs = new dbHelper(context);
    }

    public int tongDoanhThu(String ngayBatDau, String ngayKetThuc) {
        ngayBatDau = ngayBatDau.replace("/", "");
        ngayKetThuc = ngayKetThuc.replace("/", "");
        SQLiteDatabase sqLiteDatabase = dbs.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(tongtien) FROM DONHANG WHERE substr(ngaydathang,7) || substr(ngaydathang,4,2) || substr(ngaydathang,1,2) BETWEEN ? AND ?", new String[]{ngayBatDau, ngayKetThuc});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        } else {

            return 0;
        }
    }

    public int tongDonHang(String ngayBatDau, String ngayKetThuc) {
        ngayBatDau = ngayBatDau.replace("/", "");
        ngayKetThuc = ngayKetThuc.replace("/", "");
        SQLiteDatabase sqLiteDatabase = dbs.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(madonhang) FROM DONHANG WHERE substr(ngaydathang,7) || substr(ngaydathang,4,2) || substr(ngaydathang,1,2) BETWEEN ? AND ?", new String[]{ngayBatDau, ngayKetThuc});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        } else {
            return 0;
        }
    }

    public ArrayList<DonHangChiTiet> getTop3() {
        ArrayList<DonHangChiTiet> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbs.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SANPHAM.masanpham, SANPHAM.tensanpham, SUM(CHITIETDONHANG.soluong) AS soluongban\n" +
                "FROM SANPHAM\n" +
                "JOIN CHITIETDONHANG ON SANPHAM.masanpham = CHITIETDONHANG.masanpham\n" +
                "GROUP BY SANPHAM.masanpham\n" +
                "ORDER BY soluongban DESC\n" +
                "LIMIT 3;", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new DonHangChiTiet(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            } while (cursor.moveToNext());
        }

        return list;
    }

}
