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
                        rs.getString("AnhBia"),
                        rs.getString("TenTheLoai")
                );
                list.add(s);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

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
                        rs.getString("AnhBia"),
                        rs.getString("TenTheLoai")
                );
                conn.close(); // Đóng kết nối
                return s;
            }
            conn.close(); // Đóng kết nối
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateSach(String maSach, String tenSach, String moTa, double donGia, int soLuongTon) {
        String query = "UPDATE SACH " +
                "SET TenSach = ?, MoTa = ?, DonGia = ?, SoLuongTon = ? " +
                "WHERE MaSach = ?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, tenSach);
            ps.setString(2, moTa);
            ps.setDouble(3, donGia);
            ps.setInt(4, soLuongTon);
            ps.setString(5, maSach);

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

    public List<Sach> getSachByTheLoai(String maTheLoai) {
        List<Sach> list = new ArrayList<>();
        String query = "SELECT * FROM SACH WHERE MaTheLoai=?";
        if (maTheLoai == null || maTheLoai.equals("all")) {
            query = "SELECT * FROM SACH";
        }
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);

            if (maTheLoai != null && !maTheLoai.equals("all")) {
                ps.setString(1, maTheLoai); // Gán ID thể loại vào dấu ?
            }
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
                        rs.getString("AnhBia"),
                        rs.getString("TenTheLoai")
                );
                list.add(s);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countSach(String maTheLoai, String keyword) {
        String query = "SELECT COUNT(*) FROM SACH WHERE 1=1";
        if (maTheLoai != null && !maTheLoai.isEmpty()) {
            query += " AND MaTheLoai = ?";
        }
        if (keyword != null && !keyword.isEmpty()) {
            query += " AND TenSach = ?";
        }
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);

            int paramIndex = 1;
            if (maTheLoai != null && !maTheLoai.isEmpty()) {
                ps.setString(paramIndex++, maTheLoai);
            }
            if (keyword != null && !keyword.isEmpty()) {
                ps.setString(paramIndex++, "%" + keyword + "%");
            }

            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // Trả về tổng số sách
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 2. HÀM LẤY SÁCH (CÓ PHÂN TRANG VÀ LỌC)
//    (Dùng OFFSET...FETCH... của SQL Server)
    public List<Sach> getSach(String maTheLoai, String keyword, int pageNumber, int pageSize) {
        List<Sach> list = new ArrayList<>();
        String query = "SELECT S.*, T.TenTheLoai " +
                "FROM SACH S " +
                "LEFT JOIN THELOAI T ON S.MaTheLoai = T.MaTheLoai " +
                "WHERE 1=1";

        if (maTheLoai != null && !maTheLoai.isEmpty()) {
            query += " AND S.MaTheLoai = ?";
        }
        if (keyword != null && !keyword.isEmpty()) {
            query += " AND S.TenSach LIKE ?";
        }

        query += " ORDER BY S.MaSach DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);

            int paramIndex = 1;
            if (maTheLoai != null && !maTheLoai.isEmpty()) {
                ps.setString(paramIndex++, maTheLoai);
            }
            if (keyword != null && !keyword.isEmpty()) {
                ps.setString(paramIndex++, "%" + keyword + "%");
            }

            // Gán tham số cho Phân trang
            ps.setInt(paramIndex++, (pageNumber - 1) * pageSize); // OFFSET
            ps.setInt(paramIndex++, pageSize); // FETCH NEXT

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
                        rs.getString("AnhBia"),
                        rs.getString("TenTheLoai")
                );
                list.add(s);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 1. Lấy sách MỚI NHẤT (cho Carousel)
//    (Dùng hàm getSach đã có, chỉ cần gọi nó với trang 1)
    public List<Sach> getTopNewestBooks(int limit) {
        // Gọi hàm getSach(theLoai, keyword, page, pageSize)
        // Sắp xếp theo MaSach DESC để lấy sách mới nhất

        // Tạm thời chúng ta dùng lại hàm getSach cũ (nó sắp xếp theo ASC)
        // (Để làm chuẩn, bạn cần sửa lại hàm getSach để cho phép ORDER BY DESC)
        return getSach(null, null, 1, limit);
    }

    // 2. Lấy Top 5 sách cho MỘT thể loại (cho Kệ sách)
    public List<Sach> getTopBooksByCategoryID(String maTheLoai, int limit) {
        // Gọi hàm getSach(theLoai, keyword, page, pageSize)
        return getSach(maTheLoai, null, 1, limit);
    }
}