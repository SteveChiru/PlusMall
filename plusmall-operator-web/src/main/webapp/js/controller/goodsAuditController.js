app.controller('goodsAuditController',function ($scope,$controller,goodsAuditService,itemCatService) {
    $controller('baseController',{$scope:$scope});  //继承

    $scope.status=['未审核','已审核','审核未通过','关闭'];

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

    $scope.itemCatList=[];  //商品分类类表库
    //加载商品分类列表
    $scope.findItemCatList=function () {
        itemCatService.findAll().success(
            function (callback) {
                for (var i=0;i<callback.length;i++){
                    $scope.itemCatList[callback[i].id]=callback[i].name;
                }
            }
        )
    }
})