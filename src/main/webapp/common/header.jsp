<%-- /common/header.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<meta charset="UTF-8">
<title>Quản Lý Bán Sách</title>

<c:set var="root" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${root}/css/style.css">
<c:if test="${not empty pageSpecificCSS}">
    <link rel="stylesheet" href="${root}/${pageSpecificCSS}">
</c:if>

<header class="header">
    <a href="${root}/trangchu" class="logo">Website Bán Sách</a>
    <div class="header-search">
        <form id="search-form" action="filter_books" method="GET">
            <input type="text" id="search-keyword" name="search" placeholder="Tìm kiếm tên sách...">
            <button type="submit">Tìm</button>
        </form>
    </div>
    <div class="header-right">
        <a href="${root}/trangchu">Home</a>
        <c:forEach items="${dsTheLoai}" var="tl">
            <a href="#" class="filter-link" data-id="${tl.maTheLoai}">${tl.tenTheLoai}</a>
        </c:forEach>
        <a href="${root}/login.jsp">Login</a>
    </div>
</header>