var checkFullName = 0;
var checkEmail = 0;

$(document).ready(function () {
  $.get("/rest/user", function (data, status) {
    var temp = [];
    for (const property in data) {
      temp[temp.length] = String(`${data[property].email}`);
    }
    localStorage.setItem("user", temp.join(", "));
  });

  $("#email").keyup(function () {
    var email = this.value;
    if (email == "") {
      $("#email").addClass("is-invalid");
      $("#showErrorEmail").text("Vui lòng nhập Email!");
      checkEmail = 10;
    } else {
      if (isValidEmail(email) == false) {
        $("#email").addClass("is-invalid");
        $("#showErrorEmail").text("Email không đúng định dạng!");
        checkEmail = 10;
      } else {
        var user = localStorage.getItem("user").split(", ");
        if (user.includes(email)) {
          $("#email").addClass("is-invalid");
          $("#showErrorEmail").text("Email đã tồn tại!");
          checkEmail = 10;
        } else {
          $("#email").removeClass("is-invalid");
          $("#showErrorEmail").text("");
          checkEmail = 1;
        }
      }
    }
    handlerButtonSave();
  });

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
  $("#email").keyup();
  $("#fullName").keyup();
  return handlerButtonSave();
}

function handlerButtonSave() {
  var check = false;
  if (checkEmail !== 10 && checkFullName !== 10) {
    check = true;
    $("#btnSave").prop("disabled", false);
  } else {
    check = false;
    $("#btnSave").prop("disabled", true);
  }
  return check;
}

var app = angular.module("user-form-app", []);

app.controller("user-form-ctrl", function ($scope, $http) {
  $scope.form = {};
  $scope.info = {};

  $scope.form.gender = "0";
  $scope.form.news = "0";

  $scope.save = function () {
    if (checkForm()) {
      $scope.form.birthday = String($("#birthday").val());
      var item = angular.copy($scope.form);
      $http
        .post(`/rest/user/form`, item)
        .then((resp) => {
          $scope.info.status = true;
          $scope.info.content =
            "Bạn đã thêm mới thành công một tài khoản người dùng!";
          $("#modalSuccess").modal("show");
          var path = "/admin/user/form";
          $("a").attr("href", path);
          console.log(resp);
        })
        .catch((error) => {
          alert("Lỗi thêm sản phẩm");
          console.log(error);
        });
    }
  };
});

function isVietnamesePhoneNumber(number) {
  return /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/.test(number);
}

function isValidEmail(email) {
  return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email);
}

function formatDate(date) {
  var d = new Date(date),
    month = "" + (d.getMonth() + 1),
    day = "" + d.getDate(),
    year = d.getFullYear();

  if (month.length < 2) month = "0" + month;
  if (day.length < 2) day = "0" + day;

  return [year, month, day].join("-");
}
