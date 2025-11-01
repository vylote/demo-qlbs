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

// URL này phải khớp với link trong index.jsp
@WebServlet(urlPatterns = {"/edit"})
public class EditServlet extends HttpServlet {

    private SachDAO sachDAO;

    @Override
    public void init() {
        sachDAO = new SachDAO();
    }

    // Phương thức GET dùng để TẢI dữ liệu lên form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");

        Sach sach = sachDAO.getSachByID(id);

        request.setAttribute("sachCanSua", sach);

        request.setAttribute("bodyView", "views/edit_form.jsp");

        request.setAttribute("pageSpecificCSS", "css/edit_form.css");

        // 4. Chuyển tiếp (forward) sang LAYOUT CHÍNH (index.jsp)
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    // Chúng ta sẽ thêm doPost ở bước 6

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Thiết lập UTF-8 để nhận tiếng Việt
        request.setCharacterEncoding("UTF-8");

        // 1. Lấy tất cả thông tin từ form edit_form.jsp gửi lên
        String maSach = request.getParameter("maSach");
        String tenSach = request.getParameter("tenSach");
        String moTa = request.getParameter("moTa");
        String donGiaStr = request.getParameter("donGia");
        String soLuongTonStr = request.getParameter("soLuongTon");

        // 2. Ép kiểu dữ liệu
        double donGia = 0;
        int soLuongTon = 0;
        try {
            donGia = Double.parseDouble(donGiaStr);
            soLuongTon = Integer.parseInt(soLuongTonStr);
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Xử lý lỗi nếu người dùng nhập không phải số
        }

        // 3. Gọi DAO để thực thi UPDATE
        sachDAO.updateSach(maSach, tenSach, moTa, donGia, soLuongTon);

        // 4. Chuyển hướng (redirect) người dùng về trang danh sách
        //    URL "trangchu" phải khớp với @WebServlet của HomeServlet
        response.sendRedirect("trangchu");
    }
}