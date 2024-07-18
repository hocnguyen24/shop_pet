package com.example.pet_pet.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class SanPham implements Parcelable {
    private int masanpham;
    private String tensanpham;
    private int gia;
    private int maloaisanpham;

    private String tenloaisanpham;
    private String mota;
    private String anhSanPham;
    private int soluong;
    private int soLuotBanRa;

    //    maSanPham, tenSanPham, gia, maLoaiSanPham, moTa, anhSanPham)
    public SanPham() {
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }


    public SanPham(int masanpham, String tensanpham, int gia, int maloaisanpham, String tenloaisanpham, String mota, String anhSanPham, int soluong,int soLuotBanRa) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.gia = gia;
        this.maloaisanpham = maloaisanpham;
        this.tenloaisanpham = tenloaisanpham;
        this.mota = mota;
        this.anhSanPham = anhSanPham;
        this.soluong = soluong;
        this.soLuotBanRa =soLuotBanRa;
    }

    public SanPham(int masanpham, String tensanpham, int gia, int maloaisanpham, String mota, String anhSanPham, int soluong,int soLuotBanRa) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.gia = gia;
        this.maloaisanpham = maloaisanpham;
        this.mota = mota;
        this.anhSanPham = anhSanPham;
        this.soluong = soluong;
        this.soLuotBanRa =soLuotBanRa;
    }

    public SanPham(int masanpham, String tensanpham, int gia, int maloaisanpham, String tenloaisanpham, String mota, String anhSanPham) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.gia = gia;
        this.maloaisanpham = maloaisanpham;
        this.tenloaisanpham = tenloaisanpham;
        this.mota = mota;
        this.anhSanPham = anhSanPham;
    }

    public int getSoLuotBanRa() {
        return soLuotBanRa;
    }

    public void setSoLuotBanRa(int soLuotBanRa) {
        this.soLuotBanRa = soLuotBanRa;
    }

    public String getAnhSanPham() {
        return anhSanPham;
    }

    public void setAnhSanPham(String anhSanPham) {
        this.anhSanPham = anhSanPham;
    }

    public SanPham(String tensanpham, int gia) {
        this.tensanpham = tensanpham;
        this.gia = gia;
    }

    public SanPham(int masanpham, int soluong) {
        this.masanpham = masanpham;
        this.soluong = soluong;
    }

    public SanPham(int masanpham, String tensanpham, int gia, int maloaisanpham, String mota) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.gia = gia;
        this.maloaisanpham = maloaisanpham;
        this.mota = mota;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public SanPham(int masanpham, String tensanpham, int gia, int maloaisanpham) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.gia = gia;
        this.maloaisanpham = maloaisanpham;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getMaloaisanpham() {
        return maloaisanpham;
    }

    public void setMaloaisanpham(int maloaisanpham) {
        this.maloaisanpham = maloaisanpham;
    }

    public String getTenloaisanpham() {
        return tenloaisanpham;
    }

    public void setTenloaisanpham(String tenloaisanpham) {
        this.tenloaisanpham = tenloaisanpham;
    }

    public SanPham(Parcel in) {
        // Đọc dữ liệu từ Parcel và đặt vào các thuộc tính
        masanpham = in.readInt();
        tensanpham = in.readString();
        gia = in.readInt();
        maloaisanpham = in.readInt();
        tenloaisanpham = in.readString();
        mota = in.readString();
        soluong = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(masanpham);
        parcel.writeString(tensanpham);
        parcel.writeInt(gia);
        parcel.writeInt(maloaisanpham);
        parcel.writeString(tenloaisanpham);
        parcel.writeString(mota);
        parcel.writeInt(soluong);
    }

    public static final Creator<SanPham> CREATOR = new Creator<SanPham>() {

        @Override
        public SanPham createFromParcel(Parcel parcel) {
            return new SanPham(parcel);
        }

        @Override
        public SanPham[] newArray(int i) {
            return new SanPham[i];
        }
    };
}
