package com.bookstore.quanlybansachmoi.controller;

import com.bookstore.quanlybansachmoi.dao.SachDAO;
import com.bookstore.quanlybansachmoi.model.Sach;
import com.bookstore.quanlybansachmoi.model.TheLoai;
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
    private static final int CAROUSEL_BOOK_COUNT = 12;

    @Override
    public void init() { sachDAO = new SachDAO(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy 12 cuốn sách cho Carousel
        List<Sach> carouselBooks = sachDAO.getSach(null, null, 1, CAROUSEL_BOOK_COUNT);
        request.setAttribute("carouselBooks", carouselBooks); // Đặt tên mới

        // 2. Lấy dữ liệu cho Kệ Sách (Phần dưới, nếu có)
        //    (Phần này giữ nguyên, lấy 5 cuốn cho mỗi thể loại)
        List<TheLoai> categories = (List<TheLoai>) request.getAttribute("dsTheLoai");
        if (categories != null) {
            for (TheLoai tl : categories) {
                tl.setSachList(sachDAO.getSach(String.valueOf(tl.getMaTheLoai()), null, 1, 5));
            }
        }

        // 3. Chỉ định "Ruột" (Body) LÀ TẤM 1
        request.setAttribute("bodyView", "views/home_landing.jsp");

        // 4. Chỉ định CSS riêng cho Tấm 1
        request.setAttribute("pageSpecificCSS", "css/home_landing.css");

        // 5. Forward đến LAYOUT CHÍNH
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}