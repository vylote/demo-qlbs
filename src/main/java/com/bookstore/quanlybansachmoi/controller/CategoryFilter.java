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
    public void init(FilterConfig filterConfig) {
        theLoaiDAO = new TheLoaiDAO();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        List<TheLoai> listTheLoai = theLoaiDAO.getAllTheLoai();
        request.setAttribute("dsTheLoai", listTheLoai);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // (Có thể để trống)
    }
}