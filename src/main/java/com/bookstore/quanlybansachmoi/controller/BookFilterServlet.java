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

// URL này dùng riêng cho AJAX
@WebServlet(urlPatterns = {"/filter_books"})
public class BookFilterServlet extends HttpServlet {

    private SachDAO sachDAO;
    private static final int PAGE_SIZE = 3;

    @Override
    public void init() {
        sachDAO = new SachDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. Lấy tất cả tham số từ AJAX
        String maTheLoai = request.getParameter("id");
        String keyword = request.getParameter("search");
        String pageStr = request.getParameter("page");

        int pageNumber = 1; // Mặc định là trang 1
        if (pageStr != null) {
            pageNumber = Integer.parseInt(pageStr);
        }

        // 2. Gọi DAO để lấy danh sách đã lọc
        int totalBooks = sachDAO.countSach(maTheLoai, keyword);
        List<Sach> list = sachDAO.getSach(maTheLoai, keyword, pageNumber, PAGE_SIZE);

        int totalPages = (int) Math.ceil((double) totalBooks / PAGE_SIZE);
        // 3. Đặt danh sách vào request
        request.setAttribute("danhSachSach", list);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("currentSearch", keyword); // Giữ lại từ khóa tìm kiếm
        request.setAttribute("currentCategory", maTheLoai);
        // 4. Forward đến "PARTIAL VIEW" (chỉ cái bảng)
        //    KHÔNG forward đến index.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/partials/book_table.jsp");
        dispatcher.forward(request, response);
    }
}