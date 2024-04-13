function search() {
	var search = $("#search").val();
	var url = "/tim-kiem" + "?q=" + search;
	//if(search !== ""){
	//	url = url + "?q=" + search; 
	//}
	window.location.href = url;
}
// Sử dụng sự kiện keyup
document.getElementById("search").addEventListener("keyup", function(event) {
	// Kiểm tra xem phím được nhấn có phải là phím "Enter" (mã phím 13) không
	if (event.key === 'Enter') {
		// Gọi hàm xử lý tìm kiếm
		search();
	}
});

function setupClearInputOnClick(iconId, inputId) {
	const clearIcons = document.getElementsByClassName(iconId);
	const inputField = document.getElementById(inputId);

	// Kiểm tra xem clearIcons có tồn tại và không rỗng
	if (clearIcons && clearIcons.length > 0) {
		// Lặp qua tất cả các phần tử trong clearIcons
		for (let i = 0; i < clearIcons.length; i++) {
			// Thêm sự kiện 'click' cho mỗi phần tử
			clearIcons[i].addEventListener('click', function() {
				// Xóa giá trị
				inputField.value = '';
				// Focus lại vào input
				inputField.focus();
				hiddenIcon();
			});
		}
	}
}

// Sử dụng hàm với các tham số là class của icon và id của input
setupClearInputOnClick('x-mark', 'search');


function hiddenIcon() {
	const inputSearch = document.getElementById("search");
	const xmarkIcon = document.getElementById("x-mark-id");

	// Kiểm tra xem input có giá trị hay không
	if (inputSearch.value !== '') {
		// Input có giá trị
		xmarkIcon.classList.add("active"); // Hiển thị icon
		// console.log("Input có giá trị");
	} else {
		// Input không có giá trị
		xmarkIcon.classList.remove("active"); // Ẩn icon
		// console.log("Input không có giá trị");
	}
}

// Gọi hàm hiddenIcon khi input thay đổi
document.getElementById("search").addEventListener("input", hiddenIcon);

// Gọi hàm hiddenIcon khi trang web được tải lần đầu tiên
document.addEventListener("DOMContentLoaded", hiddenIcon);