<%-- /views/partials/book_table.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%--
  File này chỉ chứa BẢNG.
  Nó dùng biến "danhSachSach" mà Servlet gửi qua
--%>
<table>
    <thead>
    <tr>
        <th>Mã Sách</th>
        <th>Tên Sách</th>
        <th>Ảnh Bìa</th>
        <th>Đơn Giá</th>
        <th>Số Lượng Tồn</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${danhSachSach}" var="s">
        <tr>
            <td>${s.maSach}</td>
            <td>${s.tenSach}</td>
            <td>
                <c:choose>
                    <c:when test="${not empty s.anhBia}">
                        <img src="${pageContext.request.contextPath}/${s.anhBia}" alt="Ảnh bìa" width="80" height="100" />
                    </c:when>
                    <c:otherwise>
                        <img src="${pageContext.request.contextPath}/images/no-image.png" alt="Không có ảnh" width="80" height="100" />
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${s.donGia} VNĐ</td>
            <td>${s.soLuongTon}</td>
            <td>
                <a href="edit?id=${s.maSach}">Sửa</a> |
                <a href="delete?id=${s.maSach}">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="pagination">
    <c:forEach begin="1" end="${totalPages}" var="i">
        <c:choose>
            <%-- Nếu là trang hiện tại, thì "disable" nó đi --%>
            <c:when test="${i == currentPage}">
                <a href="#" class="page-link active">${i}</a>
            </c:when>
            <%-- Nếu là trang khác, tạo link AJAX --%>
            <c:otherwise>
                <%--
                  Link này chứa class "page-link" để JS bắt
                  và chứa data-page, data-search, data-category
                --%>
                <a href="#"
                   class="page-link"
                   data-page="${i}"
                   data-search="${currentSearch}"
                   data-category="${currentCategory}">
                        ${i}
                </a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>