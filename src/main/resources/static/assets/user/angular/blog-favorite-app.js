  
  function addComment(productId) {
    angular.element($("#content")).scope().postComment(productId);
  }
  
  var app = angular.module("blog-app", []);
  
  app.controller("blog-ctrl", function ($scope, $http) {

  
    // Xu ly comment
    $scope.itemComments = {};
    $scope.listComment = [];
    $scope.loadComment = function () {
      $http
        .get("/rest/comment/form/blog/" + $("#blogId").val())
        .then((resp) => {
          $scope.listComment = resp.data;
          console.log($scope.listComment);
          if ($scope.listComment.length === 0) {
            $("#review").hide();
            $("#noComment").show();
            $("#pagination").hide();
          } else {
            $("#review").show();
            $("#noComment").hide();
            $("#pagination").show();
          }
        })
        .catch((error) => {});
    };
    $scope.loadComment();

  
    $scope.formComment = {};
    $scope.postComment = function (productId) {
      var content = $("#input-review").val();
      var rating = $('input[name="rating"]:checked').val();
      if (content == "") {
        $("#notification").removeClass("alert-success");
        $("#notification").addClass("alert-danger");
        $("#notification").html(
          "<i class='fa fa-info-circle'></i> Vui lòng nhập nội dung đánh giá!"
        );
        $("#notification").show();
      } else {
        if (typeof rating === "undefined") {
          $("#notification").removeClass("alert-success");
          $("#notification").addClass("alert-danger");
          $("#notification").html(
            "<i class='fa fa-info-circle'></i> Vui lòng chọn số sao đánh giá!"
          );
          $("#notification").show();
        }
        else{
          $("#notification").removeClass("alert-danger");
          $("#notification").addClass("alert-success");
          $("#notification").html(
            "<i class='fa fa-check-circle'></i> Cám ơn, vì phản hồi của bạn. Nó đã được gửi cho quản trị viên web để phê duyệt!"
          );
          $("#notification").show();
          
          $scope.formComment.productId = productId;
          $scope.formComment.content = content;
          $scope.formComment.star = rating;
          
          $http.post(`/rest/comment/form`, $scope.formComment).then((resp) => {
            console.log(resp.data);  
          });
  
          $("#input-review").val("");
          $('input[name="rating"]').removeAttr('checked');
        }
      }
    };
  
    $scope.pager = {
      page: 0,
      size: 5.0,
      get items(){
          var start = this.page * this.size;
          return $scope.listComment.slice(start, start + this.size);
          
      },
      get count() {
          return Math.ceil(1.0 * $scope.listComment.length / this.size);
      },
      first(){
        this.page = 0;
      },
      prev(){
        this.page--;
        if(this.page < 0){
          this.last();
        }
        
      },
      next(){
        this.page++;
        if(this.page >= this.count){
          this.first();
        }
      },
      last(){
        this.page = this.count -1;
      }
  }
  
});
  