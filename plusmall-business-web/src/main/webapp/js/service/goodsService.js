app.service('goodsService',function ($http) {
    //添加
    this.add=function (goodsGroupEntity) {
        return $http.post('../goods/add.do',goodsGroupEntity);
    }
})