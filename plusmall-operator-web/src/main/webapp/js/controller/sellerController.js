app.controller('operatorSellerController',function ($scope, $controller, operatorSellerService) {
    $controller('baseController',{$scope:$scope});  //继承

    $scope.statusList=['未审核','已审核','审核未通过','关闭'];

    //根据条件查找商家
    $scope.searchSeller={};
    $scope.search=function (pageNum, pageSize) {
        operatorSellerService.search(pageNum,pageSize,$scope.searchSeller).success(
            function (callback) {
                $scope.result = callback.result;
                $scope.paginationConf.totalItems = callback.total;
            }
        )
    }

    //查找单个商家
    $scope.findOne=function (strId) {
        operatorSellerService.findOne(strId).success(
            function (callback) {
                $scope.seller = callback;
            }
        )
    }

    //更新商家
    $scope.update=function () {
        operatorSellerService.update($scope.seller).success(
            function (callback) {
                if (callback.success){
                    $scope.reloadList();
                } else {
                    alert(callback.msg);
                }
            }
        )
    }

    //审核操作
    $scope.updateStatus=function (status) {
        operatorSellerService.updateStatus($scope.seller.sellerId,status).success(
            function (callback) {
                if (callback.success){
                    alert(callback.msg);
                    $scope.reloadList();
                }
            }
        )
    }
});