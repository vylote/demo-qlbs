package com.bookstore.quanlybansachmoi.dao;

import com.bookstore.quanlybansachmoi.context.DBContext;
import com.bookstore.quanlybansachmoi.model.TheLoai;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class TheLoaiDAO {

    public List<TheLoai> getAllTheLoai() {
        List<TheLoai> list = new ArrayList<>();
        String query = "SELECT * FROM THELOAI";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Giả sử class TheLoai có constructor (maTheLoai, tenTheLoai)
                list.add(new TheLoai(rs.getInt("MaTheLoai"),
                        rs.getString("TenTheLoai")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}