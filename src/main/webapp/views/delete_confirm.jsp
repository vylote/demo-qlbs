<%-- /views/delete_confirm.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%--
  BÁO CHO LAYOUT BIẾT: "Tôi cần file css/delete_confirm.css!"
--%>
<c:set var="pageSpecificCSS" value="css/delete_confirm.css" scope="request" />

<form action="delete" method="post" class="content-form">

    <h2 class="delete-title">Xác Nhận Xóa Sách</h2>

    <p class="delete-warning">
        Bạn có chắc chắn muốn xóa vĩnh viễn cuốn sách này không?
        Hành động này không thể hoàn tác.
    </p>

    <div class="form-group">
        <label>Mã Sách</label>
        <input type="text" name="maSach" value="${sachCanXoa.maSach}" readonly>
    </div>

    <div class="form-group">
        <label>Tên Sách:</label>
        <input type="text" name="tenSach" value="${sachCanXoa.tenSach}" readonly>
    </div>

    <div class="form-group">
        <label>Mô Tả:</label>
        <textarea name="moTa" rows="5" readonly>${sachCanXoa.moTa}</textarea>
    </div>

    <div class="form-group">
        <label>Đơn Giá:</label>
        <input type="number" name="donGia" value="${sachCanXoa.donGia}" readonly>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-danger">Xác nhận xóa</button>
        <a href="trangchu" class="btn btn-secondary">Hủy bỏ</a>
    </div>

</form>