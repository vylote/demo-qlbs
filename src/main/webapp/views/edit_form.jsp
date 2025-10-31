<%-- /views/edit_form.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%--
  BÁO CHO LAYOUT BIẾT: "Tôi cần file css/edit_form.css!"
--%>
<c:set var="pageSpecificCSS" value="css/edit_form.css" scope="request" />

<form action="edit" method="post" class="content-form">

  <h2>Chỉnh Sửa Thông Tin Sách</h2>

  <div class="form-group">
    <label>Mã Sách (Không thể sửa)</label>
    <%-- Biến "sachCanSua" do EditServlet (hàm doGet) cung cấp --%>
    <input type="text" name="maSach" value="${sachCanSua.maSach}" readonly>
  </div>

  <div class="form-group">
    <label>Tên Sách:</label>
    <input type="text" name="tenSach" value="${sachCanSua.tenSach}">
  </div>

  <div class="form-group">
    <label>Mô Tả:</label>
    <textarea name="moTa" rows="5">${sachCanSua.moTa}</textarea>
  </div>

  <div class="form-group">
    <label>Đơn Giá:</label>
    <input type="number" step="1000" name="donGia" value="${sachCanSua.donGia}">
  </div>

  <div class="form-group">
    <label>Số Lượng Tồn:</label>
    <input type="number" name="soLuongTon" value="${sachCanSua.soLuongTon}">
  </div>

  <div class="form-actions">
    <%-- Áp dụng lớp .btn-primary (màu xanh) --%>
    <button type="submit" class="btn btn-primary">Lưu Cập Nhật</button>
    <a href="trangchu" class="btn btn-secondary">Hủy bỏ</a>
  </div>

</form>