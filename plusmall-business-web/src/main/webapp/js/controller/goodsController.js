app.controller('goodsController',function ($scope, $controller, goodsService,itemCatService,typeTemplateService) {
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

    //读取一级分类
    $scope.selectItemCat1List=function () {
        itemCatService.searchByParentId(0).success(
            function (callback) {
                $scope.itemCat1List=callback.result;
            }
        );
    }

    //读取二级分类
    $scope.$watch('goodsGroup.tbGoods.category1Id',function (newValue, oldValue) {
        //根据选择的值，查询二级分类
        itemCatService.searchByParentId(newValue).success(
            function (callback) {
                $scope.itemCat2List = callback.result;
            }
        );
    });

    //读取三级分类
    $scope.$watch('goodsGroup.tbGoods.category2Id',function (newValue, oldValue) {
        //根据选择的值，查询二级分类
        itemCatService.searchByParentId(newValue).success(
            function (callback) {
                $scope.itemCat3List=callback.result;
            }
        );
    });

    //三级分类选择后 读取模板ID
    $scope.$watch('goodsGroup.tbGoods.category3Id',function (newValue, oldValue) {
        itemCatService.findOne(newValue).success(
            function (callback) {
                $scope.goodsGroup.tbGoods.typeTemplateId = callback.typeId;     //更新模板ID
            }
        )
    })

    //模板ID选择后，更新品牌列表
    $scope.$watch('goodsGroup.tbGoods.typeTemplateId',function (newValue, oldValue) {
        typeTemplateService.findOne(newValue).success(
            function (callback) {
                $scope.typeTemplate=callback;
                $scope.typeTemplate.brandIds=JSON.parse($scope.typeTemplate.brandIds);//品牌列表
            }
        )
    })
})