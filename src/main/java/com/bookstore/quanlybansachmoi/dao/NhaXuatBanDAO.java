package com.bookstore.quanlybansachmoi.dao;

import com.bookstore.quanlybansachmoi.context.DBContext;
import com.bookstore.quanlybansachmoi.model.NhaXuatBan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhaXuatBanDAO {

    public List<NhaXuatBan> getAllNXB() {
        List<NhaXuatBan> list = new ArrayList<>();
        String query = "SELECT * FROM NHAXUATBAN";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new NhaXuatBan(rs.getInt("MaNXB"),
                        rs.getString("TenNXB")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}