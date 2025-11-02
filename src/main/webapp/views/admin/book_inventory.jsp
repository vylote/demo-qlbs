<%-- /views/admin/book_inventory.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<h2 class="text-center my-4">Quản Lý Kho Sách (Admin)</h2>

<%--
  PHẦN 1: FORM TÌM KIẾM (Cho Admin)
--%>
<div class="row justify-content-center mb-4">
    <div class="col-md-8">
        <form id="search-form-admin" action="${root}/filter-books" method="GET" class="d-flex gap-2">
            <input type="text" id="search-keyword-admin" name="search" class="form-control" placeholder="Tìm kiếm tên sách...">
            <button type="submit" class="btn btn-info">
                <i class="bi bi-search"></i> Tìm (Admin)
            </button>
        </form>
    </div>
</div>

<%--
  PHẦN 2: CONTAINER (KHUNG CHỨA)
  Thêm "data-servlet-url" để JavaScript "thông minh" biết gọi Servlet nào
--%>
<div class="ajax-load-container" data-servlet-url="filter-books">
    <%--
      Tải nội dung ban đầu (trang 1) do AdminStockServlet cung cấp
    --%>
    <jsp:include page="views/partials/admin_table.jsp" />
</div>