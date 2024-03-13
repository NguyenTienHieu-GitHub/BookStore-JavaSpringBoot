angular.module("warehouse-product-day-app", ["warehouse-product-day-app.controllers", "datatables"]);
angular
  .module("warehouse-product-day-app.controllers", [])
  .controller(
    "warehouse-product-day-ctrl",
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
        $http.get("/rest/statistical/product/warehouse").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true)
        .withOption("searching", true)
        .withOption("info", true);
    }
  );
