package com.bookstore.quanlybansachmoi.controller;

import com.bookstore.quanlybansachmoi.dao.SachDAO;
import com.bookstore.quanlybansachmoi.model.Sach;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/kho-sach"})
public class AdminStockServlet extends HttpServlet {

    private SachDAO sachDAO;
    private static final int PAGE_SIZE = 3; // (Sửa số lượng tùy ý)

    @Override
    public void init() { sachDAO = new SachDAO(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy dữ liệu (Trang 1, không lọc)
        int totalBooks = sachDAO.countSach(null, null);
        List<Sach> list = sachDAO.getSach(null, null, 1, PAGE_SIZE);
        int totalPages = (int) Math.ceil((double) totalBooks / PAGE_SIZE);

        // 2. Đặt thuộc tính
        request.setAttribute("danhSachSach", list);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", 1);

        // 3. Chỉ định "Ruột" (Body) LÀ TRANG ADMIN
        request.setAttribute("bodyView", "views/admin/book_inventory.jsp");
        // 4. Chỉ định CSS LÀ CSS CỦA TRANG ADMIN
        request.setAttribute("pageSpecificCSS", "css/admin_table.css");

        // 5. Forward đến LAYOUT CHÍNH
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}