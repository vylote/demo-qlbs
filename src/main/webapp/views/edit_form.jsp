<%-- /views/edit_form.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<%--
  BÁO CHO LAYOUT BIẾT: "Tôi cần file css/edit_form.css!"
  (File CSS này bây giờ sẽ rất nhỏ, chỉ để ghi đè)
--%>
<c:set var="pageSpecificCSS" value="css/edit_form.css" scope="request" />

<%--
  Sử dụng hệ thống lưới (grid) và card của Bootstrap
  để tạo layout cho form
--%>
<div class="row justify-content-center">
  <div class="col-md-8"> <%-- Giới hạn độ rộng của form là 8 cột --%>

    <%-- Dùng Card (thẻ) với viền màu xanh (border-primary) --%>
    <div class="card shadow-sm border-primary">

      <%-- Header của Card (nền xanh, chữ trắng) --%>
      <div class="card-header bg-primary text-white">
        <h2 class="h4 mb-0"><i class="bi bi-pencil-square"></i> Chỉnh Sửa Thông Tin Sách</h2>
      </div>

      <div class="card-body">
        <form action="edit" method="post">

          <%--
            Sử dụng class "mb-3" (margin-bottom 3)
            Sử dụng "form-label" cho <label>
            Sử dụng "form-control" cho <input>
          --%>
          <div class="mb-3">
            <label for="maSach" class="form-label">Mã Sách (Không thể sửa)</label>
            <input type="text" id="maSach" name="maSach" class="form-control"
                   value="${sachCanSua.maSach}" readonly>
          </div>

          <div class="mb-3">
            <label for="tenSach" class="form-label">Tên Sách:</label>
            <input type="text" id="tenSach" name="tenSach" class="form-control"
                   value="${sachCanSua.tenSach}">
          </div>

          <div class="mb-3">
            <label for="moTa" class="form-label">Mô Tả:</label>
            <textarea id="moTa" name="moTa" rows="5" class="form-control">${sachCanSua.moTa}</textarea>
          </div>

          <%-- Dùng row và col để chia 2 cột cho Đơn Giá và Số Lượng --%>
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="donGia" class="form-label">Đơn Giá:</label>
              <input type="number" id="donGia" step="1000" name="donGia" class="form-control"
                     value="${sachCanSua.donGia}">
            </div>
            <div class="col-md-6 mb-3">
              <label for="soLuongTon" class="form-label">Số Lượng Tồn:</label>
              <input type="number" id="soLuongTon" name="soLuongTon" class="form-control"
                     value="${sachCanSua.soLuongTon}">
            </div>
          </div>

          <hr>

          <%-- Dùng "text-end" để căn lề phải các nút --%>
          <div class="text-end">
            <button type="submit" class="btn btn-primary">
              <i class="bi bi-check-circle"></i> Lưu Cập Nhật
            </button>
            <a href="${root}/trangchu" class="btn btn-secondary">
              <i class="bi bi-x-circle"></i> Hủy bỏ
            </a>
          </div>

        </form>
      </div> <%-- Hết card-body --%>
    </div> <%-- Hết card --%>
  </div> <%-- Hết col-md-8 --%>
</div> <%-- Hết row --%>