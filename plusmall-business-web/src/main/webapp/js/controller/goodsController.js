app.controller('goodsController',function ($scope, $controller,
                   goodsService,itemCatService,typeTemplateService,uploadService) {
    $controller('baseController',{$scope:$scope});

    $scope.goodsGroup={tbGoods:{},tbGoodsDesc:{customAttributeItems:[],itemImages:[],specificationItems:[]}};//定义页面实体结构

    $scope.status=['未审核','已审核','审核未通过','关闭'];

    //保存
    $scope.save=function () {
        $scope.goodsGroup.tbGoodsDesc.introduction=editor.html();
        //把JSON实体转换为string
        $scope.goodsGroup.tbGoodsDesc.customAttributeItems=angular.toJson($scope.goodsGroup.tbGoodsDesc.customAttributeItems);
        $scope.goodsGroup.tbGoodsDesc.itemImages=angular.toJson($scope.goodsGroup.tbGoodsDesc.itemImages);
        $scope.goodsGroup.tbGoodsDesc.specificationItems=angular.toJson($scope.goodsGroup.tbGoodsDesc.specificationItems);

        goodsService.add($scope.goodsGroup).success(
            function (callback) {
                if (callback.success){
                    alert('保存成功');
                    window.location.reload();
                } else {
                    alert(callback.msg);
                }
            }
        )
    }

    //读取一级分类
    var getItemCat1List=false;
    $scope.selectItemCat1List=function () {
        itemCatService.searchByParentId(0).success(
            function (callback) {
                $scope.itemCat1List=callback.result;
                getItemCat1List = true;
            }
        );
    }

    //读取二级分类
    var getItemCat2List = false;
    $scope.$watch('goodsGroup.tbGoods.category1Id',function (newValue, oldValue) {
        //根据选择的值，查询二级分类
        if (getItemCat1List){   //只有在一级分类列表已经获取完的前提下，观察一分类变化，才搜寻二级分类
            itemCatService.searchByParentId(newValue).success(
                function (callback) {
                    $scope.itemCat2List = callback.result;
                    getItemCat2List = true;
                }
            );
        }
    });

    //读取三级分类
    var getItemCat3List = false;
    $scope.$watch('goodsGroup.tbGoods.category2Id',function (newValue, oldValue) {
        //根据选择的值，查询二级分类
        if (getItemCat2List){
            itemCatService.searchByParentId(newValue).success(
                function (callback) {
                    $scope.itemCat3List=callback.result;
                    getItemCat3List = true;
                }
            );
        }
    });

    //三级分类选择后 读取模板ID
    var getTypeTemplate = false;
    $scope.$watch('goodsGroup.tbGoods.category3Id',function (newValue, oldValue) {
        if (getItemCat3List){
            itemCatService.findOne(newValue).success(
                function (callback) {
                    $scope.goodsGroup.tbGoods.typeTemplateId = callback.typeId;     //更新模板ID
                    getTypeTemplate = true;
                }
            )
        }

    })

    //模板ID选择后，更新品牌列表
    $scope.$watch('goodsGroup.tbGoods.typeTemplateId',function (newValue, oldValue) {
        if (getTypeTemplate){
            typeTemplateService.findOne(newValue).success(
                function (callback) {
                    $scope.typeTemplate=callback;
                    $scope.typeTemplate.brandIds=JSON.parse($scope.typeTemplate.brandIds);//品牌列表
                    $scope.goodsGroup.tbGoodsDesc.customAttributeItems=JSON.parse($scope.typeTemplate.customAttributeItems);
                }
            );
            //查询规格列表
            typeTemplateService.findSpecList(newValue).success(
                function (callback) {
                    $scope.specList=callback
                }
            )
        }
    })

    //上传图片
    $scope.uploadFile=function () {
        uploadService.uploadFile().success(
            function (callback) {
                if (callback.success){  //如果上传成功，取出url
                    $scope.imageUpload.url=callback.msg;   //设置文件地址
                    alert("上传成功，图片存储路径为："+callback.msg);
                } else {
                    alert(callback.msg);
                }
            }
        ).error(function () {
            alert("上传发生错误");
        });
    }

    //添加图片列表
    $scope.add_image_entity=function () {
        $scope.goodsGroup.tbGoodsDesc.itemImages.push($scope.imageUpload);
    }

    //列表中移除图片
    $scope.remove_image_entity=function (index) {
        $scope.goodsGroup.tbGoodsDesc.itemImages.splice(index,1);
    }

    $scope.updateSpecAttribute=function ($event,name,value) {
        var object = $scope.searchObjectByKey(
            $scope.goodsGroup.tbGoodsDesc.specificationItems,'attributeName',name);
        if (object != null){
            if ($event.target.checked){
                object.attributeValue.push(value);
            } else {    //取消勾选
                object.attributeValue.splice(object.attributeValue.indexOf(value),1);
                //如果选项都取消了，将此条记录移除
                if (object.attributeValue.length==0){
                    $scope.goodsGroup.tbGoodsDesc.specificationItems.splice(
                        $scope.goodsGroup.tbGoodsDesc.specificationItems.indexOf(object),1);
                }
            }
        }else {
            $scope.goodsGroup.tbGoodsDesc.specificationItems.push(
                {"attributeName":name,"attributeValue":[value]});
        }
    }
    
    //创建SKU列表
    $scope.createItemList=function () {
        $scope.goodsGroup.itemList=[{spec:{},price:0,num:99999,status:'0',isDefault:'0'}];
        var items = $scope.goodsGroup.tbGoodsDesc.specificationItems;
        for (var i=0;i<items.length;i++){
            $scope.goodsGroup.itemList =
                addColumn($scope.goodsGroup.itemList,items[i].attributeName,items[i].attributeValue);
        }
    }

    //添加列值
    addColumn=function (list, columnName, columnValues) {
        var newList=[];     //新的集合
        for (var i=0;i<list.length;i++){
            var oldRow = list[i];
            for (var j=0;j<columnValues.length;j++){
                var newRow = JSON.parse(angular.toJson(oldRow));
                newRow.spec[columnName]=columnValues[j];
                newList.push(newRow);
            }
        }
        return newList;
    }
    
    //查找
    $scope.searchTbGoods={};
    $scope.search=function (pageNum,pageSize) {
        goodsService.search(pageNum,pageSize,$scope.searchTbGoods).success(
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

    //删除商品
    $scope.delete=function () {
        if (confirm("确定要删除所选商品的数据？")){
            goodsService.delete($scope.idsSelected).success(
                function (callback) {
                    if (callback.success){
                        $scope.reloadList();
                    } else {
                        alert(callback.msg);
                    }
                }
            )
        }
    }
})