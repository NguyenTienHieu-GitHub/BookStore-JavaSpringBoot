var checkDiscount = 0;

$("#checkClick").prop("disabled", true);

$(document).ready(function () {
  $("#discount").change(function () {
    var discount = this.value;
    if (discount == 0) {
      checkDiscount = 10;
    } else {
      checkDiscount = 1;
    }
    handlerButtonSave();
  });
});

function checkForm() {
  $("#discount").change();
  return handlerButtonSave();
}

function handlerButtonSave() {
  var check = false;
  if (checkDiscount !== 10) {
    check = true;
    $("#checkClick").prop("disabled", false);
  } else {
    check = false;
    $("#checkClick").prop("disabled", true);
  }
  return check;
}


angular.module("user-app", ["user-app.controllers", "datatables"]);
angular
  .module("user-app.controllers", [])
  .controller(
    "user-ctrl",
    function (
      $scope,
      DTOptionsBuilder,
      DTColumnBuilder,
      DTColumnDefBuilder,
      $http
    ) {
      $scope.items = [];
      $scope.form = {};
      $scope.initialize = function () {
        $http.get("/rest/discount/user/list").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

      $scope.showModal = function (item) {
        $scope.form = item;
        $("#modal").modal("show");
      };

      $scope.itemDiscount = [];
      $scope.loadDiscount = function () {
        $http.get("/rest/discount/available").then((resp) => {
          $scope.itemDiscount = resp.data;
          console.log($scope.itemDiscount);
        });
      };
      $scope.loadDiscount();

      $scope.sendCodeDiscount = function () {
        if(checkForm()){
            var code = $("#discount").val();
            $http.post("/rest/discount/user/list/" + code, $scope.form).then(resp => {				
				$("#modalInfo").modal("show");
				var path = "/admin/discount/user/list";
				$("a").attr("href", path);
				console.log(resp);
            }).catch(error => {
                alert("Lỗi gửi mã")             
                console.log(error);
            });
        }
      }

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(5).notSortable(),
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true)
        .withOption("searching", true)
        .withOption("info", true);
    }
  );
