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

@WebServlet(urlPatterns = {"/search"}) // URL MỚI CHO USER
public class SearchServlet extends HttpServlet {

    private SachDAO sachDAO;
    private static final int PAGE_SIZE = 3; // (Sửa số lượng tùy ý)

    @Override
    public void init() { sachDAO = new SachDAO(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy tất cả tham số từ AJAX
        String maTheLoai = request.getParameter("id");
        String keyword = request.getParameter("search");
        String pageStr = request.getParameter("page");
        int pageNumber = (pageStr != null) ? Integer.parseInt(pageStr) : 1;

        // 2. Gọi DAO
        int totalBooks = sachDAO.countSach(maTheLoai, keyword);
        List<Sach> list = sachDAO.getSach(maTheLoai, keyword, pageNumber, PAGE_SIZE);
        int totalPages = (int) Math.ceil((double) totalBooks / PAGE_SIZE);

        // 3. Đặt thuộc tính
        request.setAttribute("danhSachSach", list);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("currentSearch", keyword);
        request.setAttribute("currentCategory", maTheLoai);

        // 4. Forward đến "TẤM 2" (View của User)
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/partials/search_results.jsp");
        dispatcher.forward(request, response);
    }
}