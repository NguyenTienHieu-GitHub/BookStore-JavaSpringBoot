angular.module("contact-app", ["contact-app.controllers", "datatables"]);
angular
  .module("contact-app.controllers", [])
  .controller(
    "contact-ctrl",
    function (
      $scope,
      DTOptionsBuilder,
      DTColumnBuilder,
      DTColumnDefBuilder,
      $http
    ) {
      $scope.items = [];

      $scope.info = {};
      $scope.initialize = function () {
        $http.get("/rest/contact/pending").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

      $scope.formDetail = [];
      $scope.modalDetail = function (detail) {
        $http.get("/rest/contact/pending/" + detail.id).then((resp) => {
          $scope.formDetail = resp.data;
        });
        $("#modalDetail").modal("show");
      };

      $scope.formApprove = {};
      $scope.showModalApprove = function (item) {
        $scope.formApprove = item;
        $("#modalApprove").modal("show");
      };

      $scope.approve = function () {
        $http
          .put(`/rest/contact/form/approve/` + $scope.formApprove.id)
          .then((resp) => {
            var index = $scope.items.findIndex(
              (p) => p.id == $scope.formApprove.id
            );
            $scope.items.splice(index, 1);
            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã duyệt thành công đánh giá này!";
            $("#modalInfo").modal("show");
          })
          .catch((error) => {
            console.log(error);
          });
      };

      $scope.formDelete = {};
      $scope.showModal = function (item) {
        $scope.formDelete = item;
        $("#modal").modal("show");
      };

      $scope.delete = function () {
        $http
          .delete(`/rest/contact/form/delete/` + $scope.formDelete.id)
          .then((resp) => {
            var index = $scope.items.findIndex(
              (p) => p.id == $scope.formDelete.id
            );
            $scope.items.splice(index, 1);

            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã xóa đánh giá thành công!";
            $("#modalInfo").modal("show");

          })
          .catch((error) => {          
          });
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(4).notSortable(),
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true)
        .withOption("searching", true)
        .withOption("info", true);
    }
  );
