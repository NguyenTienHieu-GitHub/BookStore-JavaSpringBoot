var checkFullName = 0;

$('#email').attr('readonly', true);

$(document).ready(function () {

  $("#fullName").keyup(function () {
    var fullName = this.value;
    if (fullName == "") {
      $("#fullName").addClass("is-invalid");
      $("#showErrorFullName").text("Vui lòng nhập họ và tên!");
      checkFullName = 10;
    } else {
      var length = fullName.length;
      var minLength = $("#fullName").attr("data-minlength");
      var maxLength = $("#fullName").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#fullName").addClass("is-invalid");
        $("#showErrorFullName").text("Nhập giá trị từ 7 đến 30 ký tự!");
        checkFullName = 10;
      } else {
        $("#fullName").removeClass("is-invalid");
        $("#showErrorFullName").text("");
        checkFullName = 1;
      }
    }
    handlerButtonSave();
  });
});

function checkForm() {
  $("#fullName").keyup();
  return handlerButtonSave();
}

function handlerButtonSave() {
  var check = false;
  if (checkFullName !== 10) {
    check = true;
    $("#btnUpdate").prop("disabled", false);
  } else {
    check = false;
    $("#btnUpdate").prop("disabled", true);
  }
  return check;
}

var app = angular.module("user-form-app", []);

app.controller("user-form-ctrl", function ($scope, $http) {
  $scope.form = {};
  $scope.info = {};
  $scope.load = function () {
    $http
      .get("/rest/user/update/" + $("#demo").val())
      .then((resp) => {
        $scope.form = resp.data;
        $("#birthday").val($scope.form.birthday);
        $scope.form.gender = String($scope.form.gender);
		$scope.form.news = String($scope.form.news);
      })
      .catch((error) => {
        console.log(error);
      });
  };
  $scope.load();

  $scope.update = function () {
    if (checkForm()) {
	  $scope.form.birthday = String($("#birthday").val());
      var item = angular.copy($scope.form);
      $http  
        .put("/rest/user/form/" + $("#demo").val(), item)
        .then((resp) => {
          $scope.info.status = true;
          $scope.info.content = "Bạn đã cập nhật tài khoản thành công!";
          $("#modalSuccess").modal("show");
          var path = "/admin/user/update/" + $("#demo").val();
          $("a").attr("href", path);
          console.log(resp);
        })
        .catch((error) => {
          alert("Lỗi cập nhật");
          console.log(error);
        });
    }
  };
});

function formatDate(date) {
  var d = new Date(date),
    month = "" + (d.getMonth() + 1),
    day = "" + d.getDate(),
    year = d.getFullYear();

  if (month.length < 2) month = "0" + month;
  if (day.length < 2) day = "0" + day;

  return [year, month, day].join("-");
}
