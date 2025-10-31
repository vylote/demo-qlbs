<%-- /index.jsp (LAYOUT CHÍNH) --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <%-- 1. Gọi Header (chứa <head>, <title>, link CSS...) --%>
    <jsp:include page="common/header.jsp" />
</head>
<body>

<div class="page-container">

    <div class="content-wrap">

        <%-- 2. Gọi Menu Trái (Sidebar) --%>
        <jsp:include page="common/menu.jsp" />

        <%-- 3. NỘI DUNG CHÍNH (PHẦN THAY ĐỔI) --%>
        <main class="main-content">

            <%--
              ĐÂY LÀ "LỖ HỔNG" (RenderBody)
              Nó sẽ render file JSP nào mà Servlet chỉ định qua biến "bodyView"
            --%>
            <c:if test="${not empty bodyView}">
                <jsp:include page="${bodyView}" />
            </c:if>

            <%-- Nếu không có bodyView nào, hiển thị trang chào mừng --%>
            <c:if test="${empty bodyView}">
                <h2>Chào mừng đến với trang quản trị!</h2>
            </c:if>

        </main> <%-- Kết thúc .main-content --%>

    </div> <%-- Kết thúc .content-wrap --%>

    <%-- 4. Gọi Footer --%>
    <jsp:include page="common/footer.jsp" />

</div> <%-- Kết thúc .page-container --%>

</body>
</html>