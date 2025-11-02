// /js/filter-ajax.js
document.addEventListener("DOMContentLoaded", function() {

    let currentCategory = '';

    // 1. Lấy container (KHUNG CHỨA CỦA BODYVIEW)
    const ajaxContainer = document.getElementById("ajax-body-container");

    // 2. Lấy Form tìm kiếm (từ header)
    const searchFormUser = document.getElementById("search-form");
    const searchInputUser = document.getElementById("search-keyword");

    // === HÀM GỌI AJAX CHÍNH ===
    // (Hàm này sẽ gọi /search và trả về Tấm 2)
    function fetchUserSearch(categoryId = '', keyword = '', page = 1) {

        currentCategory = categoryId;

        if (ajaxContainer) {
            fetch(`search?id=${categoryId}&search=${keyword}&page=${page}`)
                .then(response => response.text())
                .then(html => {
                    // THAY THẾ TOÀN BỘ "TẤM 1" BẰNG "TẤM 2"
                    ajaxContainer.innerHTML = html;
                })
                .catch(error => console.error('Lỗi AJAX:', error));
        }
    }

    // === GẮN SỰ KIỆN ===

    // 3. Sự kiện submit FORM TÌM KIẾM (Header - User)
    if (searchFormUser) {
        searchFormUser.addEventListener("submit", function(event) {
            event.preventDefault();
            const keyword = searchInputUser.value;
            fetchUserSearch('', keyword, 1); // Tìm theo keyword
        });
    }

    // 4. Sự kiện click link THỂ LOẠI (Header) / XEM THÊM (Tấm 1)
    //    Dùng Event Delegation trên toàn bộ <body>
    document.body.addEventListener("click", function(event) {

        // 4a. Xử lý click Link Thể Loại / Nút Xem Thêm
        if (event.target.classList.contains('filter-link')) {
            event.preventDefault();
            const categoryId = event.target.dataset.id;
            if(searchInputUser) searchInputUser.value = '';
            fetchUserSearch(categoryId, '', 1);
        }

        // 4b. Xử lý click Phân Trang (từ Tấm 2)
        if (event.target.classList.contains('page-link')) {
            event.preventDefault();
            const page = event.target.dataset.page;
            const category = event.target.dataset.category;
            const search = event.target.dataset.search;
            fetchUserSearch(category, search, page);
        }
    });
});