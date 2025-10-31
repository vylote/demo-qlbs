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

    public void init() {
        sachDAO = new SachDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Sach> list = sachDAO.getAllSach();
        request.setAttribute("danhSachSach", list);

        // 2. Chỉ định "Ruột" (Body) cần render
        request.setAttribute("bodyView", "views/home_content.jsp");

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}