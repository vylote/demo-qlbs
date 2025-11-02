<%-- /common/menu.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<nav class="sidebar">
    <h3>Danh Mục</h3>
    <%-- Link "Tất Cả" (cho User) --%>
    <a href="#" class="filter-link" data-id="">Tất Cả Sách</a>

    <%-- Lặp qua Thể Loại (do Filter cung cấp) --%>
    <c:forEach items="${dsTheLoai}" var="tl">
        <a href="#" class="filter-link" data-id="${tl.maTheLoai}">
                ${tl.tenTheLoai}
        </a>
    </c:forEach>

    <hr>
    <h3>Quản Trị</h3>
    <%-- Link đến trang "Kho Sách" của Admin --%>
    <a href="${root}/admin/kho-sach">Quản Lý Kho Sách</a>
    <a href="#">Quản lý NXB</a>
    <a href="#">Quản lý Tác Giả</a>
</nav>