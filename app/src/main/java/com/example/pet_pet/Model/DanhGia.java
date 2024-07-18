package com.example.pet_pet.Model;
public class DanhGia {
    private int maDanhGia;
    private int maTaiKhoan;
    private String tenTaiKhoan;
    private int maSanPham;
    private String tenSanPham;
    private String danhGia;
    private String nhanXet;
    private String ngayDanhGia;

    public DanhGia() {
    }

    public DanhGia(int maDanhGia, int maTaiKhoan, String tenTaiKhoan, int maSanPham, String tenSanPham, String danhGia, String nhanXet, String ngayDanhGia) {
        this.maDanhGia = maDanhGia;
        this.maTaiKhoan = maTaiKhoan;
        this.tenTaiKhoan = tenTaiKhoan;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.danhGia = danhGia;
        this.nhanXet = nhanXet;
        this.ngayDanhGia = ngayDanhGia;
    }

    public DanhGia( String tenTaiKhoan, int maSanPham, String tenSanPham, String danhGia, String nhanXet, String ngayDanhGia) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.danhGia = danhGia;
        this.nhanXet = nhanXet;
        this.ngayDanhGia = ngayDanhGia;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getMaDanhGia() {
        return maDanhGia;
    }

    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public String getDanhGia() {
        return danhGia;
    }

    public String getNhanXet() {
        return nhanXet;
    }

    public void setMaDanhGia(int maDanhGia) {
        this.maDanhGia = maDanhGia;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }

    public void setNhanXet(String nhanXet) {
        this.nhanXet = nhanXet;
    }

    public void setNgayDanhGia(String ngayDanhGia) {
        this.ngayDanhGia = ngayDanhGia;
    }

    public String getNgayDanhGia() {
        return ngayDanhGia;
    }
}

