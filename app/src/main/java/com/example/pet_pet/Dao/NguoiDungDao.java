package com.example.pet_pet.Dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pet_pet.Database.dbHelper;
import com.example.pet_pet.Model.NguoiDung;

import java.util.ArrayList;


public class NguoiDungDao {
    private final dbHelper dbHelper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public NguoiDungDao(Context context) {
        this.dbHelper = new dbHelper(context);
        if (context != null) {
            sharedPreferences = context.getSharedPreferences("NGUOIDUNG", context.MODE_PRIVATE);
        } else {
            // Xử lý khi context là null, có thể thông báo lỗi hoặc thực hiện xử lý phù hợp
            Log.e(TAG, "Context is null in NguoiDungDao constructor");
        }

    }

    public ArrayList<NguoiDung> getAllNguoiDung() {

        ArrayList<NguoiDung> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM TAIKHOAN", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    NguoiDung nguoiDung = new NguoiDung();
                    nguoiDung.setMaTaiKhoan(cursor.getInt(0));
                    nguoiDung.setTenDangNhap(cursor.getString(1));
                    nguoiDung.setMatKhau(cursor.getString(2));
                    nguoiDung.setHoTen(cursor.getString(3));
                    nguoiDung.setEmail(cursor.getString(4));
                    nguoiDung.setSoDienThoai(cursor.getString(5));
                    nguoiDung.setDiaChi(cursor.getString(6));
                    nguoiDung.setSoTien(cursor.getInt(7));
                    nguoiDung.setLoaiTaiKhoan(cursor.getString(8));
                    nguoiDung.setAnhnguoidung(cursor.getString(9));
                    list.add(nguoiDung);
                    cursor.moveToNext();
                }

            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);
        }

        return list;
    }

    public boolean checkDangNhap(String tenDangNhap, String matKhau) {
        Log.d(TAG, "CheckDangNhap: " + tenDangNhap + " - " + matKhau);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM TAIKHOAN WHERE tendangnhap = ? AND matkhau = ?", new String[]{tenDangNhap, matKhau});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                editor = sharedPreferences.edit();
                editor.putInt("mataikhoan", cursor.getInt(0));
                editor.putString("tendangnhap", cursor.getString(1));
                editor.putString("matkhau", cursor.getString(2));
                editor.putString("hoten", cursor.getString(3));
                editor.putString("email", cursor.getString(4));
                editor.putString("sodienthoai", cursor.getString(5));
                editor.putString("diachi", cursor.getString(6));
                editor.putInt("sotien", cursor.getInt(7));
                editor.putString("loaitaikhoan", cursor.getString(8));
                editor.putString("anhtaikhoan", cursor.getString(9));
                editor.apply();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi kiểm tra đăng nhập", e);
            return false;
        }
    }

    public boolean checkDangKy(NguoiDung nguoiDung) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tendangnhap", nguoiDung.getTenDangNhap());
        values.put("matkhau", nguoiDung.getMatKhau());
        values.put("hoten", nguoiDung.getHoTen());
        values.put("email", nguoiDung.getEmail());
        values.put("sodienthoai", nguoiDung.getSoDienThoai());
        values.put("diachi", nguoiDung.getDiaChi());
        values.put("sotien", nguoiDung.getSoTien());
        values.put("loaitaikhoan", nguoiDung.getLoaiTaiKhoan());
        values.put("anhtaikhoan", nguoiDung.getAnhnguoidung());
        long result = db.insert("TAIKHOAN", null, values);
        return result != -1;
    }

    public boolean tenDangNhapDaTonTai(String tenDangNhap) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM TAIKHOAN WHERE tendangnhap =?";
        Cursor cursor = db.rawQuery(query, new String[]{tenDangNhap});
        boolean tonTai = cursor.getCount() > 0;
        cursor.close();
        return tonTai;
    }

    public boolean xoaNguoiDung(NguoiDung nd) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long check = database.delete("TAIKHOAN", "mataikhoan = ?", new String[]{String.valueOf(nd.getMaTaiKhoan())});
        return check > 0;
    }

    public boolean update(int manguoidung, String tennguoidung, int sotien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", tennguoidung);
        values.put("sotien", sotien);
        long check = db.update("TAIKHOAN", values, "mataikhoan = ?", new String[]{String.valueOf(manguoidung)});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updatekhachhang(NguoiDung nguoiDung) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("anhtaikhoan", nguoiDung.getAnhnguoidung());
        values.put("hoten", nguoiDung.getHoTen());
        values.put("tendangnhap", nguoiDung.getTenDangNhap());
        values.put("sodienthoai", nguoiDung.getSoDienThoai());
        values.put("matkhau", nguoiDung.getMatKhau());
        values.put("email", nguoiDung.getEmail());
        values.put("diachi", nguoiDung.getDiaChi());
        long check = db.update("TAIKHOAN", values, "mataikhoan = ?", new String[]{String.valueOf(nguoiDung.getMaTaiKhoan())});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateSoTien(int maTaiKhoan, int soTienMoi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sotien", soTienMoi);

        long result = db.update("TAIKHOAN", values, "mataikhoan = ?", new String[]{String.valueOf(maTaiKhoan)});

        return result != -1;
    }
    public NguoiDung getNguoiDungByMaTaiKhoan(int maTaiKhoan) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        NguoiDung nguoiDung = null;

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM TAIKHOAN WHERE mataikhoan = ?", new String[]{String.valueOf(maTaiKhoan)});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                nguoiDung = new NguoiDung();
                nguoiDung.setMaTaiKhoan(cursor.getInt(0));
                nguoiDung.setTenDangNhap(cursor.getString(1));
                nguoiDung.setMatKhau(cursor.getString(2));
                nguoiDung.setHoTen(cursor.getString(3));
                nguoiDung.setEmail(cursor.getString(4));
                nguoiDung.setSoDienThoai(cursor.getString(5));
                nguoiDung.setDiaChi(cursor.getString(6));
                nguoiDung.setSoTien(cursor.getInt(7));
                nguoiDung.setLoaiTaiKhoan(cursor.getString(8));
                nguoiDung.setAnhnguoidung(cursor.getString(9));
            }

            cursor.close();
        } catch (Exception e) {
            Log.e(TAG, "Lỗi", e);
        }

        return nguoiDung;
    }

}
