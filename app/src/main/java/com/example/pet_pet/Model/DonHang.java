package com.example.pet_pet.Model;


public class DonHang {
    private int maDonHang;
    private int maTaiKhoan;
    private String tenTaiKhoan;

    private String ngayDatHang;
    private int tongTien;
    private String trangthai;

    public DonHang(int maDonHang, String trangthai) {
        this.maDonHang = maDonHang;
        this.trangthai = trangthai;
    }

    public DonHang() {
    }

    public DonHang(int maTaiKhoan, String ngayDatHang, int tongTien, String trangthai) {
        this.maTaiKhoan = maTaiKhoan;
        this.ngayDatHang = ngayDatHang;
        this.tongTien = tongTien;
        this.trangthai = trangthai;
    }

    public DonHang(int maDonHang, int maTaiKhoan, String tenTaiKhoan, String ngayDatHang, int tongTien, String trangthai) {
        this.maDonHang = maDonHang;
        this.maTaiKhoan = maTaiKhoan;
        this.tenTaiKhoan = tenTaiKhoan;
        this.ngayDatHang = ngayDatHang;
        this.tongTien = tongTien;
        this.trangthai = trangthai;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(String ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
