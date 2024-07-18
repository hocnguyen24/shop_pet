package com.example.pet_pet.Model;

public class NguoiDung {
    private int maTaiKhoan;
    private String tenDangNhap;
    private String matKhau;
    private String hoTen;
    private String email;
    private String soDienThoai;
    private String diaChi;
    private int soTien;
    private String loaiTaiKhoan;
    private String anhnguoidung;

//    public NguoiDung(int maTaiKhoan, String tenDangNhap, String matKhau, String hoTen, String email, String soDienThoai, String diaChi, int soTien, String loaiTaiKhoan) {
//        this.maTaiKhoan = maTaiKhoan;
//        this.tenDangNhap = tenDangNhap;
//        this.matKhau = matKhau;
//        this.hoTen = hoTen;
//        this.email = email;
//        this.soDienThoai = soDienThoai;
//        this.diaChi = diaChi;
//        this.soTien = soTien;
//        this.loaiTaiKhoan = loaiTaiKhoan;
//    }

    public NguoiDung(String tenDangNhap, String matKhau, String hoTen, String email, String soDienThoai, String diaChi, int soTien, String loaiTaiKhoan,String anhnguoidung) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.soTien = soTien;
        this.loaiTaiKhoan = loaiTaiKhoan;
        this.anhnguoidung = anhnguoidung;
    }

    public NguoiDung() {
    }

    public String getAnhnguoidung() {
        return anhnguoidung;
    }

    public void setAnhnguoidung(String anhnguoidung) {
        this.anhnguoidung = anhnguoidung;
    }

    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public String getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(String loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }
}
