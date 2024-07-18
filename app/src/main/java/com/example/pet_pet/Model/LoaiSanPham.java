package com.example.pet_pet.Model;

public class LoaiSanPham {
    private int maloaisp;
    private String tenloaisp;

    public LoaiSanPham(int maloaisp, String tenloaisp) {
        this.maloaisp = maloaisp;
        this.tenloaisp = tenloaisp;
    }

    public LoaiSanPham() {
    }

    public int getMaloaisp() {
        return maloaisp;
    }

    public void setMaloaisp(int maloaisp) {
        this.maloaisp = maloaisp;
    }

    public String getTenloaisp() {
        return tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
        this.tenloaisp = tenloaisp;
    }
}
