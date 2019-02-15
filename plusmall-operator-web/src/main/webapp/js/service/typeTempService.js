app.service('typeTempService',function ($http) {

    //查找
    this.search=function (pageNum,pageSize,searchEntity) {
        return $http.post('../typeTemplate/search.do?pageNum='+pageNum+'&pageSize='+pageSize,searchEntity);
    }

    //删除
    this.delete=function (ids) {
        return $http.get('../typeTemplate/delete.do?ids='+ids);
    }

    //新增
    this.add=function (typeTemp) {
        return $http.post('../typeTemplate/add.do',typeTemp);
    }

    //根据Id查找
    this.findOne=function (id) {
        return $http.get('../typeTemplate/findOne.do?id='+id);
    }

    //更新
    this.update=function (typeTemp) {
        return $http.post('../typeTemplate/update.do',typeTemp);
    }
});