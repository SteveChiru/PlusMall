app.controller('typeTempController',function ($scope, $controller, typeTempService,brandService) {
    $controller('baseController',{$scope:$scope});

    //查找
    $scope.searchTypeTemp={};
    $scope.search=function (pageNum, pageSize) {
         typeTempService.search(pageNum,pageSize,$scope.searchTypeTemp).success(
             function (callback) {
                $scope.searchResult = callback.result;
                $scope.paginationConf.totalItems = callback.total;
            }
        );
    }

    //删除
    $scope.delete=function () {
        typeTempService.delete($scope.idsSelected).success(
            function (callback) {
                if (callback.success){
                    $scope.reloadList();
                } else {
                    alert(callback.msg);
                }
            }
        );
    }

    //新增行
    $scope.addTableRow=function () {
        $scope.typeTemp.customAttributeItems.push({});
    }
    //删除行
    $scope.deleTableRow=function (index) {
        $scope.typeTemp.customAttributeItems.splice(index,1);//删除
    }

    //保存：包括新建和更新
    $scope.save=function () {
        var serviceObj;
        if ($scope.typeTemp.id!=null) {
            serviceObj = typeTempService.update($scope.typeTemp);
        }else {
            serviceObj = typeTempService.add($scope.typeTemp);
        }
        serviceObj.success(
            function (callback) {
                if (callback.success){
                    $scope.reloadList();
                }else {
                    alert(callback.msg);
                }
            }
        );
    }

    //根据Id查找单个类型模板数据
    $scope.findOne=function (id) {
        typeTempService.findOne(id).success(
            function (callback) {
                $scope.typeTemp=callback;
                $scope.typeTemp.brandIds = JSON.parse($scope.typeTemp.brandIds);
                $scope.typeTemp.specIds = JSON.parse($scope.typeTemp.specIds);
                $scope.typeTemp.customAttributeItems = JSON.parse($scope.typeTemp.customAttributeItems);
            }
        );
    }

    $scope.brandList={data:[]};//品牌列表
    $scope.findBrandList=function(){
        brandService.selectOptionList().success(
            function (callback) {
                $scope.brandList={data:callback};
            }
        );
    }


    $scope.specList={data:[]};//规格列表
});