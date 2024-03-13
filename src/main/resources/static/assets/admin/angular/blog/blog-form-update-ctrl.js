var checkTitle = 0;
var checkImage = 0;
var checkContent = 0;
var checkUploadDate = 0;
var checkBanner = 0;
var checkActive = 0;
var checkNameSearch = 0;
var checkDescription = 0;

$(document).ready(function () {
  $("#titleBlog").keyup(function () {
    var titleBlog = this.value;
    if (titleBlog == "") {
      $("#titleBlog").addClass("is-invalid");
      $("#showErrorTitleBlog").text("Vui lòng nhập mã tiêu đề Blogs!");
      checkTitle = 10;
    } else {
      var length = titleBlog.length;
      var minLength = $("#titleBlog").attr("data-minlength");
      var maxLength = $("#titleBlog").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#titleBlog").addClass("is-invalid");
        $("#showErrorTitleBlog").text("Nhập giá trị từ 5 đến 200 ký tự!");
        checkTitle = 10;
      } else {
        $("#titleBlog").removeClass("is-invalid");
        $("#showErrorTitleBlog").text("");
        checkTitle = 1;
      }
    }
    handlerButtonSave();
  });

  $("#nameSearch").keyup(function () {
    var nameSearch = this.value;
    if (nameSearch == "") {
      $("#nameSearch").addClass("is-invalid");
      $("#showErrorNameSearch").text("Vui lòng nhập tên tìm kiếm sản phẩm!");
      checkNameSearch = 10;
    } else {
      var length = nameSearch.length;
      var minLength = $("#nameSearch").attr("data-minlength");
      var maxLength = $("#nameSearch").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#nameSearch").addClass("is-invalid");
        $("#showErrorNameSearch").text("Nhập giá trị từ 5 đến 50 ký tự!");
        checkNameSearch = 10;
      } else {
        $("#nameSearch").removeClass("is-invalid");
        $("#showErrorNameSearch").text("");
        checkNameSearch = 1;
      }
    }
    handlerButtonSave();
  });
  
  $("#chooseImage").change(function () {
    var image = this.value;
    if (image == "") {
      $("#valueImage").addClass("is-invalid");
      $("#showErrorImage").text("Vui lòng chọn logo!");
      checkImage = 10;
    } else {
      angular.element(this).scope().imageImageChanged(this.files);
      $("#valueImage").removeClass("is-invalid");
      $("#showErrorImage").text("");
      checkImage = 1;
    }
    handlerButtonSave();
  });
  
  $("#chooseBanner").change(function () {
    var banner = this.value;
    if (banner == "") {
      $("#valueImageBanner").addClass("is-invalid");
      $("#showErrorImageBanner").text("Vui lòng chọn banner!");
      checkBanner = 10;
    } else {
      angular.element(this).scope().imageImageBanner(this.files);
      $("#valueImageBanner").removeClass("is-invalid");
      $("#showErrorImageBanner").text("");
      checkBanner = 1;
    }
    handlerButtonSave();
  });
  
  $("#uploadDate").change(function() {
		var uploaddate = this.value;
		if (uploaddate == "") {
			$("#uploadDate").addClass("is-invalid");
			$("#showErrorUploadDate").text("Vui lòng chọn ngày cập nhật trang!");
			checkUploadDate = 10;
		}
		else {
			$("#uploadDate").removeClass("is-invalid");
			$("#showErrorUploadDate").text("");
			checkUploadDate = 1;
		}
		handlerButtonSave();
	});

    $("#content").keyup(function () {
    var content = this.value;
    if (content == "") {
      $("#content").addClass("is-invalid");
      $("#showErrorContent").text("Vui lòng nhập nội dung blog!");
      checkContent = 10;
    } else {
      var length = content.length;
      var minLength = $("#content").attr("data-minlength");
      var maxLength = $("#content").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#content").addClass("is-invalid");
        $("#showErrorContent").text("Nội dung phải từ 200-10000 ký tự!");
        checkContent = 10;
      } else {
        $("#content").removeClass("is-invalid");
        $("#showErrorContent").text("");
        checkContent = 1;
      }
    }
    handlerButtonSave();
  });

  $("#active").change(function() {
		var active = this.value;
		if (active == "") {
			$("#active").addClass("is-invalid");
			$("#showErrorActive").text("Vui lòng chọn trạng thái!");
			checkActive = 10;
		}
		else {
			$("#active").removeClass("is-invalid");
			$("#showErrorActive").text("");
			checkActive = 1;
		}
		handlerButtonSave();
	});

  $("#description").keyup(function () {
    var description = this.value;
    if (description == "") {
      $("#description").addClass("is-invalid");
      $("#showErrorDescription").text("Vui lòng nhập mô tả blog!");
      checkDescription = 10;
    } else {
      var length = description.length;
      var minLength = $("#description").attr("data-minlength");
      var maxLength = $("#description").attr("data-maxlength");
      
      if (length < minLength || length > maxLength) {
        $("#description").addClass("is-invalid");
        $("#showErrorDescription").text("Mô tả phải từ 50 đến 255 ký tự!");
        checkDescription = 10;
      } else {
        $("#description").removeClass("is-invalid");
        $("#showErrorDescription").text("");
        checkDescription = 1;
      }
    }
    handlerButtonSave();
  });
});

function checkForm() {
  $("#titleBlog").keyup();
  //$("#chooseImage").change();
  $("#content").keyup();
  //$("#chooseBanner").change();
  $("#uploadDate").change();
  $("#active").change();
  $("#nameSearch").keyup();
  $("#description").keyup();
  return handlerButtonSave();
}

function handlerButtonSave() {
  var check = false;
  if (checkTitle !== 10 && 
      checkImage !== 10 &&
      checkContent !== 10 &&
      checkUploadDate !== 10 &&
 	    checkBanner !== 10 &&
      checkActive !== 10 && 
      checkNameSearch !== 10 &&
      checkDescription !== 10) {
    check = true;
    $("#btnUpdate").prop("disabled", false);
  } else {
    check = false;
    $("#btnUpdate").prop("disabled", true);
  }
  return check;
}
	

var app = angular.module("blog-form-app", []);

app.controller("blog-form-ctrl", function ($scope, $http) {
  $scope.form = {};
  $scope.info = {};
  $scope.load = function () {
    $http
      .get("/rest/blog/form/" + $("#demo").val())
      .then((resp) => {
        $scope.form = resp.data;
        $("#uploadDate").val($scope.form.uploadDay);
      })
      .catch((error) => {
        console.log(error);
      });
  };
  $scope.load();

  $scope.update = function () {
    if (checkForm()) {
    $scope.form.uploadday = String($("#uploaddate").val());
      $scope.form.id = $("#demo").val();
      var item = angular.copy($scope.form);
      $http
        .put("/rest/blog/form/" + $("#demo").val(), item)
        .then((resp) => {
          $scope.info.status = true;
          $scope.info.content = "Bạn đã cập nhật danh mục thành công!";
          $("#modalSuccess").modal("show");
          var path = "/admin/blogs/update/" + $scope.form.id;
          $("a").attr("href", path);
          console.log(resp);
        })
        .catch((error) => {
          alert("Lỗi thêm sản phẩm");
          console.log(error);
        });
    }
  };

  // upload image
  $scope.imageImageChanged = function (files) {
    var data = new FormData();
    data.append("file", files[0]);
    $http
      .post("/rest/upload/imageBlog", data, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
        $scope.form.image = resp.data.name;
      })
      .catch((error) => {
        alert("loi load hinh");
        console.log(error);
      });
  };
  
  //upload banner

$scope.imageImageBanner = function (files) {
    var data = new FormData();
    data.append("file", files[0]);
    $http
      .post("/rest/upload/imageBlog", data, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
        $scope.form.banner = resp.data.name;
      })
      .catch((error) => {
        alert("loi load hinh");
        console.log(error);
      });
  };
  
});
