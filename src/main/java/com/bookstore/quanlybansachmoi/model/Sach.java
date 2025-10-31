package com.bookstore.quanlybansachmoi.model;

public class Sach {
    private int maSach;
    private String tenSach;
    private String moTa;
    private double donGia;
    private int soLuongTon;
    private int maTheLoai;
    private int maNXB;
    private String anhBia;

    public Sach() {}

    public Sach(int maSach, String tenSach, String moTa, double donGia,
                int soLuongTon, int maTheLoai, int maNXB, String anhBia) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.moTa = moTa;
        this.donGia = donGia;
        this.soLuongTon = soLuongTon;
        this.maTheLoai = maTheLoai;
        this.maNXB = maNXB;
        this.anhBia = anhBia;
    }

    // Getters và Setters
    public int getMaSach() { return maSach; }
    public void setMaSach(int maSach) { this.maSach = maSach; }

    public String getTenSach() { return tenSach; }
    public void setTenSach(String tenSach) { this.tenSach = tenSach; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    public int getSoLuongTon() { return soLuongTon; }
    public void setSoLuongTon(int soLuongTon) { this.soLuongTon = soLuongTon; }

    public int getMaTheLoai() { return maTheLoai; }
    public void setMaTheLoai(int maTheLoai) { this.maTheLoai = maTheLoai; }

    public int getMaNXB() { return maNXB; }
    public void setMaNXB(int maNXB) { this.maNXB = maNXB; }

    public String getAnhBia() { return anhBia; }  // 🆕
    public void setAnhBia(String anhBia) { this.anhBia = anhBia; }  // 🆕
}
