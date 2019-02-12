app.controller('baseController',function ($scope) {
    //重载
    $scope.reloadList=function(){
        //切换页码
        $scope.search($scope.paginationConf.currentPage,
            $scope.paginationConf.itemsPerPage);
    }

    //分页控件配置
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 5,
        itemsPerPage: 5,
        perPageOptions: [5,10, 20, 30, 40, 50],
        onChange: function(){
            $scope.reloadList();//页面加载分页控件时，默认执行了一次onChange函数
        }
    }

    $scope.idsSelected=[];

    $scope.updateSelection=function($event,id){
        if ($event.target.checked) {
            $scope.idsSelected.push(id);
        }else{
            var idx = $scope.idsSelected.indexOf(id);
            $scope.idsSelected.splice(idx,1)
        }
    }
});