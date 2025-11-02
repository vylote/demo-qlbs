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

@WebServlet(urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet {

    private SachDAO sachDAO;

    @Override
    public void init() { sachDAO = new SachDAO(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Sach sach = sachDAO.getSachByID(id);
        request.setAttribute("sachCanXoa", sach);
        request.setAttribute("bodyView", "views/delete_confirm.jsp");
        request.setAttribute("pageSpecificCSS", "css/delete_confirm.css");
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy MaSach từ form (input readonly) gửi lên
        String maSach = request.getParameter("maSach");

        // 2. Gọi DAO để thực thi lệnh DELETE (dùng hàm có Transaction)
        sachDAO.deleteSach(maSach);

        // 3. Chuyển hướng (redirect) người dùng về trang danh sách
        response.sendRedirect("trangchu");
    }
}
