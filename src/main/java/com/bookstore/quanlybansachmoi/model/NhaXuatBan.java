package com.bookstore.quanlybansachmoi.model;

public class NhaXuatBan {
    private int maNXB;
    private String tenNXB;

    public NhaXuatBan(int maNXB, String tenNXB) {
        this.maNXB = maNXB;
        this.tenNXB = tenNXB;
    }

    // (Dùng Alt+Insert -> Getters and Setters)
    public int getMaNXB() { return maNXB; }
    public void setMaNXB(int maNXB) { this.maNXB = maNXB; }
    public String getTenNXB() { return tenNXB; }
    public void setTenNXB(String tenNXB) { this.tenNXB = tenNXB; }
}
