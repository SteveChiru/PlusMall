app.controller('goodsController',function ($scope, $controller, goodsService) {
    $controller('baseController',{$scope:$scope});

    //保存
    $scope.save=function () {
        $scope.goodsGroup.tbGoodsDesc.introduction=editor.html();
        goodsService.add($scope.goodsGroup).success(
            function (callback) {
                if (callback.success){
                    alert('保存成功');
                    $scope.goodsGroup={};
                    editor.html('');    //清空富文本编辑器
                } else {
                    alert(callback.msg);
                }
            }
        )
    }
})