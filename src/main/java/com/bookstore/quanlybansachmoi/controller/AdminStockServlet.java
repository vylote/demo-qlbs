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
    private static final int PAGE_SIZE = 8; // (Sửa số lượng tùy ý)

    @Override
    public void init() { sachDAO = new SachDAO(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy dữ liệu (Trang 1, không lọc)
        int totalBooks = sachDAO.countSach(null, null, null);
        List<Sach> list = sachDAO.getSach(null, null, null,1, PAGE_SIZE);
        int totalPages = (int) Math.ceil((double) totalBooks / PAGE_SIZE);

        // 2. Đặt thuộc tính
        request.setAttribute("danhSachSach", list);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", 1);

        request.setAttribute("bodyView", "views/admin/book_inventory.jsp");
        request.setAttribute("pageSpecificCSS", "css/admin_table.css");
        // BÁO CHO JS BIẾT GỌI SERVLET NÀO KHI PHÂN TRANG
        request.setAttribute("ajaxServletUrl", "filter-books");

        // 5. Forward đến LAYOUT CHÍNH
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}