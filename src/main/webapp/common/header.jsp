<%-- /common/header.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<c:set var="root" value="${pageContext.request.contextPath}" scope="request" />
<meta charset="UTF-8">
<title>Quản Lý Bán Sách</title>

<%-- 1. TẢI BOOTSTRAP CSS (THÊM VÀO ĐÂY) --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<%-- (Optional) Link icon của Bootstrap --%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

<link rel="stylesheet" href="${root}/css/style.css">

<c:if test="${not empty pageSpecificCSS}">
    <link rel="stylesheet" href="${root}/${pageSpecificCSS}">
</c:if>

<header class="header">
    <a href="${root}/trangchu" class="logo">Website Bán Sách</a>
    <%-- Form Tìm kiếm (cho User) --%>
    <div classs="header-search">
        <form id="search-form" action="${root}/search" method="GET" class="d-flex gap-2">
            <input type="text" id="search-keyword" name="search" class="form-control" placeholder="Tìm kiếm tên sách...">
            <button type="submit" class="btn btn-primary">
                <i class="bi bi-search"></i>
            </button>
        </form>
    </div>
    <div class="header-right">
        <a href="${root}/trangchu">Home</a>
        <%--<c:forEach items="${dsTheLoai}" var="tl">
            <a href="#" class="filter-link" data-id="${tl.maTheLoai}">${tl.tenTheLoai}</a>
        </c:forEach>--%>
        <a href="${root}/login.jsp">Login</a>
    </div>
</header>