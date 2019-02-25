app.service('goodsService',function ($http) {

    //添加
    this.add=function (goodsGroupEntity) {
        return $http.post('../goods/add.do',goodsGroupEntity);
    }

    //查找
    this.search=function (pageNum,pageSize,searchEntity) {
        return $http.post('../goods/search.do?pageNum='+pageNum+'&pageSize='+pageSize,searchEntity);
    }
})