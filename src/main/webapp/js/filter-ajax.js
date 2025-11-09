// /js/filter-ajax.js (Bản "An Toàn" + Filter NXB)
document.addEventListener("DOMContentLoaded", function() {
    // 1. Lấy container (KHUNG CHỨA CỦA BODYVIEW)
    const layoutContainer = document.getElementById("ajax-body-container");
    // 2. Lấy "KHUNG TRANH" (Nơi thực sự render)
    const partialContainer = document.querySelector(".ajax-load-container");

    if (layoutContainer) {
        let currentCategory = '';
        let currentNXB = '';

        // 2. Lấy Form tìm kiếm (từ header - USER)
        const searchFormUser = document.getElementById("search-form");
        const searchInputUser = document.getElementById("search-keyword");

        // 3. Lấy Form tìm kiếm (từ trang Admin - ADMIN)
        const searchFormAdmin = document.getElementById("search-form-admin");
        const searchInputAdmin = document.getElementById("search-keyword-admin");

        // 4. Lấy các link Thể loại (từ header/menu - USER)
        const categoryLinks = document.querySelectorAll(".filter-link");

        // === HÀM GỌI AJAX CHÍNH ===
        function fetchBookData(servletUrl, categoryId = '',
                               keyword = '', page = 1, maNXB='') {

            if (servletUrl === 'search') {
                currentCategory = categoryId;
                currentNXB = maNXB;
            }

                const url = `${servletUrl}?id=${categoryId}
                                    &search=${keyword}&page=${page}&maNXB=${maNXB}`;

                fetch(url) // Dùng URL tuyệt đối
                    .then(response => {
                        if (!response.ok) { // Kiểm tra lỗi 404/500
                            throw new Error(`HTTP error! status: ${response.status} cho URL: ${url}`);
                        }
                        return response.text();
                    })
                    .then(html => {
                        // SỬA LỖI Ở ĐÂY:
                        // Chúng ta nhắm vào "KHUNG TRANH" (partialContainer),
                        // KHÔNG phải "VỎ BỌC" (layoutContainer)
                        if (partialContainer) {
                            partialContainer.innerHTML = html; // Render lại "ruột"
                        } else {
                            // Xử lý trang User (Tấm 1 -> Tấm 2)
                            layoutContainer.innerHTML = html;
                        }
                    })
                    .catch(error => {
                        console.error('Lỗi AJAX:', error);
                    });

        }

        // === GẮN SỰ KIỆN ===

        // 5. Sự kiện click link THỂ LOẠI (Header/Menu) -> Luôn gọi Servlet của USER
        categoryLinks.forEach(link => {
            link.addEventListener("click", function(event) {
                event.preventDefault();
                const categoryId = this.dataset.id;
                if(searchInputUser) searchInputUser.value = '';
                fetchBookData('search', categoryId, '', 1, ''); // Gọi 'search' (relative-from-root)
            });
        });

        // 6. Sự kiện submit FORM TÌM KIẾM (Header - User)
        if (searchFormUser) {
            searchFormUser.addEventListener("submit", function(event) {
                event.preventDefault();
                const keyword = searchInputUser.value;

                fetchBookData('search', currentCategory, keyword, 1, currentNXB);
            });
        }

        // 7. Sự kiện submit FORM TÌM KIẾM (Trang Admin)
        if (searchFormAdmin) {
            searchFormAdmin.addEventListener("submit", function(event) {
                event.preventDefault();
                const keyword = searchInputAdmin.value;
                const servletUrl = layoutContainer.dataset.servletUrl;
                fetchBookData(servletUrl, '', keyword, 1, ''); // Gọi 'admin/filter-books'
            });
        }

        // 8. Sự kiện click link PHÂN TRANG (Thông minh)
        if (layoutContainer) {
            layoutContainer.addEventListener("click", function(event) {
                if (event.target.classList.contains('page-link')) {
                    event.preventDefault();

                    // Lấy Servlet URL từ "data-" của container
                    const servletUrl = layoutContainer.dataset.servletUrl; // ('admin/filter-books')

                    const page = event.target.dataset.page;
                    const category = event.target.dataset.category;
                    const search = event.target.dataset.search;
                    const nxb = event.target.dataset.nxb;

                    // Gọi đúng Servlet
                    fetchBookData(servletUrl, category, search, page, nxb);
                }
            });
            layoutContainer.addEventListener("change", function(event) {
                // B. XỬ LÝ DROPDOWN NXB
                if (event.target.id === 'nxb-filter') {
                    event.preventDefault();

                    const servletUrl = layoutContainer.dataset.servletUrl;
                    const keyword = searchInputUser ? searchInputUser.value : (searchInputAdmin ? searchInputAdmin.value : '');
                    const nxbId = event.target.value; // Lấy MaNXB vừa chọn

                    // Gọi AJAX (Trang 1, giữ thể loại, giữ tìm kiếm, đổi NXB)
                    fetchBookData(servletUrl, currentCategory, keyword, 1, nxbId);
                }
            });
        }
    }
});