package com.example.pet_pet.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pet_pet.Database.dbHelper;
import com.example.pet_pet.Model.SanPham;

import java.util.ArrayList;



public class SanPhamDao {
    dbHelper dbs;
    private static final String COL_MASP = "masanpham";
    private static final String COL_TENSP = "tensanpham";
    private static final String COL_GIA = "gia";
    private static final String COL_MALOAI = "maloaisanpham";
    private static final String COL_MOTA = "mota";
    private static final String COL_ANHSP = "anhsanpham";
    private static final String COL_SOLUONG = "soluong";
    private static final String COL_SOLUONGBANRA = "soluongbanra";


    public SanPhamDao(Context context) {
        dbs = new dbHelper(context);
    }

    public ArrayList<SanPham> getsanphamall() {
        ArrayList<SanPham> list = new ArrayList();
        SQLiteDatabase database = dbs.getReadableDatabase();
        Cursor cursor = database.rawQuery("select sp.masanpham, sp.tensanpham, sp.gia, lsp.maloaisanpham,lsp.tenloaisanpham,sp.mota,sp.anhsanpham,sp.soluong, sp.soluongbanra from SANPHAM sp, LOAISANPHAM lsp where sp.maloaisanpham = lsp.maLoaisanpham", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7),cursor.getInt(8)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<SanPham> getsanphamallSapXep() {
        ArrayList<SanPham> list = new ArrayList();
        SQLiteDatabase database = dbs.getReadableDatabase();
        Cursor cursor = database.rawQuery("select sp.masanpham, sp.tensanpham, sp.gia, lsp.maloaisanpham,lsp.tenloaisanpham,sp.mota,sp.anhsanpham,sp.soluong, sp.soluongbanra from SANPHAM sp, LOAISANPHAM lsp where sp.maloaisanpham = lsp.maLoaisanpham order by sp.soluongbanra desc", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7),cursor.getInt(8)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public boolean insert(String tensanpham, int gia, int maloaisanpham, String mota, String anhsanpham, int soluong) {
        SQLiteDatabase db = dbs.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensanpham", tensanpham);
        values.put("gia", gia);
        values.put("maloaisanpham", maloaisanpham);
        values.put("mota", mota);
        values.put("anhsanpham", anhsanpham);
        values.put("soluong", soluong);
        values.put("soluongbanra",0);
        long check = db.insert("SANPHAM", null, values);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean update(int masanpham, String tensanpham, int gia, int maloaisanpham, String mota, String anhsanpham, int soluong) {
        SQLiteDatabase db = dbs.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensanpham", tensanpham);
        values.put("gia", gia);
        values.put("maloaisanpham", maloaisanpham);
        values.put("mota", mota);
        values.put("anhsanpham", anhsanpham);
        values.put("soluong", soluong);
        long check = db.update("SANPHAM", values, "masanpham = ?", new String[]{String.valueOf(masanpham)});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int delete(int masanpham) {
        SQLiteDatabase db = dbs.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from CHITIETDONHANG where masanpham = ?", new String[]{String.valueOf(masanpham)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = db.delete("SANPHAM", "masanpham = ?", new String[]{String.valueOf(masanpham)});
        if (check == -1) {
            return 0;
        } else {
            return 1;
        }
    }



    // ... các phương thức khác
    @SuppressLint("Range")
    public SanPham getSanPhamById(int masanpham) {
        SQLiteDatabase database = dbs.getReadableDatabase();
        SanPham sanPham = null;

        String[] columns = {COL_MASP, COL_TENSP, COL_GIA, COL_MALOAI, COL_MOTA, COL_ANHSP, COL_SOLUONG,COL_SOLUONGBANRA};
        String selection = COL_MASP + "=?";
        String[] selectionArgs = {String.valueOf(masanpham)};

        Cursor cursor = database.query("SANPHAM", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int maSanPham = cursor.getInt(cursor.getColumnIndex(COL_MASP));
            String tenSanPham = cursor.getString(cursor.getColumnIndex(COL_TENSP));
            int gia = cursor.getInt(cursor.getColumnIndex(COL_GIA));
            int maLoaiSanPham = cursor.getInt(cursor.getColumnIndex(COL_MALOAI));
            String moTa = cursor.getString(cursor.getColumnIndex(COL_MOTA));
            String anhSanPham = cursor.getString(cursor.getColumnIndex(COL_ANHSP));
            int sl = cursor.getInt(cursor.getColumnIndex(COL_SOLUONG));
            int soluongbanra = cursor.getInt(cursor.getColumnIndex(COL_SOLUONGBANRA));
            sanPham = new SanPham(maSanPham, tenSanPham, gia, maLoaiSanPham, moTa, anhSanPham, sl,soluongbanra);
        }

        cursor.close();
        return sanPham;
    }

    public boolean updateSlSanPham(int maSanPham, int newQuantity,int soluongbanra) {
        SQLiteDatabase database = dbs.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soluong", newQuantity);
        values.put("soluongbanra",soluongbanra);
        // Đảm bảo rằng điều kiện WHERE sử dụng mã sản phẩm đúng
        String whereClause = "masanpham = ?";
        String[] whereArgs = {String.valueOf(maSanPham)};

        // Thực hiện cập nhật
        int rowsAffected = database.update("SANPHAM", values, whereClause, whereArgs);

        // Trả về true nếu có ít nhất một hàng bị ảnh hưởng
        return rowsAffected > 0;
    }
    public ArrayList<SanPham> getSanPhaByMaLoaiSanPham(int maLoaiSanPham) {
        ArrayList<SanPham> list = new ArrayList();
        SQLiteDatabase database = dbs.getReadableDatabase();
//        SanPham(int masanpham, String tensanpham, int gia, int maloaisanpham, String tenloaisanpham, String mota, String anhSanPham, int soluong,int soLuotBanRa)
        String query = "SELECT sp.masanpham, sp.tensanpham, sp.gia, sp.maloaisanpham, lsp.tenloaisanpham, sp.mota, sp.anhsanpham, sp.soluong, sp.soluongbanra " +
                "FROM SANPHAM sp, LOAISANPHAM lsp " +
                "WHERE sp.maloaisanpham = lsp.maLoaisanpham AND sp.maloaisanpham = ?";

        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(maLoaiSanPham)});

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new SanPham(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getInt(7),
                        cursor.getInt(8)
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }


}
