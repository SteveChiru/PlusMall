app.service('brandService',function ($http) {
    //删除
    this.delete=function (ids) {
        return $http.get('../brand/delete.do?ids='+ids);
    }
    
    //查找
    this.search=function (pageNum, pageSize, searchEntity) {
        return $http.post('../brand/search.do?pageNum='+pageNum+'&pageSize='+pageSize,searchEntity);
    }

    //新增
    this.add=function (brandEntity) {
        return $http.post('../brand/addBrand.do',brandEntity);
    }

    //修改
    this.update=function (brandEntity) {
        return $http.post('../brand/updateBrand.do',brandEntity)
    }

    //根据id查找单个brand数据
    this.findOne=function (id) {
        return $http.get('../brand/findOne.do?id='+id);
    }
});