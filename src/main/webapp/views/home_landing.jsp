<%-- /views/home_landing.jsp (TẤM 1 MỚI - ĐÃ FIX) --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<%-- BÁO CHO LAYOUT BIẾT CẦN TẢI CSS RIÊNG --%>
<c:set var="pageSpecificCSS" value="css/home_landing.css" scope="request" />

<%--
  PHẦN 1: CAROUSEL (FULL-WIDTH BACKGROUND)
--%>
<div class="carousel-wrapper"> <%-- Lớp bọc ngoài 100% (có nền màu) --%>
    <div class="container"> <%-- Lớp .container bên trong để "thụt lề" nội dung --%>

        <div id="bookCarousel" class="carousel slide" data-bs-ride="false">
            <div class="carousel-inner">

                <%-- Item 1 (Active) - Sách 1-4 --%>
                <div class="carousel-item active">
                    <div class="row g-4">
                        <c:forEach items="${carouselBooks}" var="sach" begin="0" end="3">
                            <div class="col-md-3">
                                <div class="card h-100 shadow-sm book-card">
                                    <img src="${root}/${sach.anhBia}" class="card-img-top book-card-img"
                                         onerror="this.onerror=null; this.src='${root}/images/no-image.png';">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title book-card-title">${sach.tenSach}</h5>
                                        <p class="card-text text-danger fw-bold mt-auto">
                                            <fmt:formatNumber value="${sach.donGia}" pattern="#,##0.##" /> VNĐ
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <%-- Item 2 - Sách 5-8 (ĐÃ SỬA) --%>
                <div class="carousel-item">
                    <div class="row g-4">
                        <c:forEach items="${carouselBooks}" var="sach" begin="4" end="7">
                            <div class="col-md-3">
                                    <%-- Dán code card đầy đủ vào đây --%>
                                <div class="card h-100 shadow-sm book-card">
                                    <img src="${root}/${sach.anhBia}" class="card-img-top book-card-img"
                                         onerror="this.onerror=null; this.src='${root}/images/no-image.png';">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title book-card-title">${sach.tenSach}</h5>
                                        <p class="card-text text-danger fw-bold mt-auto">
                                            <fmt:formatNumber value="${sach.donGia}" pattern="#,##0.##" /> VNĐ
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <%-- Item 3 - Sách 9-12 (ĐÃ SỬA) --%>
                <div class="carousel-item">
                    <div class="row g-4">
                        <c:forEach items="${carouselBooks}" var="sach" begin="8" end="11">
                            <div class="col-md-3">
                                    <%-- Dán code card đầy đủ vào đây --%>
                                <div class="card h-100 shadow-sm book-card">
                                    <img src="${root}/${sach.anhBia}" class="card-img-top book-card-img"
                                         onerror="this.onerror=null; this.src='${root}/images/no-image.png';">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title book-card-title">${sach.tenSach}</h5>
                                        <p class="card-text text-danger fw-bold mt-auto">
                                            <fmt:formatNumber value="${sach.donGia}" pattern="#,##0.##" /> VNĐ
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

            </div>

            <%-- NÚT ĐIỀU KHIỂN (PREV/NEXT) --%>
            <button class="carousel-control-prev" type="button" data-bs-target="#bookCarousel" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#bookCarousel" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
            </button>
        </div>

    </div> <%-- Hết .container --%>
</div> <%-- Hết .carousel-wrapper --%>
<%--
  PHẦN 2: LƯỚI SÁCH (LẪN LỘN)
  (Đặt trong .container để "thụt lề" hai bên)
--%>
<div class="container mt-5">
    <h2 class="text-center mb-4">Sách Nổi Bật</h2>

    <div class="row g-4">
        <%-- Lặp qua 8 cuốn sách đầu tiên (lấy từ carouselBooks) --%>
        <c:forEach items="${carouselBooks}" var="sach" begin="0" end="7">
            <div class="col-6 col-md-4 col-lg-3">
                <div class="card h-100 shadow-sm book-card">
                    <img src="${root}/${sach.anhBia}" class="card-img-top book-card-img"
                         onerror="this.onerror=null; this.src='${root}/images/no-image.png';">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title book-card-title">${sach.tenSach}</h5>
                        <p class="card-text text-danger fw-bold mt-auto">
                            <fmt:formatNumber value="${sach.donGia}" pattern="#,##0.##" /> VNĐ
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <%-- NÚT XEM THÊM (Kích hoạt AJAX) --%>
    <div class="text-center mt-5 mb-4">
        <a href="#" class="btn btn-outline-primary btn-lg filter-link" data-id="">
            Xem Thêm Tất Cả Sách
        </a>
    </div>
</div>