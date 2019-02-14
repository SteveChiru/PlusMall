app.service('specService',function ($http) {
   //删掉
   this.delete=function (ids) {
       return $http.get('../specification/delete.do?ids='+ids);
   }

   //查找
    this.search=function (pageNum, pageSize, searchEntity) {
        return $http.post('../specification/search.do?pageNum='+pageNum+'&pageSize='+pageSize,searchEntity);
    }

    //新增
    this.add=function (specPojo) {
        return $http.post('../specification/add.do',specPojo);
    }

    //根据id查找单个specification数据
    this.findOne=function (id) {
        return $http.get('../specification/findOne.do?id='+id);
    }

    //修改
    this.update=function (specPojo) {
        return $http.post('../specification/update.do',specPojo);
    }
});