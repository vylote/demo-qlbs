<%-- /views/partials/search_results.jsp (TẤM 2) --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<%-- Tự tạo biến root (Vì file này được AJAX gọi riêng) --%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<%-- BÁO CHO LAYOUT BIẾT CẦN TẢI CSS RIÊNG --%>
<c:set var="pageSpecificCSS" value="css/search_results.css" scope="request" />

<div class="container-fluid mt-4"> <%-- Dùng container-fluid để nó 100% bên trong .main-content --%>
    <div class="row">

        <%-- CỘT 1: BỘ LỌC (Filter Sidebar) --%>
        <div class="col-md-3">
            <div class="filter-sidebar">
                <h4 class="h5">Bộ Lọc</h4>
                <hr>
                <div class="mb-3">
                    <label class="form-label fw-bold">Theo Nhà Xuất Bản</label>
                    <select class="form-select">
                        <option selected>Tất cả NXB</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label fw-bold">Theo Mức Giá</label>
                    <select class="form-select">
                        <option selected>Tất cả mức giá</option>
                    </select>
                </div>
            </div>
        </div>

        <%-- CỘT 2: KẾT QUẢ TÌM KIẾM (Bảng Sách) --%>
        <div class="col-md-9">
            <h5 class="mb-3">Kết quả tìm kiếm cho: "${currentSearch}"</h5>

            <%-- PHẦN A: BẢNG (Bootstrap hóa) --%>
            <table class="table table-striped table-hover table-bordered align-middle">
                <thead class="table-light">
                <tr>
                    <th>Ảnh Bìa</th>
                    <th>Tên Sách</th>
                    <th>Đơn Giá</th>
                    <th>Thể Loại</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${danhSachSach}" var="s">
                    <tr>
                        <td style="width: 10%;"><img src="${root}/${s.anhBia}" class="img-fluid rounded" /></td>
                        <td style="width: 40%;">${s.tenSach}</td>
                        <td style="width: 25%;">${s.donGia} VNĐ</td>
                        <td style="width: 25%;">${s.tenTheLoai}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <%-- PHẦN B: PHÂN TRANG (Bootstrap hóa) --%>
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <li class="page-item ${i == currentPage ? 'active' : ''}">
                            <a href="#"
                               class="page-link"
                               data-page="${i}"
                               data-search="${currentSearch}"
                               data-category="${currentCategory}">
                                    ${i}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>

        </div> <%-- Hết Cột 2 --%>
    </div> <%-- Hết row --%>
</div>