app.service('typeTempService',function ($http) {

    //查找
    this.search=function (pageNum,pageSize,searchEntity) {
        $http.post('../typeTemplate/search.do?pageNum='+pageNum+'&pageSize='+pageSize,searchEntity);
    }

    //删除
    this.delete=function (ids) {
        $http.get('../typeTemplate/delete.do?ids='+ids);
    }

    //新增
    this.add=function (typeTemp) {
        $http.post('../typeTemplate/add.do',typeTemp);
    }

    //根据Id查找
    this.findOne=function (id) {
        $http.get('../typeTemplate/findOne.do?id='+id);
    }

    //更新
    this.update=function (typeTemp) {
        $http.post('../typeTemplate/update.do',typeTemp);
    }
});