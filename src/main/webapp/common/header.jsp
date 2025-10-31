<%-- /common/header.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<meta charset="UTF-8">
<title>Quản Lý Bán Sách</title>

<c:set var="root" value="${pageContext.request.contextPath}" />

<%-- 1. Tải CSS Chung (file gốc của bạn chứa header/footer/menu) --%>
<link rel="stylesheet" href="${root}/css/style.css">
<%-- ĐÂY LÀ "LỖ HỔNG" --%>
<c:if test="${not empty pageSpecificCSS}">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/${pageSpecificCSS}">
</c:if>
<%--
  HEADER (NAVBAR) VỚI DỮ LIỆU ĐỘNG (Phần này đã đúng)
--%>
<header class="header">
    <a href="${root}/trangchu" class="logo">Website Bán Sách</a>
    <div class="header-right">
        <a href="${root}/trangchu">Home</a>
        <c:forEach items="${dsTheLoai}" var="tl">
            <a href="${root}/category?id=${tl.maTheLoai}">${tl.tenTheLoai}</a>
        </c:forEach>
        <a href="${root}/login.jsp">Login</a>
    </div>
</header>