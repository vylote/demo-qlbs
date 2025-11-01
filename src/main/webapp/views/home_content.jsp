<%-- /views/home_content.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%--
  BÁO CHO LAYOUT BIẾT: "Tôi cần file css/home_content.css!"
--%>
<c:set var="pageSpecificCSS" value="css/home_content.css" scope="request" />

<h2>Danh Sách Sách</h2>

<div id="book-list-container">
    <jsp:include page="partials/book_table.jsp" />
</div>