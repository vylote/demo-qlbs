document.addEventListener("DOMContentLoaded", function() {

    // Biến lưu trữ trạng thái lọc/tìm kiếm hiện tại
    let currentCategory = '';
    let currentSearch = '';

    // 1. Lấy container (khung chứa) sẽ nhận kết quả
    const container = document.getElementById("book-list-container");

    // 2. Lấy Form tìm kiếm
    const searchForm = document.getElementById("search-form");
    const searchInput = document.getElementById("search-keyword");

    // 3. Lấy các link Thể loại (từ menu.jsp)
    const categoryLinks = document.querySelectorAll(".filter-link");

    // === HÀM GỌI AJAX CHÍNH ===
    // Hàm này sẽ được gọi bởi TẤT CẢ các sự kiện
    function fetchBookData(categoryId = '', keyword = '', page = 1) {

        // Cập nhật trạng thái hiện tại
        currentCategory = categoryId;
        currentSearch = keyword;

        // 6. GỌI AJAX (dùng fetch)
        //    Gọi đến /filter-books (Servlet ở Bước 2)
        fetch(`filter_books?id=${categoryId}&search=${keyword}&page=${page}`)
            .then(response => response.text()) // Nhận kết quả về dạng text (HTML)
            .then(html => {
                // 7. Render lại nội dung (cả Bảng VÀ Phân trang)
                container.innerHTML = html;
            })
            .catch(error => {
                console.error('Lỗi AJAX:', error);
            });
    }

    categoryLinks.forEach(link => {
        link.addEventListener("click", function(event) {
            event.preventDefault();
            const categoryId = this.dataset.id;
            searchInput.value = ''; // Xóa nội dung tìm kiếm

            // Gọi AJAX (Trang 1, lọc theo thể loại, không tìm kiếm)
            fetchBookData(categoryId, '', 1);
        });
    });

    // 5. Sự kiện khi submit FORM TÌM KIẾM
    if (searchForm) {
        searchForm.addEventListener("submit", function(event) {
            event.preventDefault(); // Ngăn form submit (tải lại trang)
            const keyword = searchInput.value;

            // Gọi AJAX (Trang 1, giữ thể loại, tìm theo từ khóa)
            fetchBookData(currentCategory, keyword, 1);
        });
    }

    // 6. Sự kiện khi click link PHÂN TRANG
    //    (Kiểm tra xem container có tồn tại không)
    if (container) {
        container.addEventListener("click", function(event) {
            if (event.target.classList.contains('page-link')) {
                event.preventDefault();

                const page = event.target.dataset.page;
                const category = event.target.dataset.category;
                const search = event.target.dataset.search;

                fetchBookData(category, search, page);
            }
        });
    }

});