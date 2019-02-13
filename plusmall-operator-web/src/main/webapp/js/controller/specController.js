app.controller('specController',function ($scope, $controller, specService) {
    $controller('baseController',{$scope:$scope});  //继承

    //查找
    $scope.searchSpec={};
    $scope.search=function (pageNum, pageSize) {
        brandService.search(pageNum,pageSize,$scope.searchSpec).success(
            function (callback) {
                $scope.result = callback.result;
                $scope.paginationConf.totalItems = callback.total;
            }
        )
    }

    //删除
    $scope.delete=function () {
        if (confirm("确定要删除所选规格信息？")) {
            brandService.delete($scope.idsSelected).success(
                function (callback) {
                    if (callback.success) {
                        $scope.reloadList();
                    }else {
                        alert(callback.msg);
                    }
                }
            )
        }
    }
});