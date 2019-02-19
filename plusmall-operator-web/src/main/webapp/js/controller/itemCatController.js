app.controller('itemCatController',function ($scope,$controller,itemCatService) {
    $controller('baseController',{$scope:$scope});

    //查找
    $scope.parentId=0;   //
    $scope.searchByParentId=function (pId) {
        $scope.parentId=pId;
        $scope.reloadList();
    }
    $scope.search=function (pageNum, pageSize) {
        itemCatService.search(pageNum,pageSize,$scope.parentId).success(
            function (callback) {
                $scope.searchResult = callback.result;
                $scope.paginationConf.totalItems = callback.total;
            }
        )
    }

    //删除
    $scope.delete=function () {
        itemCatService.delete($scope.idsSelected).success(
            function (callback) {
                if (callback.success){
                    $scope.reloadList();
                } else {
                    alert(callback.msg);
                }
            }
        )
    }

    //保存
    $scope.save=function () {
        var serviceObj;
        if ($scope.itemCat.id!=null){
            serviceObj = itemCatService.update($scope.itemCat);
        } else {
            $scope.itemCat.parentId = $scope.parentId;
            serviceObj = itemCatService.add($scope.itemCat);
        }
        serviceObj.success(
            function (callback) {
                if (callback.success){
                    $scope.reloadList();
                } else {
                    alert(callback.msg);
                }
            }
        );
    }

    //根据Id查找单个商品类别数据
    $scope.findOne=function (id) {
        itemCatService.findOne(id).success(
            function (callback) {
                $scope.itemCat = callback;
            }
        )
    }

    //面包屑导航
    $scope.grade=1; //默认为1级
    //设置级别
    $scope.setGrade=function (grade) {
        $scope.grade = grade;
    }

    //读取列表
    $scope.selectList=function (p_entity) {
        if ($scope.grade==1){   //如果为1级
            $scope.entity_1=null;
            $scope.entity_2=null;
        }
        if ($scope.grade==2){   //如果为2级
            $scope.entity_1=p_entity;
            $scope.entity_2=null;
        }
        if ($scope.grade==3){   //如果为3级
            $scope.entity_2=p_entity;
        }
        $scope.searchByParentId(p_entity.id);
    }


})