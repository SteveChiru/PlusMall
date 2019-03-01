app.controller('sellerController',function ($scope, $controller, sellerService,loginService) {
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

    //根据sellerId查找单个用户信息
    $scope.findOne=function(){
        loginService.loginName().success(
            function (callback) {
                $scope.loginName=callback.loginName;
                sellerService.findOne($scope.loginName).success(
                    function (callback) {
                        $scope.seller=callback;
                    }
                )
            }
        );
    }

    //更新信息
    $scope.update=function () {
        sellerService.update($scope.seller).success(
            function (callback) {
                if (callback.success){
                    top.location.href='/shoplogin.html';
                } else{
                    alert(callback.msg);
                }
            }
        )
    }
});