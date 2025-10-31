package com.bookstore.quanlybansachmoi.controller;

import com.bookstore.quanlybansachmoi.dao.TheLoaiDAO;
import com.bookstore.quanlybansachmoi.model.TheLoai;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.List;

// Annotation @WebFilter("/*") có nghĩa là "chặn TẤT CẢ các request"
@WebFilter("/*")
public class CategoryFilter implements Filter {

    private TheLoaiDAO theLoaiDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo DAO khi filter bắt đầu
        theLoaiDAO = new TheLoaiDAO();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 1. GỌI DAO ĐỂ LẤY DỮ LIỆU
        List<TheLoai> listTheLoai = theLoaiDAO.getAllTheLoai();

        // 2. "NHÉT" DỮ LIỆU VÀO REQUEST
        //    Để TẤT CẢ các file JSP đều có thể truy cập
        request.setAttribute("dsTheLoai", listTheLoai);

        // 3. CHO PHÉP REQUEST ĐI TIẾP
        //    (Đi tiếp đến HomeServlet, EditServlet, hoặc file JSP)
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // (Có thể để trống)
    }
}