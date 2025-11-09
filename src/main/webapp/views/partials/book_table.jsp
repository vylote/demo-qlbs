<%-- /views/partials/book_table.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<c:set var="root" value="${pageContext.request.contextPath}" />
<%--
  PHẦN 1: BẢNG SÁCH (Đã "Bootstrap hóa")
  - Thêm class: table, table-striped, table-hover, table-bordered
  - Thêm class "align-middle" để căn giữa nội dung (ảnh, nút bấm)
--%>
<table class="table table-striped table-hover table-bordered align-middle">
    <%-- Dùng "table-dark" cho <thead> để có nền đen --%>
    <thead class="table-dark">
    <tr>
        <th>Mã Sách</th>
        <th>Thể Loại</th>
        <th>Tên Sách</th>
        <th>Ảnh Bìa</th>
        <th>Mô Tả</th>
        <th>Đơn Giá</th>
        <th>Số Lượng Tồn</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${danhSachSach}" var="s">
        <tr>
            <td>${s.maSach}</td>
            <td>${s.tenTheLoai}</td>
            <td>${s.tenSach}</td>
            <td>
                    <%-- Dùng "img-fluid" (responsive) và "rounded" (bo góc) --%>
                <c:choose>
                    <c:when test="${not empty s.anhBia}">
                        <img src="${root}/${s.anhBia}" alt="Ảnh bìa" class="img-fluid rounded" style="width: 80px;"
                             onerror="this.onerror=null; this.src='${root}/images/no-image.png';" />
                    </c:when>
                    <c:otherwise>
                        <img src="${root}/images/no-image.png" alt="Không có ảnh" class="img-fluid rounded" style="width: 80px;" />
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${s.moTa}</td>
            <%--<td>
                <fmt:formatNumber value="${s.donGia}" pattern="#,##0.##" /> VNĐ
            </td>--%>
            <td>${s.donGia} vnd</td>
            <td>${s.soLuongTon}</td>
            <td>
                    <%-- Dùng class "btn" (nút) của Bootstrap --%>
                <a href="edit?id=${s.maSach}" class="btn btn-primary btn-sm">
                    <i class="bi bi-pencil-square"></i> Sửa
                </a>
                <a href="delete?id=${s.maSach}" class="btn btn-danger btn-sm">
                    <i class="bi bi-trash"></i> Xóa
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<hr/>

<%--
  PHẦN PHÂN TRANG (PAGINATION) - ĐÃ SỬA LỖI TÔ MÀU
--%>
<nav aria-label="Page navigation">
    <%-- Dùng class "justify-content-center" để căn giữa --%>
    <ul class="pagination justify-content-center">

        <c:forEach begin="1" end="${totalPages}" var="i">

            <%--
              DÒNG QUAN TRỌNG NHẤT LÀ Ở ĐÂY
              Nó thêm class "active" nếu ${i} bằng ${currentPage}
            --%>
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