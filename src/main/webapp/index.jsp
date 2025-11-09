<%-- /index.jsp (LAYOUT CHÍNH - ĐÃ SỬA) --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}" scope="request" />

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="common/header.jsp" />
</head>
<body>

<div class="page-container">
    <div class="content-wrap">
        <jsp:include page="common/menu.jsp" />
        <main class="main-content">

            <%--
              THỐNG NHẤT:
              Chỉ giữ lại "id" và các "data-" attribute
            --%>
            <div id="ajax-body-container"
                 data-servlet-url="${ajaxServletUrl}"
                 data-context-root="${root}">

                <c:if test="${not empty bodyView}">
                    <jsp:include page="${bodyView}" />
                </c:if>
                <c:if test="${empty bodyView}">
                    <h2>Chào mừng đến với trang quản trị!</h2>
                </c:if>
            </div>

        </main>
    </div>
    <jsp:include page="common/footer.jsp" />
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
<script src="${root}/js/filter-ajax.js"></script>
</body>
</html>