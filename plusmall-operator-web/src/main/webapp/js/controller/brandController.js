app.controller('brandController',function ($scope,$controller,brandService) {
    $controller('baseController',{$scope:$scope});  //继承

    //删除
    $scope.delete=function () {
        if (confirm("确定要删除所选品牌信息？")) {
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

    //查找
    $scope.searchBrand={};
    $scope.search=function (pageNum, pageSize) {
        brandService.search(pageNum,pageSize,$scope.searchBrand).success(
            function (callback) {
                $scope.result = callback.result;
                $scope.paginationConf.totalItems = callback.total;
            }
        )
    }

    //保存
    $scope.save=function () {
        var serviceObject;
        if ($scope.brand.id != null){
            serviceObject = brandService.update($scope.brand);
        }else {
            serviceObject = brandService.add($scope.brand);
        }
        serviceObject.success(
            function (callback) {
                if (callback.success){
                    $scope.reloadList();
                } else {
                    alert(callback.msg);
                }
            }
        );
    }

    //根据id查找单个brand的数据
    $scope.findOne=function (id) {
        brandService.findOne(id).success(
            function (callback) {
                $scope.brand = callback;
            }
        )
    }

});