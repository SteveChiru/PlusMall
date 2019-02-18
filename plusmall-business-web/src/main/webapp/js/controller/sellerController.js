app.controller('sellerController',function ($scope, $controller, sellerService) {
    $controller('baseController',{$scope:$scope});    //继承

    //商家申请入驻
    $scope.apply=function () {
        sellerService.add($scope.seller).success(
            function (callback) {
                if (callback.success){
                    location.href='shoplogin.html';
                }else {
                    alert(callback.msg);
                }
            }
        );
    }

});