<%-- /views/partials/admin_table.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<%--
  PHẦN 1: BẢNG SÁCH (CÓ SỬA/XÓA)
--%>
<table class="table table-striped table-hover table-bordered align-middle">
    <thead class="table-dark">
    <tr>
        <th>Mã Sách</th>
        <th>Tên Sách</th>
        <th>Ảnh Bìa</th>
        <th>Đơn Giá</th>
        <th>Thể Loại</th>
        <th>SL Tồn</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${danhSachSach}" var="s">
        <tr>
            <td>${s.maSach}</td>
            <td>${s.tenSach}</td>
            <td><img src="${root}/${s.anhBia}" class="img-fluid rounded" style="width: 80px;" /></td>
            <td>${s.donGia} VNĐ</td>
            <td>${s.tenTheLoai}</td>
            <td>${s.soLuongTon}</td>
            <td>
                <a href="edit?id=${s.maSach}" class="btn btn-primary btn-sm">Sửa</a>
                <a href="delete?id=${s.maSach}" class="btn btn-danger btn-sm">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%--
  PHẦN 2: PHÂN TRANG (PAGINATION)
--%>
<nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
        <c:forEach begin="1" end="${totalPages}" var="i">
            <li class="page-item ${i == currentPage ? 'active' : ''}">
                <a href="#" class="page-link"
                   data-page="${i}"
                   data-search="${currentSearch}"
                   data-category="${currentCategory}">
                        ${i}
                </a>
            </li>
        </c:forEach>
    </ul>
</nav>