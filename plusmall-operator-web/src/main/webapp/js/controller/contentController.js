app.controller('contentController',function ($scope, $controller,
                 contentService,contentCatService,uploadService) {
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
        contentService.delete($scope.idsSelected).success(
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

    //查找全部广告分类
    $scope.contentCatList=[];  //广告分类类表库
    $scope.findContentCatList=function () {
        contentCatService.findAll().success(
            function (callback) {
                $scope.contentCatMap=callback;
                for (var i=0;i<callback.length;i++){
                    $scope.contentCatList[callback[i].id]=callback[i].name;
                }
            }
        )
    }
    
    //上传文件
    $scope.uploadFile=function () {
        uploadService.uploadFile().success(
            function (callback) {
                if (callback.success){  //如果上传成功，取出url
                    $scope.content.pic=callback.msg;   //设置文件地址
                    alert("上传成功，图片存储路径为："+callback.msg);
                } else {
                    alert(callback.msg);
                }
            }
        ).error(function () {
            alert("上传发生错误");
        });
    }
})