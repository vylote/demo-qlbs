<%-- /views/home_content.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%--
  BÁO CHO LAYOUT BIẾT: "Tôi cần file css/home_content.css!"
--%>
<c:set var="pageSpecificCSS" value="css/home_content.css" scope="request" />

<h2>Danh Sách Sách</h2>

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
    <%-- Biến "danhSachSach" này do HomeServlet cung cấp --%>
    <c:forEach items="${danhSachSach}" var="s">
        <tr>
            <td>${s.maSach}</td>
            <td>${s.tenSach}</td>
            <td>
                <c:choose>
                    <c:when test="${not empty s.anhBia}">
                        <img src="${pageContext.request.contextPath}/${s.anhBia}"
                             alt="Ảnh bìa" width="80" height="100"
                             onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/images/no-image.png';" />
                    </c:when>
                    <c:otherwise>
                        <img src="${pageContext.request.contextPath}/images/no-image.png"
                             alt="Không có ảnh" width="80" height="100" />
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${s.donGia} VNĐ</td>
            <td>${s.soLuongTon}</td>
            <td>
                <a href="edit?id=${s.maSach}">Sửa</a> | <a href="delete?id=${s.maSach}">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>