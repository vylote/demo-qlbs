package com.bookstore.quanlybansachmoi.model;

import java.util.List;

public class TheLoai {
    private int maTheLoai;
    private String tenTheLoai;

    // 2. THÊM TRƯỜNG MỚI ĐỂ "CHỨA" SÁCH
    //    (Trường này không có trong CSDL, chỉ dùng trong Java)
    private List<Sach> sachList;

    // (Code được tự động sinh)
    public TheLoai() {
    }

    // (Code được tự động sinh)
    public TheLoai(int maTheLoai, String tenTheLoai) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
    }

    // (Code được tự động sinh)
    public int getMaTheLoai() {
        return maTheLoai;
    }
    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }
    public String getTenTheLoai() {
        return tenTheLoai;
    }
    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    // 3. THÊM GETTER VÀ SETTER MỚI (DÙNG CHO KỆ SÁCH)
    //    (HomeServlet sẽ gọi hàm setSachList này)
    public List<Sach> getSachList() {
        return sachList;
    }
    public void setSachList(List<Sach> sachList) {
        this.sachList = sachList;
    }
}
