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

    //新建
    $scope.specPojo={specOptionsList:[]};
    //新增选项行
    $scope.addTableRow=function () {
        $scope.specPojo.specOptionsList.push({});
    }
    //批量选项删除
    $scope.deleTableRow=function (index) {
        $scope.specPojo.specOptionsList.splice(index,1);//删除
    }
    //保存
    $scope.save=function () {
        var serviceObject;
        if ($scope.brand.id != null){
            serviceObject = specService.update($scope.specPojo);
        }else {
            serviceObject = specService.add($scope.specPojo);
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

    //修改
    //根据id查找单个brand的数据
    $scope.findOne=function (id) {
        brandService.findOne(id).success(
            function (callback) {
                $scope.specPojo = callback;
            }
        )
    }

});