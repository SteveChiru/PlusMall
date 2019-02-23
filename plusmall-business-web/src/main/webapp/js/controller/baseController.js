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

    //提取json字符串数据中某个属性，返回拼接字符串，逗号分隔
    $scope.jsonToString=function(jsonString,key){
        var json=JSON.parse(jsonString);//将json字符串转换为json对象
        var value="";
        for(var i=0;i<json.length;i++){
            if(i>0){
                value+=","
            }
            value+=json[i][key];
        }
        return value;
    }

    //从集合中按照key查询对象
    $scope.searchObjectByKey=function (list, key, keyValue) {
        for (var i=0;i<list.length;i++){
            if (list[i][key]==keyValue){
                return list[i];
            }
        }
        return null;
    }
});