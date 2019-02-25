app.service('goodsService',function ($http) {

    //添加
    this.add=function (goodsGroupEntity) {
        return $http.post('../goods/add.do',goodsGroupEntity);
    }

    //查找
    this.search=function (pageNum,pageSize,searchEntity) {
        return $http.post('../goods/search.do?pageNum='+pageNum+'&pageSize='+pageSize,searchEntity);
    }

    //删除
    this.delete=function (ids) {
        return $http.get('../goods/delete.do?ids='+ids);
    }

    //根据商品Id查找商品信息
    this.findOne=function (id) {
        return $http.get('../goods/findOne.do?id='+id);
    }
})