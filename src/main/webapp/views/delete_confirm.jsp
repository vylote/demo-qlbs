<%-- /views/delete_confirm.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%--
  BÁO CHO LAYOUT BIẾT: "Tôi cần file css/delete_confirm.css!"
--%>
<c:set var="pageSpecificCSS" value="css/delete_confirm.css" scope="request" />

<div class="row justify-content-center">
    <div class="col-md-8">

        <%-- Dùng Card (thẻ) với viền màu đỏ (border-danger) --%>
        <div class="card shadow-sm border-danger">

            <%-- Header của Card (nền đỏ, chữ trắng) --%>
            <div class="card-header bg-danger text-white">
                <h2 class="h4 mb-0"><i class="bi bi-exclamation-triangle-fill"></i> Xác Nhận Xóa Sách</h2>
            </div>

            <div class="card-body">
                <%-- Dùng component "alert" của Bootstrap cho thông báo --%>
                <div class="alert alert-danger">
                    Bạn có chắc chắn muốn xóa vĩnh viễn cuốn sách này không?
                    Hành động này không thể hoàn tác.
                </div>

                <form action="delete" method="post">
                    <%-- (Các trường readonly dùng "mb-3", "form-label", "form-control") --%>
                    <div class="mb-3">
                        <label for="maSach" class="form-label">Mã Sách</label>
                        <input type="text" id="maSach" name="maSach" class="form-control"
                               value="${sachCanXoa.maSach}" readonly>
                    </div>

                    <div class="mb-3">
                        <label for="tenSach" class="form-label">Tên Sách:</label>
                        <input type="text" id="tenSach" name="tenSach" class="form-control"
                               value="${sachCanXoa.tenSach}" readonly>
                    </div>

                    <hr>

                    <div class="text-end">
                        <%--
                          Sử dụng class "btn-danger" (nút đỏ)
                          và "btn-secondary" (nút xám)
                        --%>
                        <button type="submit" class="btn btn-danger">
                            <i class="bi bi-trash"></i> Xác nhận xóa
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