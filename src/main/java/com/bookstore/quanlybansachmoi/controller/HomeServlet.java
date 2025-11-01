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

@WebServlet(name = "HomeServlet", value = "/trangchu")
public class HomeServlet extends HttpServlet {

    private SachDAO sachDAO;
    private static final int PAGE_SIZE = 3;

    public void init() {
        sachDAO = new SachDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. Lấy dữ liệu (Trang 1, không lọc)
        int totalBooks = sachDAO.countSach(null, null);
        List<Sach> list = sachDAO.getSach(null, null, 1, PAGE_SIZE);
        int totalPages = (int) Math.ceil((double) totalBooks / PAGE_SIZE);


        request.setAttribute("danhSachSach", list);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", 1); // Luôn là trang 1

        // 2. Chỉ định "Ruột" (Body) cần render
        request.setAttribute("bodyView", "views/home_content.jsp");
        request.setAttribute("pageSpecificCSS", "css/home_content.css");

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}