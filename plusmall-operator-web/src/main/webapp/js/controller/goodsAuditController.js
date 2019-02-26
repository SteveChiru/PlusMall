app.controller('goodsAuditController',function ($scope,$controller,goodsAuditService) {
    $controller('baseController',{$scope:$scope});  //继承

    //查找
    $scope.searchTbGoods={};
    $scope.search=function (pageNum, pageSize) {
        goodsAuditService.search(pageNum,pageSize,$scope.searchTbGoods).success(
            function (callback) {
                $scope.result = callback.result;
                $scope.paginationConf.totalItems = callback.total;
            }
        )
    }
})