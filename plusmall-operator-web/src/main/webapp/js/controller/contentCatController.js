app.controller('contentCatController',function ($scope,$controller, contentCatService) {
    $controller('baseController',{$scope:$scope});  //继承

    //查找
    $scope.search=function (pageNum, pageSize) {
        contentCatService.search(pageNum,pageSize).success(
            function (callback) {
                $scope.result = callback.result;
                $scope.paginationConf.totalItems = callback.total;
            }
        )
    }

    //删除
    $scope.delete=function () {
        contentCatService.delete($scope.idsSelected).success(
            function (callback) {
                if (callback.success){
                    $scope.reloadList();
                }
            }
        )
    }

    //保存
    $scope.save=function () {
        var obj;
        if ($scope.contentCat.id != null){
            obj = contentCatService.update($scope.contentCat)
        }else {
            obj = contentCatService.add($scope.contentCat);
        }
        obj.success(
            function (callback) {
                if (callback.success){
                    $scope.reloadList();
                }
            }
        )
    }

    //根据Id查找
    $scope.findOne=function (id) {
        contentCatService.findOne(id).success(
            function (callback) {
                $scope.contentCat = callback;
            }
        )
    }
})