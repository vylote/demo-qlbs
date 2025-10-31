package com.bookstore.quanlybansachmoi.dao;

import com.bookstore.quanlybansachmoi.context.DBContext;
import com.bookstore.quanlybansachmoi.model.Sach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SachDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Sach> getAllSach() {
        List<Sach> list = new ArrayList<>();
        String query = "SELECT * FROM SACH";
        try {
            conn = DBContext.getConnection();
            assert conn != null;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Sach s = new Sach(
                        rs.getInt("MaSach"),
                        rs.getString("TenSach"),
                        rs.getString("MoTa"),
                        rs.getDouble("DonGia"),
                        rs.getInt("SoLuongTon"),
                        rs.getInt("MaTheLoai"),
                        rs.getInt("MaNXB"),
                        rs.getString("AnhBia")
                );
                list.add(s);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Bên trong class SachDAO.java, thêm phương thức mới:

    public Sach getSachByID(String id) {
        String query = "SELECT * FROM SACH WHERE MaSach = ?";
        try {
            conn = DBContext.getConnection(); // Mở kết nối
            ps = conn.prepareStatement(query);
            ps.setString(1, id); // Truyền ID vào dấu ?
            rs = ps.executeQuery();

            if (rs.next()) { // Chỉ mong đợi 1 kết quả
                Sach s = new Sach(
                        rs.getInt("MaSach"),
                        rs.getString("TenSach"),
                        rs.getString("MoTa"),
                        rs.getDouble("DonGia"),
                        rs.getInt("SoLuongTon"),
                        rs.getInt("MaTheLoai"),
                        rs.getInt("MaNXB"),
                        rs.getString("AnhBia")
                );
                conn.close(); // Đóng kết nối
                return s;
            }
            conn.close(); // Đóng kết nối
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }
    // Thêm phương thức này vào SachDAO.java

    public void updateSach(String maSach, String tenSach, String moTa, double donGia, int soLuongTon) {
        String query = "UPDATE SACH " +
                "SET TenSach = ?, MoTa = ?, DonGia = ?, SoLuongTon = ? " +
                "WHERE MaSach = ?";
        try {
            conn = DBContext.getConnection(); // Mở kết nối
            ps = conn.prepareStatement(query);

            // Truyền tham số
            ps.setString(1, tenSach);
            ps.setString(2, moTa);
            ps.setDouble(3, donGia);
            ps.setInt(4, soLuongTon);
            ps.setString(5, maSach); // MaSach cho mệnh đề WHERE

            ps.executeUpdate(); // Thực thi lệnh UPDATE

            conn.close(); // Đóng kết nối
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSach(String maSach) {
        String query_ChiTiet = "DELETE FROM CHITIET_DONHANG WHERE MaSach = ?";
        String query_Sach = "DELETE FROM SACH WHERE MaSach = ?";
        String query_Sach_TacGia = "DELETE FROM SACH_TACGIA WHERE MaSach = ?";
        PreparedStatement ps_ChiTiet = null;
        PreparedStatement ps_Sach = null;
        PreparedStatement ps_Sach_TacGia = null;
        try {
            conn = DBContext.getConnection();
            // --- BẮT ĐẦU TRANSACTION ---
            // Tắt chế độ tự động commit
            conn.setAutoCommit(false);

            // 3. Thực thi xóa CHITIET_DONHANG trước
            ps_ChiTiet = conn.prepareStatement(query_ChiTiet);
            ps_ChiTiet.setString(1, maSach);
            ps_ChiTiet.executeUpdate();

            ps_Sach_TacGia = conn.prepareStatement(query_Sach_TacGia);
            ps_Sach_TacGia.setString(1, maSach);
            ps_Sach_TacGia.executeUpdate();

            // 4. Thực thi xóa SACH (Chỉ chạy nếu bước 3 thành công)
            ps_Sach = conn.prepareStatement(query_Sach);
            ps_Sach.setString(1, maSach);
            ps_Sach.executeUpdate();

            // --- KẾT THÚC TRANSACTION ---
            // Nếu cả hai lệnh chạy thành công, commit thay đổi
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            // Dọn dẹp tài nguyên và bật lại AutoCommit
            try {
                if (ps_ChiTiet != null) ps_ChiTiet.close();
                if (ps_Sach != null) ps_Sach.close();
                if (ps_Sach_TacGia != null) ps_Sach_TacGia.close();
                if (conn != null) {
                    conn.setAutoCommit(true); // Bật lại AutoCommit
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}