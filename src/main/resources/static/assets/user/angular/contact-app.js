function addContact() {
  angular.element($("#content")).scope().postComment();
}

var app = angular.module("contact-app", []);

app.controller("contact-ctrl", function ($scope, $http) {
    // Xu ly comment
  $scope.itemComments = {};
  $scope.listComment = [];
  
  $scope.loadComment = function () {
    $http
      .get("/rest/contact")
      .then((resp) => {
        $scope.listComment = resp.data;
        
      })
      .catch((error) => {});
  };
  $scope.loadComment();


  $scope.formComment = {};
  $scope.postComment = function () {
    var content = $("#input-review").val();
    var name = $("#input-name").val();
    var email = $("#input-email").val();
    if (content == "" || name == "" || email == "") {
      $("#notification").removeClass("alert-success");
      $("#notification").addClass("alert-danger");
      $("#notification").html(
        "<i class='fa fa-info-circle'></i> Vui lòng nhập đầy đủ nội dung đánh giá!"
      );
      $("#notification").show();
    } else{
        $("#notification").removeClass("alert-danger");
        $("#notification").addClass("alert-success");
        $("#notification").html(
          "<i class='fa fa-check-circle'></i> Cám ơn, vì phản hồi của bạn. Nó đã được gửi cho quản trị viên web để phê duyệt!"
        );
        $("#notification").show();
        
        $scope.formComment.content = content;
        $scope.formComment.name = name;
        $scope.formComment.email = email;
        
        $http.post(`/rest/contact/form`, $scope.formComment).then((resp) => {
           console.log(resp.data);  
         });
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

  // Xy ly comment
});
