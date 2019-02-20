app.controller('goodsController',function ($scope, $controller, goodsService) {
    $controller('baseController',{$scope:$scope});

    //保存
    $scope.save=function () {
        goodsService.add($scope.goodsGroup).success(
            function (callback) {
                if (callback.success){
                    alert('保存成功');
                    $scope.goodsGroup={};
                } else {
                    alert(callback.msg);
                }
            }
        )
    }
})