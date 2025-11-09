package com.bookstore.quanlybansachmoi.controller;

import com.bookstore.quanlybansachmoi.dao.NhaXuatBanDAO;
import com.bookstore.quanlybansachmoi.dao.SachDAO;
import com.bookstore.quanlybansachmoi.model.NhaXuatBan;
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
@WebServlet(urlPatterns = {"/admin/filter-books"})
public class BookFilterServlet extends HttpServlet {

    private SachDAO sachDAO;
    private NhaXuatBanDAO nxbDAO;
    private static final int PAGE_SIZE = 8;

    @Override
    public void init() {
        sachDAO = new SachDAO();
        nxbDAO = new NhaXuatBanDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. Lấy tất cả tham số từ AJAX
        String maTheLoai = request.getParameter("id");
        String keyword = request.getParameter("search");
        String maNXB = request.getParameter("maNXB");
        String pageStr = request.getParameter("page");
        int pageNumber = (pageStr != null) ? Integer.parseInt(pageStr) : 1;

        // 2. Gọi DAO
        int totalBooks = sachDAO.countSach(maTheLoai, keyword, maNXB);
        List<Sach> list = sachDAO.getSach(maTheLoai, keyword, maNXB, pageNumber, PAGE_SIZE);
        int totalPages = (int) Math.ceil((double) totalBooks / PAGE_SIZE);
        // 7. LẤY DỮ LIỆU CHO DROPDOWN NXB
        List<NhaXuatBan> listNXB = nxbDAO.getAllNXB();
        // 3. Đặt thuộc tính
        request.setAttribute("danhSachSach", list);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("currentSearch", keyword);
        request.setAttribute("currentCategory", maTheLoai);
        request.setAttribute("currentNXB", maNXB);
        request.setAttribute("dsNXB", listNXB);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/partials/admin_table.jsp");
        dispatcher.forward(request, response);
    }
}