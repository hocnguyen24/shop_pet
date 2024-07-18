package com.example.pet_pet.Model;

public class DonHangChiTiet {
    private int maChiTietDonHang;
    private int maDonHang;
    private int maSanPham;
    private String tenSanPham;
    private int soLuong;
    private int donGia;
    private int thanhTien;
    private String anhsanpham;

    public String getAnhsanpham() {
        return anhsanpham;
    }

    public void setAnhsanpham(String anhsanpham) {
        this.anhsanpham = anhsanpham;
    }

    public DonHangChiTiet() {
    }
//    orderId, gioHang.getMaSanPham(), gioHang.getSoLuongMua(), sanPham.getGia(), gioHang.getSoLuongMua() * sanPham.getGia()

    public DonHangChiTiet(int maDonHang, int maSanPham, int soLuong, int donGia, int thanhTien) {
        this.maDonHang = maDonHang;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public DonHangChiTiet(int maSanPham, String tenSanPham, int soLuong) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
    }

    public DonHangChiTiet(int maChiTietDonHang, int maDonHang, int maSanPham, String tenSanPham, int soLuong, int donGia, int thanhTien) {
        this.maChiTietDonHang = maChiTietDonHang;
        this.maDonHang = maDonHang;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getMaChiTietDonHang() {
        return maChiTietDonHang;
    }

    public void setMaChiTietDonHang(int maChiTietDonHang) {
        this.maChiTietDonHang = maChiTietDonHang;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }
}
