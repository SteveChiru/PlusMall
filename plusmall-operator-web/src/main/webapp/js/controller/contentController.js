app.controller('contentController',function ($scope, $controller, contentService) {
    $controller('baseController',{$scope:$scope});  //继承

    //查找
    $scope.search=function (pageNum, pageSize) {
        contentService.search(pageNum,pageSize).success(
            function (callback) {
                $scope.result = callback.result;
                $scope.paginationConf.totalItems = callback.total;
            }
        )
    }

    //删除
    $scope.delete=function () {
        contentCat.delete($scope.idsSelected).success(
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
        if ($scope.content.id != null){
            obj = contentService.update($scope.content)
        }else {
            obj = contentService.add($scope.content);
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
        contentService.findOne(id).success(
            function (callback) {
                $scope.content = callback;
            }
        )
    }
})